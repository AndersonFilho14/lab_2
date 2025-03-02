package View;

import Controller.FuncionarioController;
import Model.Funcionario;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FuncionarioView {
    private Scanner scanner;
    private FuncionarioController funcionarioController;

    public FuncionarioView(FuncionarioController funcionarioController) {
        this.funcionarioController = funcionarioController;
        this.scanner = new Scanner(System.in);
    }

  public void exibirMenu() {
        while (true) {
            System.out.println("\n--- MENU FUNCIONÁRIOS ---");
            System.out.println("1 - Adicionar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Atualizar Funcionário");
            System.out.println("4 - Deletar Funcionário");
            System.out.println("5 - Login de Funcionário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
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
                    scanner.nextLine(); 

                    funcionarioController.adicionarFuncionario(nome, email, senha, cpf, telefone, cargo);
                    break;

                case 2:
                    List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
                    if (funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        for (Funcionario f : funcionarios) {
                            System.out.println(f.getNome() + " | " + f.getEmail() + " | Cargo: " + f.getCargo() + " | Empregado: " + f.getEmpregado());
                        }
                    }
                    break;

                case 3:
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

                    funcionarioController.atualizarFuncionario(idAtualizar, novoNome, novoEmail, novaSenha, novoCpf, novoTelefone, novoCargo, empregado);
                    break;

                case 4:
                    System.out.print("ID do funcionário para remover: ");
                    int idRemover = scanner.nextInt();
                    funcionarioController.deletarFuncionario(idRemover);
                    break;

                case 5:
                    System.out.print("Email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Senha: ");
                    String loginSenha = scanner.nextLine();

                    Funcionario funcionarioLogado = funcionarioController.loginFuncionario(loginEmail, loginSenha);
                    if (funcionarioLogado != null) {
                        System.out.println("Login bem-sucedido! Bem-vindo, " + funcionarioLogado.getNome());
                    } else {
                        System.out.println("Email ou senha inválidos.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}