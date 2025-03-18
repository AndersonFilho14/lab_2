package View;

import Controller.ClienteController;
import Controller.FinanceiroController;
import Controller.FuncionarioController;
import Controller.ProdutoController;
import Model.Funcionario;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Scanner;

public class FuncionarioView {
    private Scanner scanner;
    private FinanceiroController financeiroController;
    private FuncionarioController funcionarioController;

    public FuncionarioView(FuncionarioController funcionarioController) {
        this.funcionarioController = funcionarioController;
        this.financeiroController = new FinanceiroController();
        this.scanner = new Scanner(System.in);
    }

    // ===== MENU GERENTE =====
    public void exibirMenuGerente(Funcionario funcionario) {
        int opcao = 1 ;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIOS Peão PLus (Gerente) ---");
            System.out.println("1 - Adicionar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Atualizar Funcionário");
            System.out.println("4 - Demitir / Recontratar Funcionário");
            System.out.println("5 - Promover / Despromover Funcionário");
            System.out.println("6 - Verificar trabalho de funcionário por id");
            System.out.println("7 - Menu de gerente para Produtos");
            System.out.println("8 - Menu de gerente para CLientes");
            System.out.println("9 - Relatório geral");
            System.out.println("10 - Gerar Relatório personalizado");
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
//                	Adicionar Funcionário
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
                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida! O cargo deve ser um número.");
                        scanner.next(); // limpa a entrada inválida
                        break;
                    }
                    
                    int cargo = scanner.nextInt();
                    scanner.nextLine(); 

                    funcionarioController.adicionarFuncionario(nome, email, senha, cpf, telefone, cargo);
                    break;

                case 2:
                    // Listar Funcionários
                    List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
                    if (funcionarios == null || funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                    	System.out.println("");
                        for (Funcionario f : funcionarios) {
                            System.out.println("ID: " + f.getId() 
                            		+	" | Nome: " + f.getNome() 
                                    + " | Email: " + f.getEmail() 
                                    + " | Cargo: " + f.getCargo() 
                                    + " | Empregado: " + f.getEmpregado());
                        }
                    }
                    break;

