package Model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Financeiro;
import Model.Database.ConnectionFactory;

public class FinanceiroDao {
    private Connection conn;

    public FinanceiroDao() {
        try {
            this.conn = ConnectionFactory.createdConnectionToMySQL();
        } catch (SQLException e) {
            e.printStackTrace();
            this.conn = null;
        }
    }

    
    public List<Financeiro> gerarRelatorioGeral() {
        List<Financeiro> lista = new ArrayList<>();
        String sql = "SELECT * FROM financeiro";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Financeiro f = new Financeiro(
                        rs.getInt("id"),
                        rs.getDouble("valor_compra"),
                        rs.getInt("id_cliente"),
                        rs.getString("data_compra")
                );
                lista.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public List<Financeiro> gerarRelatorioUltimosDias(int dias) {
        List<Financeiro> lista = new ArrayList<>();
        String sql = "SELECT * FROM financeiro WHERE data_compra >= NOW() - INTERVAL ? DAY";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dias);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Financeiro f = new Financeiro(
                            rs.getInt("id"),
                            rs.getDouble("valor_compra"),
                            rs.getInt("id_cliente"),
                            rs.getString("data_compra")
                    );
                    lista.add(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public boolean inserirFinanceiro(Financeiro financeiro) {
        String sql = "INSERT INTO financeiro (valor_compra, id_cliente) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, financeiro.getValorCompra());
            stmt.setInt(2, financeiro.getIdCliente());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        financeiro.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
