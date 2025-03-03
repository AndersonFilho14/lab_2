package View;

import Controller.FuncionarioController;
import Model.Funcionario;

import java.util.List;
import java.util.Scanner;

public class FuncionarioView {
    private Scanner scanner;
    private FuncionarioController funcionarioController;

    public FuncionarioView(FuncionarioController funcionarioController) {
        this.funcionarioController = funcionarioController;
        this.scanner = new Scanner(System.in);
    }

    // ===== MENU GERENTE =====
    public void exibirMenuGerente() {
        int opcao;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIOS Peão PLus (Gerente) ---");
            System.out.println("1 - Adicionar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Atualizar Funcionário");
            System.out.println("4 - Demitir / Recontratar Funcionário");
            System.out.println("5 - Promover / Despromover Funcionário");
            System.out.println("6 - Verificar trabalho de funcionário por nome"); // Funcionalidade a implementar
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    // Adicionar Funcionário
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Cargo (número): ");
                    int cargo = scanner.nextInt();
                    scanner.nextLine(); // Consumir quebra de linha

                    funcionarioController.adicionarFuncionario(nome, email, senha, cpf, telefone, cargo);
                    break;

                case 2:
                    // Listar Funcionários
                    List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
                    if (funcionarios == null || funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        for (Funcionario f : funcionarios) {
                            System.out.println("Nome: " + f.getNome() 
                                    + " | Email: " + f.getEmail() 
                                    + " | Cargo: " + f.getCargo() 
                                    + " | Empregado: " + f.getEmpregado());
                        }
                    }
                    break;

                case 3:
                    // Atualizar Funcionário
                    System.out.print("ID do funcionário para atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();
                    System.out.print("Novo CPF: ");
                    String novoCpf = scanner.nextLine();
                    System.out.print("Novo telefone: ");
                    String novoTelefone = scanner.nextLine();
                    System.out.print("Novo cargo (número): ");
                    int novoCargo = scanner.nextInt();
                    System.out.print("Está empregado? (true/false): ");
                    boolean empregado = scanner.nextBoolean();
                    scanner.nextLine(); // Consumir quebra de linha

                    funcionarioController.atualizarFuncionario(idAtualizar, novoNome, novoEmail, novaSenha, novoCpf, novoTelefone, novoCargo, empregado);
                    break;

                case 4:
                    // Demitir / Recontratar Funcionário
                    System.out.print("Digite o nome do funcionário: ");
                    String nomeFunc = scanner.nextLine();
                    System.out.print("Digite 1 para demitir ou 2 para recontratar: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine(); // Consumir quebra de linha

                    if (escolha == 1) {
                        funcionarioController.demitirFuncionario(nomeFunc);
                    } else if (escolha == 2) {
                        funcionarioController.empregarFuncionario(nomeFunc);
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 5:
                    // Promover / Despromover Funcionário
                    System.out.print("Digite o nome do funcionário: ");
                    String nomePromocao = scanner.nextLine();
                    System.out.print("Digite o novo cargo (número): ");
                    int novoCargoPromocao = scanner.nextInt();
                    scanner.nextLine(); // Consumir quebra de linha

                    funcionarioController.mudarCargoFuncionarioNome(nomePromocao, novoCargoPromocao);
                    break;

                case 6:
                    // Verificar trabalho de funcionário por nome (a implementar)
                    System.out.println("Funcionalidade não implementada.");
                    break;

                case 0:
                    System.out.println("Saindo do menu de gerente...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    // ===== MENU FUNCIONÁRIO =====
    public void exibirMenuFuncionario() {
        int opcao;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIOS Peão (Funcionário) ---");
            System.out.println("1 - Trabalhar"); // Funcionalidade a implementar
            System.out.println("2 - Listar Produtos que eu adicionei"); // Funcionalidade a implementar
            System.out.println("3 - Atualizar Informações");
            System.out.println("4 - Me demitir");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.println("Funcionalidade 'Trabalhar' não implementada.");
                    break;

                case 2:
                    System.out.println("Funcionalidade 'Listar Produtos que eu adicionei' não implementada.");
                    break;

                case 3:
                    // Atualizar Informações
                    System.out.print("ID do funcionário para atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();
                    System.out.print("Novo CPF: ");
                    String novoCpf = scanner.nextLine();
                    System.out.print("Novo telefone: ");
                    String novoTelefone = scanner.nextLine();
                    System.out.print("Novo cargo (número): ");
                    int novoCargo = scanner.nextInt();
                    System.out.print("Está empregado? (true/false): ");
                    boolean empregado = scanner.nextBoolean();
                    scanner.nextLine(); // Consumir quebra de linha

                    funcionarioController.atualizarFuncionario(idAtualizar, novoNome, novoEmail, novaSenha, novoCpf, novoTelefone, novoCargo, empregado);
                    break;

                case 4:
                    // Me demitir (auto-demissão)
                    System.out.print("Tem certeza que deseja se demitir? (S/N): ");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("S")) {
                        System.out.print("Digite seu nome para confirmar: ");
                        String nomeConfirmacao = scanner.nextLine();
                        funcionarioController.demitirFuncionario(nomeConfirmacao);
                        System.out.println("Você foi demitido.");
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do menu de funcionário...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }
}
