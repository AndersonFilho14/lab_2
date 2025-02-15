package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representa um produto no supermercado.
 * Contém informações sobre nome, validade, preço, categoria e estoque.
 */
public class Produto {
    private String nome;
    private LocalDate validade;
    private float preco;
    private Categoria categoria;
    private int quantidade; // Adicionada para gerenciar o estoque

    /**
     * Constrói um novo produto com as informações fornecidas.
     * 
     * @param nome Nome do produto.
     * @param validade Data de validade do produto no formato "yyyy-MM-dd".
     * @param preco Preço do produto (em formato float).
     * @param categoria Categoria à qual o produto pertence.
     * @param quantidade Quantidade inicial do produto no estoque.
     */
    public Produto(String nome, String validade, float preco, Categoria categoria, int quantidade) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.nome = nome;
        this.validade = LocalDate.parse(validade, formatter);
        this.preco = preco;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }


    public String getNome() {
        return nome;
    }
   
    

    public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public void adicionarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    public void removerEstoque(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
        } else {
            System.out.println("Estoque insuficiente para remover " + quantidade + " unidades.");
        }
    }

    public boolean temEstoque(int quantidade) {
        return this.quantidade >= quantidade;
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", validade=" + validade + ", preco=" + preco + ", categoria=" + categoria
                + ", quantidade=" + quantidade + "]";
    }
}
