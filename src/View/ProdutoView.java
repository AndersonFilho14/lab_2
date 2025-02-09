package View;

import Controller.ProdutoController;
import Model.Categoria;
import Model.Produto;

import java.time.LocalDate;
import java.util.Scanner;

public class ProdutoView {
    private ProdutoController produtoController;
    private Scanner scanner;

    public ProdutoView(ProdutoController produtoController) {
        this.produtoController = produtoController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\nMenu de Produtos:");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Editar Produto");
            System.out.println("3 - Remover Produto");
            System.out.println("4 - Listar Produtos");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a escolha

            switch (opcao) {
                case 1:
                	cadastrarProduto();
                    break;
                case 2:
                    editarProduto();
                    break;
                case 3:
                    removerProduto();  // Chama o método de remover produto
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }


    /**
     * Realiza o cadastro de um novo produto.
     * Solicita ao usuário os dados necessários e chama o controlador para realizar o cadastro.
     */
    private void cadastrarProduto() {
        System.out.println("\nCadastro de Produto");

        // Solicitar dados do produto
        String nome = obterNomeProduto();
        String validade = obterValidadeProduto();
        float preco = obterPrecoProduto();
        Categoria categoria = obterCategoriaProduto();
        int quantidade = obterQuantidadeProduto();

        // Criar produto e chamar controlador para cadastro
        Produto produto = new Produto(nome, validade, preco, categoria, quantidade);
        produtoController.adicionarProduto(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    /**
     * Obtém o nome do produto.
     */
    private String obterNomeProduto() {
        System.out.print("Digite o nome do produto: ");
        return scanner.nextLine();
    }

    /**
     * Obtém a validade do produto no formato yyyy-MM-dd.
     */
    private String obterValidadeProduto() {
        System.out.print("Digite a validade do produto (yyyy-MM-dd): ");
        return scanner.nextLine();
    }

    /**
     * Obtém o preço do produto.
     */
    private float obterPrecoProduto() {
        float preco = -1;
        while (preco <= 0) {
            System.out.print("Digite o preço do produto: ");
            if (scanner.hasNextFloat()) {
                preco = scanner.nextFloat();
                if (preco <= 0) {
                    System.out.println("Preço deve ser um número positivo.");
                }
            } else {
                System.out.println("Valor inválido para preço. Tente novamente.");
            }
            scanner.nextLine();  // Consumir a linha de quebra após o valor
        }
        return preco;
    }

    /**
     * Obtém a categoria do produto.
     */
    private Categoria obterCategoriaProduto() {
        int categoriaEscolhida = 0;
        while (categoriaEscolhida < 1 || categoriaEscolhida > 3) {
            System.out.print("Escolha a categoria (1- COMIDA, 2- LIMPEZA, 3- MATERIAL): ");
            if (scanner.hasNextInt()) {
                categoriaEscolhida = scanner.nextInt();
                scanner.nextLine();  // Consumir a linha de quebra após o número
                if (categoriaEscolhida < 1 || categoriaEscolhida > 3) {
                    System.out.println("Categoria inválida, tente novamente.");
                }
            } else {
                System.out.println("Opção inválida. Por favor, insira um número de 1 a 3.");
                scanner.nextLine();  // Limpar o buffer para tentar novamente
            }
        }

        switch (categoriaEscolhida) {
            case 1: return Categoria.COMIDA;
            case 2: return Categoria.LIMPEZA;
            case 3: return Categoria.MATERIAL;
            default: return null;  // Nunca deve chegar aqui
        }
    }

    /**
     * Obtém a quantidade inicial do produto.
     */
    private int obterQuantidadeProduto() {
        int quantidade = -1;
        while (quantidade <= 0) {
            System.out.print("Digite a quantidade inicial do produto: ");
            if (scanner.hasNextInt()) {
                quantidade = scanner.nextInt();
                if (quantidade <= 0) {
                    System.out.println("Quantidade deve ser um número positivo.");
                }
            } else {
                System.out.println("Valor inválido para quantidade. Tente novamente.");
            }
            scanner.nextLine();  // Consumir a linha de quebra após o valor
        }
        return quantidade;
    }

    // Editar produto
    public void editarProduto() {
        System.out.print("Digite o nome do produto a ser editado: ");
        String nomeProduto = scanner.nextLine();
        Produto produtoExistente = produtoController.buscarProdutoPorNome(nomeProduto);  // Supondo que exista esse método

        if (produtoExistente != null) {
            // Editar dados
            String nome = obterNomeProduto();
            produtoExistente.setNome(nome.isEmpty() ? produtoExistente.getNome() : nome);

            String validade = obterValidadeProduto();
            if (validade.isEmpty()) {
                // Se não for informado, manter o valor atual
                produtoExistente.setValidade(produtoExistente.getValidade());
            } else {
                // Converter a String para LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                produtoExistente.setValidade(LocalDate.parse(validade, formatter));
            }

            float preco = obterPrecoProduto();
            produtoExistente.setPreco(preco <= 0 ? produtoExistente.getPreco() : preco);

            int quantidade = obterQuantidadeProduto();
            produtoExistente.setQuantidade(quantidade <= 0 ? produtoExistente.getQuantidade() : quantidade);

            System.out.println("Produto editado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
    
    public void removerProduto() {
        System.out.print("Digite o nome do produto a ser removido: ");
        String nomeProduto = scanner.nextLine();

        Produto produtoExistente = produtoController.buscarProdutoPorNome(nomeProduto);  // Chama o método no controlador

        if (produtoExistente != null) {
            produtoController.removerProduto(produtoExistente);  // Chama o método de remover produto no controlador
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }



    // Listar produtos
    private void listarProdutos() {
        produtoController.listarProdutos();
    }
}
