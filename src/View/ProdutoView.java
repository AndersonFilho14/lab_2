package View;

import Controller.ProdutoController;
import Model.Categoria;
import Model.Funcionario;
import Model.Produto;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ProdutoView {
    private ProdutoController produtoController;
    private Scanner scanner;

    public ProdutoView(ProdutoController produtoController) {
        this.produtoController = produtoController;
        this.scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
    }

    public void exibirMenuGerente(Funcionario funcionario) {
        int opcao= 1;
        do {
            System.out.println("\nMenu de Produtos Gerente:");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Editar Produto");
            System.out.println("3 - Remover Produto");
            System.out.println("4 - Listar Produtos");
            System.out.println("5 - Colocar na Prateleira");
            System.out.println("6 - Retirar da Prateleira");
            System.out.println("7 - Verificar Produtos Próximos da Validade");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.next(); 
                continue; 
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto(funcionario);
                    break;
                case 2:
                    editarProduto();
                    break;
                case 3:
                    removerProduto();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    colocarNaPrateleira();
                    break;
                case 6:
                    retirarDaPrateleira();
                    break;
                case 7:
                    verificarProdutosProximosValidade();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public void exibirMenuFuncionario(Funcionario funcionario) {
        int opcao = 1;
        do {
            System.out.println("\nMenu de Produtos Funcionario:");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Editar Produto");
            System.out.println("3 - Listar Produtos");
            System.out.println("4 - Colocar na Prateleira");
            System.out.println("5 - Retirar da Prateleira");
            System.out.println("6 - Verificar Produtos Próximos da Validade");
            System.out.println("7 - Colocar mais produtos no estoque");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.next(); 
                continue; 
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto(funcionario);
                    break;
                case 2:
                    editarProduto();
                    break;
                case 3:
                	listarProdutos();
                    break;
                case 4:
                	colocarNaPrateleira();
                    break;
                case 5:
                	retirarDaPrateleira();
                    break;
                case 6:
                	verificarProdutosProximosValidade();
                    break;
                case 7:
                	aumentarQuantidadeProduto();
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while ( opcao != 0);
    }

    
    
    private void cadastrarProduto(Funcionario funcionario) {
        System.out.println("\nCadastro de Produto:");

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a validade do produto (yyyy-MM-dd): ");
        String validade = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        float preco = scanner.nextFloat();
        scanner.nextLine(); 

        System.out.print("Escolha a categoria (1- COMIDA, 2- LIMPEZA, 3- MATERIAL): ");
        int categoriaEscolhida = scanner.nextInt();
        scanner.nextLine(); 
        Categoria categoria = obterCategoria(categoriaEscolhida);

        System.out.print("Digite a quantidade inicial do produto: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        produtoController.adicionarProduto(nome, validade, preco, categoria, quantidade, funcionario.getId()); 
    }

    private Categoria obterCategoria(int categoriaEscolhida) {
        switch (categoriaEscolhida) {
            case 1: return Categoria.COMIDA;
            case 2: return Categoria.LIMPEZA;
            case 3: return Categoria.MATERIAL;
            default: return null; 
        }
    }

    private void editarProduto() {
        System.out.print("Digite o nome do produto a ser editado: ");
        String nomeProduto = scanner.nextLine();
        Produto produtoExistente = produtoController.buscarProdutoPorNome(nomeProduto);
        
        if (produtoExistente != null) {
            System.out.println("\nEditando Produto: "+ produtoExistente.getNome());
            System.out.print("Digite o novo nome do produto (ou pressione Enter para manter o atual): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                produtoExistente.setNome(nome);
            }
            System.out.print("Digite a nova validade do produto (yyyy-MM-dd) (ou pressione Enter para manter a atual): ");
            String validade = scanner.nextLine();
            if (!validade.isEmpty()) {
                produtoExistente.setValidade(validade);
            }

            System.out.print("Digite o novo preço do produto (ou 0 para manter o atual): ");
            float preco = scanner.nextFloat();
            scanner.nextLine();
            if (preco > 0) {
                produtoExistente.setPreco(preco);
            }

            System.out.print("Digite a nova quantidade do produto (ou 0 para manter a atual): ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); 
            if (quantidade > 0) {
                produtoExistente.setQuantidade(quantidade);
            }

            produtoController.atualizarProduto(produtoExistente);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
    
    
    private void aumentarQuantidadeProduto() {
        System.out.print("Digite o id do produto para aumentar a quantidade no estoque: ");
        int id = scanner.nextInt();
        Produto produtoExistente = produtoController.buscarProdutoPorId(id);

        if (produtoExistente != null) {
            System.out.println("\nProduto encontrado: " + produtoExistente.getNome());
            System.out.print("Digite a quantidade a ser adicionada ao estoque: ");
            int quantidadeAAdicionar = scanner.nextInt();
            scanner.nextLine();

            if (quantidadeAAdicionar > 0) {
                int novaQuantidade = produtoExistente.getQuantidade() + quantidadeAAdicionar;
                produtoExistente.setQuantidade(novaQuantidade);
                produtoController.atualizarProduto(produtoExistente);
                System.out.println("Quantidade aumentada com sucesso! Novo estoque: " + novaQuantidade);
            } else {
                System.out.println("A quantidade a ser adicionada deve ser maior que zero.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
    
    
    private void removerProduto() {
        System.out.print("Digite o nome do produto a ser removido: ");
        String nomeProduto = scanner.nextLine();
        Produto produtoExistente = produtoController.buscarProdutoPorNome(nomeProduto);

        if (produtoExistente != null) {
            produtoController.removerProduto(produtoExistente.getId());
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = produtoController.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    private void colocarNaPrateleira() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        produtoController.colocarNaPrateleira(id);
    }

    private void retirarDaPrateleira() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        produtoController.retirarDaPrateleira(id);
    }

    private void verificarProdutosProximosValidade() {
        System.out.print("Digite o número de dias para verificar a validade: ");
        int dias = scanner.nextInt();
        scanner.nextLine(); 

        List<Produto> produtos = produtoController.produtosComValidadeProxima(dias);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto próximo da validade.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }
}