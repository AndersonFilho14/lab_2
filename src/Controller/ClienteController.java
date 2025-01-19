package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.Cliente;


/**
 * Controlador responsável por gerenciar os clientes no sistema.
 */
public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();


    /**
     * Cadastra um novo cliente.
     * 
     * @param nome O nome do cliente.
     * @param email O email do cliente.
     * @param senha A senha do cliente.
     * @param cpf O CPF do cliente.
     * @param telefone O telefone do cliente.
     * @throws IllegalArgumentException se o ID do cliente já existir.
     */
    public void cadastrarCliente(String nome, String email, String senha, String cpf, String telefone) {
        try {
            Cliente cliente = new Cliente(nome, email, senha, cpf, telefone);
            clientes.add(cliente);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    /**
     * Lista todos os clientes cadastrados.
     * @return Lista de clientes.
     */
    public List<Cliente> listarClientes() {
        return clientes;
    }

    /**
     * Consulta um cliente por ID.
     * 
     * @param id O ID do cliente a ser consultado.
     * @return O cliente correspondente ou null se não encontrado.
     */
    public Cliente consultarClientePorId(int id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst().orElse(null);
    }

    /**
     * Consulta clientes por nome.
     * 
     * @param nome O nome do cliente a ser consultado.
     * @return Lista de clientes que possuem o nome correspondente.
     */
    public List<Cliente> consultarClientePorNome(String nome) {
        return clientes.stream()
                .filter(cliente -> cliente.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }

    /**
     * Atualiza as informações de um cliente.
     * Caso algum valor seja null ou vazio, o campo não será alterado.
     * 
     * @param id O ID do cliente a ser atualizado.
     * @param nome O novo nome do cliente.
     * @param email O novo email do cliente.
     * @param senha A nova senha do cliente.
     * @param cpf O novo CPF do cliente.
     * @param telefone O novo telefone do cliente.
     * @throws IllegalArgumentException se o cliente não for encontrado.
     */
    public void atualizarCliente(int id, String nome, String email, String senha, String cpf, String telefone) {
        Cliente cliente = consultarClientePorId(id);
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
            if (telefone != null) { // Permite telefone vazio ("")
                cliente.setTelefone(telefone);
            }
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }


    /**
     * Remove um cliente por ID.
     * 
     * @param id O ID do cliente a ser removido.
     * @throws IllegalArgumentException se o cliente não for encontrado.
     */
    public void removerCliente(int id) {
        Cliente cliente = consultarClientePorId(id);
        if (cliente != null) {
            clientes.remove(cliente);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
 }


    /**
     * Adiciona pontos de fidelidade a um cliente.
     * 
     * @param id O ID do cliente a quem os pontos serão adicionados.
     * @param pontos A quantidade de pontos a ser adicionada.
     * @throws IllegalArgumentException se o cliente não for encontrado.
     */
    public void adicionarPontosFidelidade(int id, int pontos) {
        Cliente cliente = consultarClientePorId(id);
        if (cliente != null) {
            cliente.adicionarPontos(pontos);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    /**
     * Remove pontos de fidelidade de um cliente.
     * 
     * @param id O ID do cliente de quem os pontos serão removidos.
     * @param pontos A quantidade de pontos a ser removida.
     * @throws IllegalArgumentException se o cliente não for encontrado.
     */
    public void removerPontosFidelidade(int id, int pontos) {
        Cliente cliente = consultarClientePorId(id);
        if (cliente != null) {
            cliente.removerPontos(pontos);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }
}