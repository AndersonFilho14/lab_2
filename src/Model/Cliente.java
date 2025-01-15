package Model;

import View.MainView;

/**
 * Representa um cliente com informações como id, nome, CPF, email e telefone.
 */
public class Cliente {
	private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
	
 /**
     * Construtor para criar um novo cliente.
     * 
     * @param id O identificador único do cliente.
     * @param nome O nome do cliente.
     * @param cpf O CPF do cliente.
     * @param email O email do cliente.
     * @param telefone O telefone do cliente.
     */
    public Cliente(int id, String nome, String cpf, String email, String telefone) {
    	if (id < 0) throw new IllegalArgumentException("ID deve ser maior que zero.");
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio.");
        setCpf(cpf);
        if (!email.contains("@")) throw new IllegalArgumentException("Email inválido.");
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos numéricos.");
        }
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
