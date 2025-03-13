package Controller;

import java.util.Map;

import Model.Carrinho;
import Model.Produto;

public class CarrinhoController {
    private Carrinho carrinho;

    public CarrinhoController(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        carrinho.adicionarProduto(produto, quantidade);
    }

    public void removerProduto(Produto produto) {
        carrinho.removerProduto(produto);
    }

    public void atualizarQuantidade(Produto produto, int quantidade) {
        carrinho.atualizarQuantidade(produto, quantidade);
    }

    public Map<Produto, Integer> getItens() {
        return carrinho.getItens();
    }

    public double calcularTotal() {
        return carrinho.calcularTotal();
    }
}