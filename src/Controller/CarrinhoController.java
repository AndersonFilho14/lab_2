package Controller;

import java.util.Map;
import java.util.Scanner;

import Model.Carrinho;
import Model.Cliente;
import Model.Produto;

public class CarrinhoController {
    private Carrinho carrinho;
    private ProdutoController produtoController;
    private FinanceiroController financeiroController;
    private Scanner scanner; 
    

    public CarrinhoController(Carrinho carrinho, ProdutoController produtoController) {
        this.carrinho = carrinho;
        this.produtoController = produtoController;
        this.financeiroController = new FinanceiroController();
        this.scanner = new Scanner(System.in);
    }

    
    public String adicionarProduto(int id, int quantidade) {
        Produto produto = produtoController.buscarProdutoPorId(id);
        if (produto == null) {
            return "Produto não encontrado!";
        }
        if (quantidade <= 0) {
            return "Quantidade deve ser maior que zero.";
        }
        if (produto.getQuantidade() < quantidade) {
            return "Quantidade indisponível no estoque.";
        }
        carrinho.adicionarProduto(produto, quantidade);
        return "Produto adicionado ao carrinho!";
    }

    
    public String removerProduto(int id) {
        Produto produtoRemover = null;
        for (Produto p : carrinho.getItens().keySet()) {
            if (p.getId() == id) {
                produtoRemover = p;
                break;
            }
        }
        if (produtoRemover == null) {
            return "Produto não encontrado no carrinho.";
        }
        carrinho.removerProduto(produtoRemover);
        return "Produto removido do carrinho!";
    }

    
    public String atualizarQuantidade(int id, int quantidade) {
        Produto produto = produtoController.buscarProdutoPorId(id);
        if (produto == null || !carrinho.getItens().containsKey(produto)) {
            return "Produto não encontrado no carrinho.";
        }
        if (quantidade <= 0) {
            carrinho.removerProduto(produto);
            return "Produto removido do carrinho!";
        }
        if (produto.getQuantidade() < quantidade) {
            return "Quantidade indisponível no estoque.";
        }
        carrinho.atualizarQuantidade(produto, quantidade);
        return "Quantidade do produto atualizada!";
    }
    

    public Map<Produto, Integer> getItens() {
        return carrinho.getItens();
    }

    
    public double calcularTotal() {
        return carrinho.calcularTotal();
    }
    
    public String finalizarCompra(int idCliente) {
        double total = calcularTotal();

        if (carrinho.getItens().isEmpty()) {
            return "O carrinho está vazio. Adicione produtos antes de finalizar a compra.";
        }
        if (total <= 0) {
            return "Erro ao calcular total da compra.";
        }
        
        ClienteController clienteController = new ClienteController();
        Cliente cliente = clienteController.consultarClientePorId(idCliente);
        System.out.println("pontos fidelidade  " + cliente.getPontosFidelidade() + "  Nome = " + cliente.getNome() + "  id  " + cliente.getId());

        if (cliente.getId() != 1 && cliente.getPontosFidelidade() > 0) {
            System.out.println("\nDeseja usar seus pontos fidelidade? Você tem " 
                + cliente.getPontosFidelidade() + " pontos disponíveis.");
            System.out.println("Digite ( S ) para confirmar ou qualquer outra coisa para não usar os pontos.");

            // Removemos o try-with-resources para evitar fechar o scanner
            boolean usarPontos = false;
            String resposta = scanner.nextLine().trim().toUpperCase();  // Usa o scanner já criado na classe
            if (resposta.equals("S")) {
                usarPontos = true;
            }

            if (usarPontos) {
                if (cliente.getPontosFidelidade() < total) {
                    total -= cliente.getPontosFidelidade();
                    clienteController.removerPontosFidelidade(idCliente, cliente.getPontosFidelidade());
                    System.out.println("O valor total da compra agora é " + total + ". Você usou " + cliente.getPontosFidelidade() + " pontos.");
                } else {
                    int pontosTotal = (int) Math.floor(total);  // Limita os pontos ao valor do total
                    clienteController.removerPontosFidelidade(idCliente, pontosTotal);
                    total = 0;
                    System.out.println("Boa, você juntou pontos suficientes para sua compra sair grátis!");
                }
            }
        }

        boolean sucesso = financeiroController.registrarCompra(total, idCliente);
        if (!sucesso) {
            return "Erro ao registrar compra no financeiro.";
        }

        for (Map.Entry<Produto, Integer> entry : carrinho.getItens().entrySet()) {
            Produto produto = entry.getKey();
            int quantidadeComprada = entry.getValue();
            produtoController.comprarProduto(produto.getId(), quantidadeComprada);
        }
        carrinho.getItens().clear();

        if (cliente.getId() != 1) {
            int pontosFidelidade = (int) Math.floor(total * 0.01); 
            clienteController.adicionarPontosFidelidade(cliente.getId(), pontosFidelidade);
        }

        return "Compra finalizada com sucesso!";
    }
    
    
}
