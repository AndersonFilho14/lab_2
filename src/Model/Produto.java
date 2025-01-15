package Model;


/**
 * Representa um produto com informações de id, nome, preço e quantidade.
 */
public class Produto {

    private int id;
    private String nome;
    private double preco; 
    private int quantidade;

    /**
     * Construtor para criar um novo produto.
     *
     * @param id         O identificador único do produto (deve ser maior que zero).
     * @param nome       O nome do produto (não pode ser nulo ou vazio).
     * @param preco      O preço do produto (não pode ser negativo).
     * @param quantidade A quantidade do produto disponível (deve ser maior que zero).
     */
    public Produto(int id, String nome, double preco, int quantidade) {
        if (id <= 0) throw new IllegalArgumentException("ID deve ser maior que zero.");
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio.");
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");

        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID deve ser maior que zero.");
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio.");
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");
        this.preco = preco; //
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        this.quantidade = quantidade;
    }

    /**
     * Sobrescreve o método toString para exibir as informações do produto.
     */
    @Override
    public String toString() {
        return String.format("Produto[id=%d, nome='%s', preco=%.2f, quantidade=%d]", 
                             id, nome, preco, quantidade);
    }

 }

