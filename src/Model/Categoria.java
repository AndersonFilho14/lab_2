package Model;

/**
 * Representa uma categoria de produto no supermercado.
 * As categorias podem ser hierárquicas, ou seja, podem ter uma categoria "pai".
 */
public class Categoria {
	private String nome; 
	private Categoria categoriaPai;
	
	/**
     * Constrói uma nova categoria com o nome e, opcionalmente, uma categoria "pai".
     * 
     * @param nome Nome da categoria.
     * @param categoriaPai Categoria "pai" da categoria atual, pode ser null se não houver categoria pai.
     */
	public Categoria(String nome, Categoria categoriaPai) {
		this.nome = nome;
		this.categoriaPai = categoriaPai;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}
	
    /**
     * Retorna o nome completo da categoria, incluindo o nome das categorias "pais".
     * 
     * @return Nome completo da categoria com a hierarquia, se houver.
     */
	public String getNomeCompleto() {
        if (categoriaPai != null) {
            return categoriaPai.getNomeCompleto() + " > " + nome;
        }
        return nome;
    }

}
