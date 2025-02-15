package Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por criar e gerenciar a conexão com o banco de dados MySQL.
 * Utiliza o JDBC para conectar ao banco especificado com as credenciais fornecidas.
 */
public class ConnectionFactory {
    
    private static final String URL = "jdbc:mysql://localhost:3306/lab_2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Cria e retorna uma nova conexão com o banco de dados MySQL.
     *
     * @return A conexão estabelecida com o banco de dados.
     * @throws SQLException Caso ocorra um erro na conexão.
     */
    public static Connection createdConnectionToMySQL() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
