package Model;

import java.math.BigDecimal;

/**
 * Representa um item dentro de um pedido, contendo informações sobre o produto, quantidade e preço unitário.
 */
public class ItemPedido {
	private Produto produto;
    private int quantidade;
    private double precoUnitario;
/**
     * Construtor para criar um item de pedido.
     * 
     * @param produto O produto do item.
     * @param quantidade A quantidade do produto no pedido.
     * @param precoUnitario O preço unitário do produto.
     */
    public ItemPedido(Produto produto, int quantidade, double precoUnitario) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        if (precoUnitario <= 0) throw new IllegalArgumentException("Preço unitário deve ser maior que zero.");
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
     * Calcula o subtotal do item.
     * 
     * @return O valor total do item (quantidade * preço unitário).
     */

    public double calcularSubtotal() {
        return precoUnitario * quantidade;
    }
}