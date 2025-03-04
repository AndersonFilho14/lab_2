package View;

import Controller.ClienteController;
import Controller.FuncionarioController;
import Model.Cliente;
import Model.Funcionario;

import java.util.Scanner;

public class LoginView {
    private ClienteController clienteController;
    private FuncionarioController funcionarioController;
    private Scanner scanner;
    private FuncionarioView funcionarioView;

    // Inicialização direta sem precisar passar pelo construtor
    public LoginView() {
        this.clienteController = new ClienteController();
        this.funcionarioController = new FuncionarioController();
        this.scanner = new Scanner(System.in);
        this.funcionarioView = new FuncionarioView(funcionarioController);
    }

    /**
     * Exibe o menu de login com opções para o usuário escolher.
     * O menu permite que o usuário acesse como cliente cadastrado, 
     * cliente sem cadastro, crie um novo login ou acesse como funcionário.
     */
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\nTela de Login:");
            System.out.println("1 - Entrar como cliente cadastrado");
            System.out.println("2 - Entrar como cliente sem cadastro");
            System.out.println("3 - Criar login");
            System.out.println("4 - Acessar como Funcionário");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a escolha

            switch (opcao) {
                case 1:
                    entrarComoClienteCadastrado();
                    break;
                case 2:
                    entrarComoClienteSemCadastro();
                    break;
                case 3:
                    criarLogin();
                    break;
                case 4:
                    acessarComoFuncionario();
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
     * Processa o login de um cliente cadastrado.
     * Solicita e-mail e senha, e valida as credenciais.
     */
    private void entrarComoClienteCadastrado() {
        System.out.print("\nDigite o e-mail do cliente: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha do cliente: ");
        String senha = scanner.nextLine();

        Cliente cliente = clienteController.verificarLogin(email, senha);
        if (cliente != null) {
            System.out.println("Bem-vindo, " + cliente.getNome() + "!");
            //tela de compras futura	
        } else {
            System.out.println("E-mail ou senha incorretos.");
        }
    }


    /**
     * Futuro processo de login para um cliente sem cadastro, direcionando-o para a tela de cadastro.
     */
    private void entrarComoClienteSemCadastro() {
        System.out.println("\nVocê será redirecionado para a tela de compras...");
        // vai direto as compras
    }

    /**
     * Realiza o processo de criação de um novo login para o cliente.
     * Solicita informações como nome, e-mail, senha, CPF e telefone.
     */
    private void criarLogin() {
        System.out.println("\nCadastro de Novo Cliente:");
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o e-mail do cliente: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha do cliente: ");
        String senha = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o telefone do cliente (opcional): ");
        String telefone = scanner.nextLine();

        try {
            clienteController.cadastrarCliente(nome, email, senha, cpf, telefone);
            System.out.println("Cadastro realizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    /**
     * Processa o login de um funcionário.
     * Solicita o ID e a senha do funcionário para validação.
     */
    private void acessarComoFuncionario() {
        System.out.println("\nAcesso de Funcionário:");
        System.out.print("Digite o email do funcionário: ");
        String emailFuncionario = scanner.nextLine();
        System.out.print("Digite a senha do funcionário: ");
        String senhaFuncionario = scanner.nextLine();
        Funcionario funcionario = funcionarioController.loginFuncionario(emailFuncionario, senhaFuncionario);
        if (funcionario != null) {
            if (funcionario.getId() == 1) {
                System.out.println("Bem-vindo, Funcionário " + funcionario.getNome() + " .");
                funcionarioView.exibirMenuFuncionario();
            } else {
                System.out.println("Bem-vindo, Gerente " + funcionario.getNome() + " .");
                funcionarioView.exibirMenuGerente();
            }
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }
}
