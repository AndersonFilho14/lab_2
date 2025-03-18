package View;

import Controller.ClienteController;
import Model.Cliente;

import java.util.Scanner;
import java.util.List;

/**
 * Classe responsável pela interação com o usuário e exibição de informações sobre clientes.
 */
public class ClienteView {
    private ClienteController clienteController;
    private Scanner scanner;

    /**
     * Construtor da classe ClienteView.
     * 
     * @param clienteController O controlador de clientes para gerenciar as ações relacionadas a clientes.
     */
    public ClienteView(ClienteController clienteController) {
        this.clienteController = clienteController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Exibe o menu principal para o usuário escolher a ação desejada.
     * O menu continua sendo exibido até que o usuário escolha sair.
     */
    public void exibirMenu() {
    	int opcao = 1;
        do {
            System.out.println("\nMenu de Clientes:");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Consultar Cliente");
            System.out.println("4 - Atualizar Cliente");
            System.out.println("5 - Remover Cliente");
            System.out.println("6 - Adicionar Pontos de Fidelidade");
            System.out.println("7 - Remover Pontos de Fidelidade");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.next(); // Limpar a entrada inválida
                continue; // Voltar para o início do loop
            }
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
                    adicionarPontos();
                    break;
                case 7:
                    removerPontos();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);
    }

/**
 * Realiza o cadastro de um novo cliente.
 * Solicita ao usuário os dados necessários e chama o controlador para realizar o cadastro.
 */
    private void cadastrarCliente() {
        System.out.println("\nCadastro de Cliente");
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha do cliente: ");
        String senha = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o telefone do cliente (opcional): ");
        String telefone = scanner.nextLine();

        try {
            clienteController.cadastrarCliente(nome, email, senha, cpf, telefone);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    /**
     * Lista todos os clientes cadastrados.
     * Exibe as informações dos clientes, incluindo ID e pontos de fidelidade.
     */
    private void listarClientes() {
        System.out.println("\nLista de Clientes:");
        List<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente.getId() + " - " + cliente.getNome() + " - Pontos: " + cliente.getPontosFidelidade());
            }
        }
    }

    /**
     * Consulta um cliente pelo ID.
     * Exibe os dados completos do cliente ou uma mensagem de erro caso o cliente não seja encontrado.
     */
    private void consultarCliente() {
        System.out.print("\nDigite o ID do cliente para consultar: ");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.consultarClientePorId(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("Pontos de Fidelidade: " + cliente.getPontosFidelidade());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    /**
     * Atualiza os dados de um cliente, permitindo que o usuário modifique os campos existentes.
     * Caso algum campo seja deixado em branco, o valor atual será mantido.
     */
    private void atualizarCliente() {
        System.out.print("\nDigite o ID do cliente que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o ID

        Cliente cliente = clienteController.consultarClientePorId(id);
        if (cliente != null) {
            System.out.println("\nAtualização de Cliente (deixe em branco para manter o valor atual):");
            System.out.print("Nome atual (" + cliente.getNome() + "): ");
            String nome = scanner.nextLine();
            nome = nome.isEmpty() ? null : nome; // Define como null se o campo for deixado vazio

            System.out.print("Email atual (" + cliente.getEmail() + "): ");
            String email = scanner.nextLine();
            email = email.isEmpty() ? null : email;

            System.out.print("Senha atual: ");
            String senha = scanner.nextLine();
            senha = senha.isEmpty() ? null : senha;

            System.out.print("CPF atual (" + cliente.getCpf() + "): ");
            String cpf = scanner.nextLine();
            cpf = cpf.isEmpty() ? null : cpf;

            System.out.print("Telefone atual (" + (cliente.getTelefone().isEmpty() ? "Vazio" : cliente.getTelefone()) + "): ");
            String telefone = scanner.nextLine();
            telefone = telefone.isEmpty() ? null : telefone;

            try {
                clienteController.atualizarCliente(id, nome, email, senha, cpf, telefone);
                System.out.println("Cliente atualizado com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }


    /**
     * Remove um cliente com base no ID fornecido.
     * Solicita confirmação antes de realizar a remoção.
     */
    private void removerCliente() {
        System.out.print("\nDigite o ID do cliente que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após o ID

        Cliente cliente = clienteController.consultarClientePorId(id);
        if (cliente != null) {
            System.out.print("Tem certeza de que deseja remover o cliente '" + cliente.getNome() + "'? (S/N): ");
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("S")) {
                clienteController.removerCliente(id);
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }


    /**
     * Remove um cliente com base no ID fornecido.
     * Solicita confirmação antes de realizar a remoção.
     */
    private void adicionarPontos() {
        System.out.print("\nDigite o ID do cliente para adicionar pontos: ");
        int id = scanner.nextInt();
        System.out.print("Digite a quantidade de pontos a adicionar: ");
        int pontos = scanner.nextInt();

        try {
            clienteController.adicionarPontosFidelidade(id, pontos);
            System.out.println("Pontos adicionados com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar pontos: " + e.getMessage());
        }
    }

    /**
     * Remove pontos de fidelidade de um cliente.
     * Solicita o ID do cliente e a quantidade de pontos a ser removida.
     */
    private void removerPontos() {
        System.out.print("\nDigite o ID do cliente para remover pontos: ");
        int id = scanner.nextInt();
        System.out.print("Digite a quantidade de pontos a remover: ");
        int pontos = scanner.nextInt();

        try {
            clienteController.removerPontosFidelidade(id, pontos);
            System.out.println("Pontos removidos com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao remover pontos: " + e.getMessage());
        }
    }
}
