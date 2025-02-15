package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.Cliente;
import Model.DAO.ClienteDao;


/**
 * Controlador responsável por gerenciar os clientes no sistema.
 */
public class ClienteController {
	ClienteDao clienteDao = new ClienteDao();
    private List<Cliente> clientes = new ArrayList<>();


    /**
     * Cadastra um novo cliente no sistema.
     * 
     * @param nome O nome do cliente.
     * @param email O email do cliente.
     * @param senha A senha do cliente.
     * @param cpf O CPF do cliente.
     * @param telefone O telefone do cliente.
     */
    public void cadastrarCliente(String nome, String email, String senha, String cpf, String telefone) {
        try {
            Cliente novoCliente = new Cliente(nome, email, senha, cpf, telefone);
            clienteDao.cadastrarCliente(novoCliente); // Inserir no banco
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    /**
     * Lista todos os clientes cadastrados no sistema.
     * 
     * @return Lista de clientes.
     */
    public List<Cliente> listarClientes() {
        try {
            return clienteDao.listarClientes(); // Buscar todos os clientes no banco
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Consulta um cliente pelo ID.
     * 
     * @param id O ID do cliente a ser consultado.
     * @return O cliente correspondente ou null se não encontrado.
     */

    public Cliente consultarClientePorId(int id) {
        try {
            return clienteDao.consultarClientePorId(id); // Consultar por ID no banco
        } catch (SQLException e) {
            System.err.println("Erro ao consultar cliente: " + e.getMessage());
            return null;
        }
    }


    /**
     * Atualiza os dados de um cliente no sistema.
     * Os campos que forem nulos ou vazios não são atualizados.
     * 
     * @param id O ID do cliente a ser atualizado.
     * @param nome O novo nome do cliente (pode ser null).
     * @param email O novo email do cliente (pode ser null).
     * @param senha A nova senha do cliente (pode ser null).
     * @param cpf O novo CPF do cliente (pode ser null).
     * @param telefone O novo telefone do cliente (pode ser null).
     */
    public void atualizarCliente(int id, String nome, String email, String senha, String cpf, String telefone) {
        try {
            Cliente cliente = clienteDao.consultarClientePorId(id);
            if (cliente != null) {
                if (nome != null && !nome.isEmpty()) {
                    cliente.setNome(nome);
                }
                if (email != null && !email.isEmpty()) {
                    cliente.setEmail(email);
                }
                if (senha != null && !senha.isEmpty()) {
                    cliente.setSenha(senha);
                }
                if (cpf != null && !cpf.isEmpty()) {
                    cliente.setCpf(cpf);
                }
                if (telefone != null && !telefone.isEmpty()) {
                    cliente.setTelefone(telefone);
                }
                clienteDao.atualizarCliente(cliente); // Atualizar no banco
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }



    /**
     * Remove um cliente do sistema.
     * 
     * @param id O ID do cliente a ser removido.
     */
    public void removerCliente(int id) {
        try {
            Cliente cliente = clienteDao.consultarClientePorId(id);
            if (cliente != null) {
                clienteDao.removerCliente(cliente); // Remover do banco
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover cliente: " + e.getMessage());
        }
    }

    /**
     * Verifica as credenciais de login de um cliente.
     * 
     * @param email O email do cliente.
     * @param senha A senha do cliente.
     * @return O cliente correspondente ou null se as credenciais estiverem incorretas.
     */
    public Cliente verificarLogin(String email, String senha) {
        try {
            Cliente cliente = clienteDao.consultarClientePorEmailESenha(email, senha);
            if (cliente != null) {
                return cliente; // Login válido, retorna o cliente
            } else {
                return null; // Login inválido
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }	
    
    
    /**
     * Adiciona pontos de fidelidade a um cliente.
     * 
     * @param id O ID do cliente a quem os pontos serão adicionados.
     * @param pontos A quantidade de pontos a ser adicionada.
     */
    public void adicionarPontosFidelidade(int id, int pontos) {
    	try {
		Cliente cliente = consultarClientePorId(id);
		
		if (cliente != null) {
        clienteDao.adicionarPontosFidelidade(id, pontos);
        } else {
        	System.out.println("Cliente não encontrado.");
        }} catch (SQLException e) {
            System.err.println("Erro ao remover cliente: " + e.getMessage());
        }
    }

    /**
     * Remove pontos de fidelidade de um cliente.
     * 
     * @param id O ID do cliente de quem os pontos serão removidos.
     * @param pontos A quantidade de pontos a ser removida.
     */
    public void removerPontosFidelidade(int id, int pontos) {
        try{
        	Cliente cliente = consultarClientePorId(id);
        	if (cliente != null) {
        	clienteDao.removerPontosFidelidade(id, pontos);
        	} else {
        		throw new IllegalArgumentException("Cliente não encontrado.");
        	}} catch (SQLException e) {
                System.err.println("Erro ao remover cliente: " + e.getMessage());
            }
    }
}