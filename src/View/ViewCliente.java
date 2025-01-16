package View;

import Controller.ClienteController;
import Model.Cliente;
import Model.ClienteFidelidade;

import java.util.Scanner;
import java.util.List;

public class ViewCliente {
    private ClienteController clienteController;
    private Scanner scanner;

    public ViewCliente(ClienteController clienteController) {
        this.clienteController = clienteController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\nMenu de Clientes:");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Consultar Cliente");
            System.out.println("4 - Atualizar Cliente");
            System.out.println("5 - Remover Cliente");
            System.out.println("6 - Cadastrar Cliente Fidelidade");
            System.out.println("7 - Listar Clientes Fidelidade");
            System.out.println("8 - Consultar Cliente Fidelidade");
            System.out.println("9 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a escolha

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    consultarCliente();
                    break;
                case 4:
                    atualizarCliente();
                    break;
                case 5:
                    removerCliente();
                    break;
                case 6:
                    cadastrarClienteFidelidade();
                    break;
                case 7:
                    listarClientesFidelidade();
                    break;
                case 8:
                    consultarClienteFidelidade();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 9);
    }

    private void cadastrarCliente() {
        System.out.println("\nCadastro de Cliente");
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();
        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();

        try {
            Cliente cliente = new Cliente(id, nome, cpf, email, telefone);
            clienteController.cadastrarCliente(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private void listarClientes() {
        System.out.println("\nLista de Clientes:");
        System.out.println("Id  -  Nome");
        List<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente.getId() + " - " + cliente.getNome());
            }
        }
    }

    private void consultarCliente() {
        System.out.print("\nDigite o ID do cliente para consultar: ");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.consultarClientePorId(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.getNome() + " - " + cliente.getCpf());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void atualizarCliente() {
        System.out.print("\nDigite o ID do cliente a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        Cliente cliente = clienteController.consultarClientePorId(id);
        if (cliente != null) {
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Novo email: ");
            String email = scanner.nextLine();
            System.out.print("Novo telefone: ");
            String telefone = scanner.nextLine();

            try {
                clienteController.atualizarCliente(id, nome, cpf, email, telefone);
                System.out.println("Cliente atualizado com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void removerCliente() {
        System.out.print("\nDigite o ID do cliente a ser removido: ");
        int id = scanner.nextInt();
        try {
            clienteController.removerCliente(id);
            System.out.println("Cliente removido com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
        }
    }

    private void cadastrarClienteFidelidade() {
        System.out.println("\nCadastro de Cliente Fidelidade");
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        System.out.print("Digite os pontos acumulados: ");
        int pontosAcumulados = scanner.nextInt();

        try {
            ClienteFidelidade clienteFidelidade = new ClienteFidelidade(id, pontosAcumulados, clienteController);
            clienteController.cadastrarClienteFidelidade(clienteFidelidade);
            System.out.println("Cliente fidelidade cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar cliente fidelidade: " + e.getMessage());
        }
    }


    private void listarClientesFidelidade() {
        System.out.println("\nLista de Clientes Fidelidade:");
        List<ClienteFidelidade> clientesFidelidade = clienteController.listarClientesFidelidade();
        if (clientesFidelidade.isEmpty()) {
            System.out.println("Nenhum cliente fidelidade cadastrado.");
        } else {
            for (ClienteFidelidade clienteFidelidade : clientesFidelidade) {
            	System.out.println("Id   -  Pontos Acumulados");
                System.out.println(clienteFidelidade.getClienteId() + " - " + clienteFidelidade.getPontosAcumulados());
            }
        }
    }

    private void consultarClienteFidelidade() {
        System.out.print("\nDigite o ID do cliente fidelidade para consultar: ");
        int id = scanner.nextInt();
        ClienteFidelidade clienteFidelidade = clienteController.consultarClienteFidelidadePorId(id);
        if (clienteFidelidade != null) {
            System.out.println("Cliente fidelidade encontrado:  id  " + clienteFidelidade.getClienteId() + " -  Pontos acumulados  " + clienteFidelidade.getPontosAcumulados());
        } else {
            System.out.println("Cliente fidelidade não encontrado.");
        }
    }
}
