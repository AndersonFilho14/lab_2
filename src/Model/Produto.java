package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representa um produto no supermercado.
 * Contém informações sobre nome, validade, preço e categoria do produto.
 */
public class Produto {
	private String nome;
	private LocalDate validade;
	private float preco;
	private Categoria categoria;
	
    /**
     * Constrói um novo produto com as informações fornecidas.
     * 
     * @param nome Nome do produto.
     * @param validade Data de validade do produto no formato "yyyy-MM-dd".
     * @param preco Preço do produto (em formato float).
     * @param categoria Categoria à qual o produto pertence.
     */
	public Produto(String nome, String validade, float preco, Categoria categoria) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.nome = nome;
        this.validade = LocalDate.parse(validade, formatter);
        this.preco = preco;
        this.categoria = categoria;
    }
	
	
	public String getNome() {
		return nome;
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

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", validade=" + validade + ", preco=" + preco + ", categoria=" + categoria
				+ "]";
	}
	
}
