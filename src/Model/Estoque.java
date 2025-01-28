package Model;

/**
 * Representa o estoque de um produto no supermercado.
 * Contém informações sobre o produto e a quantidade disponível no estoque.
 */
public class Estoque {
	private Produto produto;
	private int quantidade;

    /**
     * Constrói um estoque para o produto especificado com a quantidade inicial.
     * 
     * @param produto Produto que está sendo armazenado no estoque.
     * @param quantidade Quantidade inicial do produto no estoque.
     */
	public Estoque(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
    /**
     * Adiciona uma quantidade ao estoque do produto.
     * 
     * @param quantidade Quantidade a ser adicionada ao estoque.
     */
	public void adicionarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }
	
    /**
     * Remove uma quantidade do estoque do produto, se houver estoque suficiente.
     * Caso contrário, exibe uma mensagem de erro.
     * 
     * @param quantidade Quantidade a ser removida do estoque.
     */
	public void removerEstoque(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
        } else {
            System.out.println("Estoque insuficiente para remover " + quantidade + " unidades.");
        }
    }

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
    /**
     * Verifica se há quantidade suficiente do produto no estoque.
     * 
     * @param quantidade Quantidade a ser verificada.
     * @return true se houver estoque suficiente, false caso contrário.
     */
	public boolean temEstoque(int quantidade) {
        return this.quantidade >= quantidade;
    }

	@Override
	public String toString() {
		return "Estoque [produto=" + produto + ", quantidade=" + quantidade + "]";
	}
	
	
}