                case 3:
                	// Atualizar Funcionário
                	System.out.println("Caso não queira atualizar valor, basta aperta enter");
                    System.out.print("ID do funcionário para atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    Funcionario funcionarioAtual = funcionarioController.buscarFuncionarioPorId(idAtualizar);
                    
                    if (funcionarioAtual == null) {
                        System.out.println("Funcionário não encontrado.");
                        break;
                    }

                    System.out.print("Novo nome (" + funcionarioAtual.getNome() + "): ");
                    String novoNome = scanner.nextLine();
                    if (novoNome.isEmpty()) novoNome = funcionarioAtual.getNome();

                    System.out.print("Novo email (" + funcionarioAtual.getEmail() + "): ");
                    String novoEmail = scanner.nextLine();
                    if (novoEmail.isEmpty()) novoEmail = funcionarioAtual.getEmail();

                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();
                    if (novaSenha.isEmpty()) novaSenha = funcionarioAtual.getSenha();

                    System.out.print("Novo CPF (" + funcionarioAtual.getCpf() + "): ");
                    String novoCpf = scanner.nextLine();
                    if (novoCpf.isEmpty()) novoCpf = funcionarioAtual.getCpf();

                    System.out.print("Novo telefone (" + funcionarioAtual.getTelefone() + "): ");
                    String novoTelefone = scanner.nextLine();
                    if (novoTelefone.isEmpty()) novoTelefone = funcionarioAtual.getTelefone();

                    System.out.print("Novo cargo (" + funcionarioAtual.getCargo() + "): ");
                    String cargoInput = scanner.nextLine();
                    int novoCargo = cargoInput.isEmpty() ? funcionarioAtual.getCargo() : Integer.parseInt(cargoInput);

                    System.out.print("Está empregado? (" + funcionarioAtual.getEmpregado() + "): ");
                    String empregadoInput = scanner.nextLine();
                    boolean empregado = empregadoInput.isEmpty() ? funcionarioAtual.getEmpregado() : Boolean.parseBoolean(empregadoInput);

                    funcionarioController.atualizarFuncionario(idAtualizar, novoNome, novoEmail, novaSenha, novoCpf, novoTelefone, novoCargo, empregado);
                    break;

                case 4:
                    // demitir e recontratar funcionário
                    System.out.print("Digite o nome do funcionário: ");
                    String nomeFunc = scanner.nextLine();
                    System.out.print("Digite 1 para demitir ou 2 para recontratar: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida! Digite 1 ou 2.");
                        scanner.next(); 
                        break;
                    }
                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    if (escolha == 1) {
                        funcionarioController.demitirFuncionario(nomeFunc);
                    } else if (escolha == 2) {
                        funcionarioController.empregarFuncionario(nomeFunc);
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 5:
                    // Promover despromover funcionário
                    System.out.print("Digite o nome do funcionário: ");
                    String nomePromocao = scanner.nextLine();
                    System.out.print("Digite o novo cargo (número): ");
                    int novoCargoPromocao = scanner.nextInt();
                    scanner.nextLine(); 

                    funcionarioController.mudarCargoFuncionarioNome(nomePromocao, novoCargoPromocao);
                    break;

                case 6:
                	System.out.print("Digite o id do funcionario que vc quer visualizar: ");
                    int idFuncionarioVerificar = scanner.nextInt();
                    funcionarioController.getProdutosAdicionadosPeloFuncionario_id(idFuncionarioVerificar);
                    break;
                
                case 7:
                	ProdutoController produtoController = new ProdutoController();
                	ProdutoView produtoView = new ProdutoView(produtoController);
                	produtoView.exibirMenuGerente(funcionario);
                    break;
                
                
                case 8:
                	ClienteController clienteController = new ClienteController();
                	ClienteView clienteView = new ClienteView(clienteController);
                	clienteView.exibirMenu();
                	break;
                
                case 9:
                	financeiroController.listarCompras();
                	break;
                
                case 10:
                	System.out.println("Digite o número de dias para o relatório (apenas valores positivos):");

                    if (!scanner.hasNextInt()) { 
                        System.out.println("Entrada inválida! Digite um número inteiro positivo.");
                        scanner.next(); 
                        break; 
                    }
                    
                    int dias = scanner.nextInt();
                    if (dias <= 0) { 
                        System.out.println("Por favor, insira um número maior que zero.");
                        break;
                    }
                    financeiroController.listarComprasUltimosDias(dias);
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
    public void exibirMenuFuncionario(Funcionario funcionario) {
    	int opcao = 1 ;
        do {
            System.out.println("\n--- MENU FUNCIONÁRIOS Peão (Funcionário) ---");
            System.out.println("1 - Trabalhar"); 
            System.out.println("2 - Listar Produtos que eu adicionei"); 
            System.out.println("3 - Atualizar Informações");
            System.out.println("4 - Me demitir");
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
                	ProdutoController produtoController = new ProdutoController();
                	ProdutoView produtoView = new ProdutoView(produtoController);
                	produtoView.exibirMenuFuncionario(funcionario);
                    break;

                case 2:
                    this.funcionarioController.getProdutosAdicionadosPeloFuncionario_id(funcionario.getId());
                    break;

                case 3:
                    // Atualizar Informações
                    System.out.print("Novo nome (ou pressione Enter para manter o atual): ");
                    String novoNome = scanner.nextLine();
                    if (novoNome.isEmpty()) {
                        novoNome = funcionario.getNome();
                    }

                    System.out.print("Novo email (ou pressione Enter para manter o atual): ");
                    String novoEmail = scanner.nextLine();
                    if (novoEmail.isEmpty()) {
                        novoEmail = funcionario.getEmail();
                    }

                    System.out.print("Nova senha (ou pressione Enter para manter a atual): ");
                    String novaSenha = scanner.nextLine();
                    if (novaSenha.isEmpty()) {
                        novaSenha = funcionario.getSenha();
                    }

                    System.out.print("Novo CPF (ou pressione Enter para manter o atual): ");
                    String novoCpf = scanner.nextLine();
                    if (novoCpf.isEmpty()) {
                        novoCpf = funcionario.getCpf();
                    }

                    System.out.print("Novo telefone (ou pressione Enter para manter o atual): ");
                    String novoTelefone = scanner.nextLine();
                    if (novoTelefone.isEmpty()) {
                        novoTelefone = funcionario.getTelefone();
                    }
                    funcionarioController.atualizarFuncionario(funcionario.getId(), novoNome, novoEmail, novaSenha, novoCpf, novoTelefone, funcionario.getCargo(), funcionario.getEmpregado());
                    break;

                case 4:
                    System.out.print("Tem certeza que deseja se demitir? (S/N): ");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("S")) {
                        System.out.print("Digite seu nome para confirmar: ");
                        String nomeConfirmacao = scanner.nextLine();
	                        if (nomeConfirmacao.equals(funcionario.getNome())) {
	                        	funcionarioController.demitirFuncionario(nomeConfirmacao);
	                        	System.out.println("Você foi demitido.");
	                        }else {
	                        	System.out.println("Nome não bate com  " + funcionario.getNome());}
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
