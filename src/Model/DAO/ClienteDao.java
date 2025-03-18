package Model.DAO;
import Model.Database.ConnectionFactory;

import Model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe ClienteDao é responsável pela interação com o banco de dados para realizar operações CRUD (Create, Read, Update, Delete)
 * relacionadas ao cliente, além de gerenciar pontos de fidelidade.
 */
public class ClienteDao implements DAO<Cliente>{
    private Connection conn;

    
    public ClienteDao() {
        try {
            this.conn = ConnectionFactory.createdConnectionToMySQL(); 
        } catch (SQLException e) {
            e.printStackTrace();
            this.conn = null; 
        }
    }
    
    @Override
    public void create(Cliente entity) throws SQLException {
        cadastrarCliente(entity); 
    }

    @Override
    public List<Cliente> readAll() throws SQLException {
        return listarClientes(); 
    }

    @Override
    public void update(Cliente entity) throws SQLException {
        atualizarCliente(entity); 
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    
    /**
     * Consulta um cliente no banco de dados utilizando o email e senha fornecidos.
     * 
     * @param email O email do cliente.
     * @param senha A senha do cliente.
     * @return O objeto Cliente correspondente ao email e senha fornecidos, ou null se não encontrado.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public Cliente consultarClientePorEmailESenha(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                    );
                    cliente.setId(rs.getInt("id"));
                    return cliente;
                }
            }
        }
        return null; 
    }
    
    
    /**
     * Cadastra um novo cliente no banco de dados.
     * 
     * @param cliente O objeto Cliente a ser cadastrado.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public void cadastrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, email, senha, cpf, telefone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getSenha());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefone());
            stmt.executeUpdate();
        }
    }


    /**
     * Lista todos os clientes cadastrados no banco de dados.
     * 
     * @return Uma lista de objetos Cliente representando todos os clientes.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                );
                cliente.setId(rs.getInt("id"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }


    /**
     * Consulta um cliente no banco de dados pelo ID.
     * 
     * @param id O ID do cliente a ser consultado.
     * @return O objeto Cliente correspondente ao ID fornecido, ou null se não encontrado.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public Cliente consultarClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                    );
                    cliente.setId(rs.getInt("id"));
                    cliente.setPontosFidelidade(rs.getInt("pontos_fidelidade"));
                    return cliente;
                }
            }
        }
        return null;
    }


    /**
     * Atualiza os dados de um cliente no banco de dados.
     * 
     * @param cliente O objeto Cliente contendo as novas informações.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, email = ?, senha = ?, cpf = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getSenha());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefone());
            stmt.setInt(6, cliente.getId());
            stmt.executeUpdate();
        }
    }


    /**
     * Remove um cliente do banco de dados.
     * 
     * @param cliente O objeto Cliente a ser removido.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public void removerCliente(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            stmt.executeUpdate();
        }
    }


    /**
     * Adiciona pontos de fidelidade a um cliente.
     * 
     * @param id O ID do cliente que receberá os pontos.
     * @param pontos A quantidade de pontos a ser adicionada.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public void adicionarPontosFidelidade(int id, int pontos) throws SQLException {
        String sql = "UPDATE clientes SET pontos_fidelidade = pontos_fidelidade + ? WHERE id = ?";
        
        try (Connection con = ConnectionFactory.createdConnectionToMySQL();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, pontos);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    /**
     * Remove pontos de fidelidade de um cliente.
     * 
     * @param id O ID do cliente que perderá os pontos.
     * @param pontos A quantidade de pontos a ser removida.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public void removerPontosFidelidade(int id, int pontos) throws SQLException {
        String sql = "UPDATE clientes SET pontos_fidelidade = pontos_fidelidade - ? WHERE id = ?";
        
        try (Connection con = ConnectionFactory.createdConnectionToMySQL();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, pontos);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
    

}

