package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.Cliente;
import Model.ClienteFidelidade;

/**
 * Controlador responsável por gerenciar os clientes no sistema.
 */
public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();
    private List<ClienteFidelidade> clientesFidelidade = new ArrayList<>();

    /**
     * Cadastra um novo cliente.
     * 
     * @param cliente O cliente a ser cadastrado.
     * @throws IllegalArgumentException se o ID do cliente já existir.
     */
    public void cadastrarCliente(Cliente cliente) {
        if (isIdDuplicado(cliente.getId())) {
            throw new IllegalArgumentException("Já existe um cliente com este ID.");
        }
        clientes.add(cliente);
    }

    /**
     * Lista todos os clientes cadastrados.
     * 
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
                .findFirst()
                .orElse(null);
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
     * 
     * @param id O ID do cliente a ser atualizado.
     * @param nome O novo nome do cliente.
     * @param cpf O novo CPF do cliente.
     * @param email O novo email do cliente.
     * @param telefone O novo telefone do cliente.
     * @throws IllegalArgumentException se o cliente não for encontrado.
     */
    public void atualizarCliente(int id, String nome, String cpf, String email, String telefone) {
        Cliente cliente = consultarClientePorId(id);
        if (cliente != null) {
            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setEmail(email);
            cliente.setTelefone(telefone);
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
        boolean removido = clientes.removeIf(cliente -> cliente.getId() == id);
        if (!removido) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    /**
     * Verifica se já existe um cliente com o ID fornecido.
     * 
     * @param id O ID a ser verificado.
     * @return true se o ID já existir, false caso contrário.
     */
    private boolean isIdDuplicado(int id) {
        return clientes.stream().anyMatch(cliente -> cliente.getId() == id);
    }


    /**
     * Esse método percorre a lista de clientes e retorna true se um cliente
	*			com o ID informado for encontrado. Caso contrário, ele retorna false.
     * **/
    public boolean existeCliente(int id) {
        // Verifica se existe um cliente com o ID informado
        return clientes.stream().anyMatch(cliente -> cliente.getId() == id);
    }
    
    /**
     * Adiciona um cliente de fidelidade.
     * 
     * @param clienteFidelidade O cliente de fidelidade a ser adicionado.
     */
 // Métodos para manipulação de ClienteFidelidade (CRUD)

    public void cadastrarClienteFidelidade(ClienteFidelidade clienteFidelidade) {
        // Verifica se o cliente existe antes de adicionar o ClienteFidelidade
        if (!existeCliente(clienteFidelidade.getClienteId())) {
            throw new IllegalArgumentException("Cliente com o ID informado não existe.");
        }
        clientesFidelidade.add(clienteFidelidade);
    }

    public List<ClienteFidelidade> listarClientesFidelidade() {
        return clientesFidelidade;
    }

    public ClienteFidelidade consultarClienteFidelidadePorId(int id) {
        return clientesFidelidade.stream()
                .filter(clienteFidelidade -> clienteFidelidade.getClienteId() == id)
                .findFirst()
                .orElse(null);
    }

    public void atualizarClienteFidelidade(ClienteFidelidade clienteFidelidadeAtualizado) {
        for (int i = 0; i < clientesFidelidade.size(); i++) {
            ClienteFidelidade clienteFidelidade = clientesFidelidade.get(i);
            if (clienteFidelidade.getClienteId() == clienteFidelidadeAtualizado.getClienteId()) {
                clientesFidelidade.set(i, clienteFidelidadeAtualizado);
                return;
            }
        }
        throw new IllegalArgumentException("Cliente fidelidade não encontrado.");
    }

    public void removerClienteFidelidade(int id) {
        boolean removido = clientesFidelidade.removeIf(clienteFidelidade -> clienteFidelidade.getClienteId() == id);
        if (!removido) {
            throw new IllegalArgumentException("Cliente fidelidade não encontrado.");
        }
    }
}