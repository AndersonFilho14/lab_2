package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Produto {
    private int id;
    private String nome;
    private LocalDate validade;
    private float preco;
    private Categoria categoria;
    private int quantidade;
    private boolean prateleira;

    // (para listar do banco)
    public Produto(int id, String nome, String validade, float preco, Categoria categoria, int quantidade, boolean prateleira) {
        this.id = id;
        this.nome = nome;
        this.validade = parseData(validade); // Converte String para LocalDate
        this.preco = preco;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.prateleira = prateleira;
    }

    //(para criação de novos produtos)
    public Produto(String nome, String validade, float preco, Categoria categoria, int quantidade) {
        this.nome = nome;
        this.validade = parseData(validade);
        this.preco = preco;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.prateleira = true;
    }

    private LocalDate parseData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(data, formatter);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = parseData(validade); }

    public float getPreco() { return preco; }
    public void setPreco(float preco) { this.preco = preco; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public boolean isPrateleira() { return prateleira; }
    public void setPrateleira(boolean prateleira) { this.prateleira = prateleira; }

    @Override
    public String toString() {
        return getId() + " | " + getNome() + " | " + getCategoria() + " | Preço: " + getPreco() + " | Quantidade no estoque: " + getQuantidade() + " |  Na prateleira: " + isPrateleira();
    }

    
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Produto produto = (Produto) obj;
	    return id == produto.id; // Comparação pelo ID do produto
	}

	@Override
	public int hashCode() {
	    return Integer.hashCode(id);
	}

    
}
