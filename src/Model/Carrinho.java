package Model;

import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    private Map<Produto, Integer> itens = new HashMap<>();

    public void adicionarProduto(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        itens.put(produto, itens.getOrDefault(produto, 0) + quantidade);
    }

    public void removerProduto(Produto produto) {
        itens.remove(produto);
    }

    public void atualizarQuantidade(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            removerProduto(produto);
        } else {
            itens.put(produto, quantidade);
        }
    }

    public Map<Produto, Integer> getItens() {
        return new HashMap<>(itens); // Retorna uma cópia para evitar alterações externas
    }

    public double calcularTotal() {
        return itens.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPreco() * entry.getValue())
                .sum();
    }
}
