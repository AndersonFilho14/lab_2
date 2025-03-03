package Model.DAO;

import Model.Funcionario;
import Model.Database.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
    private Connection conn;

    /**
     * Construtor que inicializa a conexão com o banco de dados.
     */
    public FuncionarioDao() {
        try {
            this.conn = ConnectionFactory.createdConnectionToMySQL(); // Conectar ao banco de dados
        } catch (SQLException e) {
            e.printStackTrace();
            this.conn = null;
        }
    }

    /**
     * Consulta um funcionário no banco de dados utilizando o email e senha fornecidos.
     * 
     * @param email O email do funcionário.
     * @param senha A senha do funcionário.
     * @return O objeto Funcionario correspondente ao email e senha fornecidos, ou null se não encontrado.
     * @throws SQLException Se ocorrer algum erro ao acessar o banco de dados.
     */
    public Funcionario consultarFuncionarioPorEmailESenha(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM funcionarios WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario funcionario = new Funcionario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                    );
                    funcionario.setCargo(rs.getInt("cargo"));
                    funcionario.setEmpregado(rs.getBoolean("empregado"));
                    return funcionario;
                }
            }
        }
        return null;
    }

    /**
     * Cadastra um novo funcionário no banco de dados.
     */
    public void adicionar(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, email, senha, cpf, telefone, cargo, empregado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getEmail());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setInt(6, funcionario.getCargo());
            stmt.setBoolean(7, funcionario.getEmpregado());
            stmt.executeUpdate();
        }
    }

    /**
     * Lista todos os funcionários cadastrados no banco de dados.
     */
    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario f = new Funcionario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                );
                f.setId(rs.getInt("id"));
                f.setCargo(rs.getInt("cargo"));
                f.setEmpregado(rs.getBoolean("empregado"));
                funcionarios.add(f);
            }
        }
        return funcionarios;
    }

    /**
     * Atualiza os dados de um funcionário no banco de dados.
     */
    public void atualizar(Funcionario funcionario, int id) throws SQLException {
        String sql = "UPDATE funcionarios SET nome=?, email=?, senha=?, cpf=?, telefone=?, cargo=?, empregado=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getEmail());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setInt(6, funcionario.getCargo());
            stmt.setBoolean(7, funcionario.getEmpregado());
            stmt.setInt(8, id);
            stmt.executeUpdate();
        }
    }

    /**
     * Remove um funcionário do banco de dados.
     */
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    /**
     * Demite um funcionário definindo o campo empregado como 0.
     * 
     * Esse método atualiza o campo empregado para 0 (demitido)
     * onde o nome do funcionário corresponder ao parâmetro passado.
     */
    public void demitirFuncionarioPorNome(String nome) throws SQLException {
        String sql = "UPDATE funcionarios SET empregado = 0 WHERE nome = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        }
    }
    
    public void empregarFuncionarioPorNome(String nome) throws SQLException {
        String sql = "UPDATE funcionarios SET empregado = 1 WHERE nome = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        }
    }
    
    public void mudarCargoFuncionarioPorNome(String nome, int novoCargo) throws SQLException {
        String sql = "UPDATE funcionarios SET cargo = ? WHERE nome = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novoCargo);
            stmt.setString(2, nome);
            stmt.executeUpdate();
        }
    }
    

}
