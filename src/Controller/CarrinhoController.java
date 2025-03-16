package Controller;

import java.util.Map;
import Model.Carrinho;
import Model.Produto;

public class CarrinhoController {
    private Carrinho carrinho;
    private ProdutoController produtoController;
    private FinanceiroController financeiroController;

    public CarrinhoController(Carrinho carrinho, ProdutoController produtoController) {
        this.carrinho = carrinho;
        this.produtoController = produtoController;
        this.financeiroController = new FinanceiroController();
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
        boolean sucesso = financeiroController.registrarCompra(total, idCliente);
        if (!sucesso) {
            return "Erro ao registrar compra no financeiro.";
        }
        //  produtos comprados
        for (Map.Entry<Produto, Integer> entry : carrinho.getItens().entrySet()) {
            Produto produto = entry.getKey();
            int quantidadeComprada = entry.getValue();
            produtoController.comprarProduto(produto.getId(), quantidadeComprada);
        }
        carrinho.getItens().clear();
        return "Compra finalizada com sucesso!";
    }
    
    
    
}
