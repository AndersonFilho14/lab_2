package Model;

/**
 * Classe abstrata que representa uma pessoa com atributos comuns: id, nome, email, senha, cpf e telefone.
 * A classe fornece métodos para manipulação desses dados e validações de entrada.
 */
public abstract class Pessoa {
    private int id; 
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone; // Opcional

    /**
     * Construtor para inicializar os dados da pessoa.
     * 
     * @param nome Nome da pessoa (não pode ser vazio).
     * @param email Email da pessoa (deve conter '@').
     * @param senha Senha da pessoa (deve ter pelo menos 4 caracteres).
     * @param cpf CPF da pessoa (deve conter exatamente 11 dígitos numéricos).
     * @param telefone Telefone da pessoa (pode ser nulo).
     */
    public Pessoa(String nome, String email, String senha, String cpf, String telefone) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        if (senha == null || senha.length() < 4) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 4 caracteres.");
        }
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos.");
        }

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone != null ? telefone : ""; // Telefone pode ser vazio
    }

    public void setId(int id) {
		this.id = id;
	}

    // Getters e setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.length() <  4) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 4 caracteres.");
        }
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos.");
        }
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone != null ? telefone : ""; // Telefone pode ser vazio
    }
}
