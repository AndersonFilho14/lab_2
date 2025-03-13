package View;

import java.util.Scanner;

import Model.Carrinho;

public class CompraView {
	private Scanner scanner = new Scanner(System.in);
	private Carrinho carrinho = new Carrinho();
	
	public void exibirMenuCompras() {
        while (true) {
            System.out.println("\n1 - Ver produtos");
            System.out.println("2 - Adicionar produto ao carrinho");
            System.out.println("3 - Ver carrinho");
            System.out.println("4 - Finalizar compra");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    adicionarProdutoAoCarrinho();
                    break;
                case 3:
                    carrinho.visualizarCarrinho();
                    break;
                case 4:
                    finalizarCompra();
                    return;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarProdutos() {
        try {
            List<Produto> produtos = produtoDao.listarProdutos();
            System.out.println("Produtos disponíveis:");
            for (Produto p : produtos) {
                System.out.println(p.getId() + " - " + p.getNome() + " - R$" + p.getPreco() + " - Estoque: " + p.getQuantidade());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    private void adicionarProdutoAoCarrinho() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); 

        try {
            Produto produto = produtoDao.buscarProdutoPorId(id);
            if (produto != null && produto.getQuantidade() >= quantidade) {
                carrinho.adicionarProduto(produto, quantidade);
                System.out.println("Produto adicionado ao carrinho!");
            } else {
                System.out.println("Quantidade indisponível!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto: " + e.getMessage());
        }
    }

    private void finalizarCompra() {
        System.out.println("Total da compra: R$" + carrinho.calcularTotal());
        System.out.print("Confirmar compra? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Compra cancelada.");
        }
    }
}
4. Criar método para buscar produto por ID
No ProdutoDao, crie um método para buscar um produto específico:

java
Copy
Edit
public Produto buscarProdutoPorId(int id) throws SQLException {
    String sql = "SELECT * FROM produtos WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Produto produto = new Produto(
                    rs.getString("nome"),
                    rs.getDate("validade").toLocalDate(),
                    rs.getDouble("preco"),
                    rs.getString("categoria"),
                    rs.getInt("quantidade")
                );
                produto.setId(rs.getInt("id"));
                return produto;
            }
        }
    }
    return null;
}
}
