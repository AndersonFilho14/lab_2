package Model.DAO;

import Model.Database.ConnectionFactory;
import Model.Categoria;
import Model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
    private Connection conn;

    public ProdutoDao() {
        try {
            this.conn = ConnectionFactory.createdConnectionToMySQL();
        } catch (SQLException e) {
            e.printStackTrace();
            this.conn = null;
        }
    }

    // CREATE
    public void inserirProduto(Produto produto, int id_funcionario) {
        String sql = "INSERT INTO produtos (nome_produto, categoria_produto, estoque_produto, preco_produto, validade_produto, id_funcionario, prateleira) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria().name());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setFloat(4, produto.getPreco());
            stmt.setDate(5, Date.valueOf(produto.getValidade()));
            stmt.setInt(6, id_funcionario); 
            stmt.setBoolean(7, produto.isPrateleira());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id_produtos"),
                    rs.getString("nome_produto"),
                    rs.getDate("validade_produto").toString(),
                    rs.getFloat("preco_produto"),
                    Categoria.valueOf(rs.getString("categoria_produto").toUpperCase()), // Garante compatibilidade
                    rs.getInt("estoque_produto"),
                    rs.getBoolean("prateleira")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Atualizar um produto
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome_produto=?, categoria_produto=?, estoque_produto=?, preco_produto=?, validade_produto=?, prateleira=? WHERE id_produtos=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria().name());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setFloat(4, produto.getPreco());
            stmt.setDate(5, Date.valueOf(produto.getValidade()));
            stmt.setBoolean(6, produto.isPrateleira());
            stmt.setInt(7, produto.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletar um produto pelo ID
    public void deletarProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id_produtos=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Buscar um produto pelo nome
    public Produto buscarProdutoPorNome(String nome) {
        String sql = "SELECT * FROM produtos WHERE nome_produto LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%"); // Adiciona % antes e depois do nome para buscar padrões
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produto(
                    rs.getInt("id_produtos"),
                    rs.getString("nome_produto"),
                    rs.getDate("validade_produto").toString(),
                    rs.getFloat("preco_produto"),
                    Categoria.valueOf(rs.getString("categoria_produto").toUpperCase()),
                    rs.getInt("estoque_produto"),
                    rs.getBoolean("prateleira")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Diminui a quantidade do produto
    public void subtrairQuantidade(int id, int quantidade) {
        String sql = "UPDATE produtos SET estoque_produto = estoque_produto - ? WHERE id_produtos = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade); // Subtrai a quantidade do estoque
            stmt.setInt(2, id); // Define qual produto será atualizado
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Soma a quantidade ao produto
    public void somarQuantidade(int id, int quantidade) {
        String sql = "UPDATE produtos SET estoque_produto = estoque_produto + ? WHERE id_produtos = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantidade); // Soma a quantidade ao estoque
            stmt.setInt(2, id); // Define qual produto será atualizado
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Diminui o preço do produto
    public void diminuirPreco(int id, float preco) {
        String sql = "UPDATE produtos SET preco_produto = preco_produto - ? WHERE id_produtos = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, preco); // Subtrai o preço do produto
            stmt.setInt(2, id); // Define qual produto será atualizado
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Soma o preço ao produto
    public void somarPreco(int id, float preco) {
        String sql = "UPDATE produtos SET preco_produto = preco_produto + ? WHERE id_produtos = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, preco); // Soma o preço ao produto
            stmt.setInt(2, id); // Define qual produto será atualizado
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adiciona ou retira o produto da prateleira (muda o estado booleano)
    public void alterarPrateleira(int id, boolean prateleira) {
        String sql = "UPDATE produtos SET prateleira = ? WHERE id_produtos = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, prateleira); // se o produto estará na prateleira ou não
            stmt.setInt(2, id); // Define qual produto será atualizado
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retorna produtos com validade que está chegando ao fim
    public List<Produto> validadeProxima(int dias) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE DATEDIFF(validade_produto, CURDATE()) <= ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dias); // Definir os dias para a validade que está chegando ao fim
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id_produtos"),
                    rs.getString("nome_produto"),
                    rs.getDate("validade_produto").toString(),
                    rs.getFloat("preco_produto"),
                    Categoria.valueOf(rs.getString("categoria_produto").toUpperCase()),
                    rs.getInt("estoque_produto"),
                    rs.getBoolean("prateleira")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}

