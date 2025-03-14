package View;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;
import Controller.CarrinhoController;
import Controller.ProdutoController;
import Model.DAO.ProdutoDao;
import Model.Produto;

public class CompraView {
    private Scanner scanner = new Scanner(System.in);
    private ProdutoController produtoController = new ProdutoController();
    private CarrinhoController carrinhoController;

    public CompraView(CarrinhoController carrinhoController) {
        this.carrinhoController = carrinhoController;
    }

    public void exibirMenuCompras() {
        while (true) {
            System.out.println("\n1 - Ver produtos");
            System.out.println("2 - Adicionar produto ao carrinho");
            System.out.println("3 - Remover produto do carrinho");
            System.out.println("4 - Ver carrinho");
            System.out.println("5 - Atualizar quantidade de um produto no carrinho");
            System.out.println("6  - Finalizar compra");
            System.out.println("0 - Sair");
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
                    removerProdutoDoCarrinho(); //corrigir erro nesse método
                    break;
                case 4:
                    visualizarCarrinho();
                    break;
                case 5:
                    atualizarQuantidadeProduto(); // erro,,  não atualiza
                    break;
                case 6:
                	finalizarCompra();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = produtoController.listarProdutosNaPrateleira();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto disponível.");
            return;
        }
        System.out.println("Produtos disponíveis:");
        for (Produto p : produtos) {
            System.out.println("Id produto = " + p.getId() + " - " + p.getNome() + " - R$" + p.getPreco() + " - Estoque: " + p.getQuantidade());
        }
    }

    private void adicionarProdutoAoCarrinho() {
    	System.out.print("Digite o Id do produto: ");
        if (!scanner.hasNextInt()) {
            System.out.println("ID inválido! Digite um número inteiro.");
            scanner.next();
            return;
        }
        int id = scanner.nextInt();

        System.out.print("Digite a quantidade: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Quantidade inválida! Digite um número inteiro.");
            scanner.next();
            return;
        }
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        String resultado = carrinhoController.adicionarProduto(id, quantidade);
        System.out.println(resultado);
    }

    private void removerProdutoDoCarrinho() {
        System.out.print("Digite o ID do produto a ser removido: ");
        if (!scanner.hasNextInt()) {
            System.out.println("ID inválido! Digite um número inteiro.");
            scanner.next();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        String resultado = carrinhoController.removerProduto(id);
        System.out.println(resultado);
    }

    private void visualizarCarrinho() {
        System.out.println("Itens no carrinho:");
        // Exibe os itens no carrinho com os preços arredondados
        carrinhoController.getItens().forEach((produto, quantidade) -> {
            // Calcula o total do item e arredonda para 2 casas decimais
            BigDecimal precoUnitario = new BigDecimal(produto.getPreco()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalItem = precoUnitario.multiply(new BigDecimal(quantidade)).setScale(2, RoundingMode.HALF_UP);

            System.out.println("Id  = " + produto.getId() + " - " + produto.getNome() + " - Quantidade: " 
                + quantidade + " - Preço unitário: R$" + precoUnitario + " - Total: R$" + totalItem);
        });
         // Calcula o total da compra e arredonda para 2 casas decimais
        BigDecimal totalCompra = new BigDecimal(carrinhoController.calcularTotal()).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Total da compra: R$" + totalCompra);
    }

    private void atualizarQuantidadeProduto() {
        System.out.print("Digite o ID do produto: ");
        if (!scanner.hasNextInt()) {
            System.out.println("ID inválido! Digite um número inteiro.");
            scanner.next();
            return;
        }
        int id = scanner.nextInt();

        System.out.print("Digite a nova quantidade: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Quantidade inválida! Digite um número inteiro.");
            scanner.next();
            return;
        }
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        String resultado = carrinhoController.atualizarQuantidade(id, quantidade);
        System.out.println(resultado);
    }

    private void finalizarCompra() {
        System.out.println("Total da compra: R$" + carrinhoController.calcularTotal());
        System.out.print("Confirmar compra? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Compra realizada com sucesso!");
            // lógica para atualizar o estoque no banco de dados
        } else {
            System.out.println("Compra cancelada.");
        }
    }
}
