package Model;
/**
 * Representa um cliente com benefícios de fidelidade, herdando as características do cliente.
 */

public class ClienteFidelidade extends Cliente {
	private int pontosAcumulados;
	
	/**
     * Construtor para criar um cliente de fidelidade.
     * 
     * @param id O identificador único do cliente.
     * @param nome O nome do cliente.
     * @param cpf O CPF do cliente.
     * @param email O email do cliente.
     * @param telefone O telefone do cliente.
     * @param pontosAcumulados A quantidade de pontos acumulados pelo cliente.
     */
	public ClienteFidelidade(int id, String nome, String cpf, String email, String telefone, int pontosAcumulados) {
        super(id, nome, cpf, email, telefone);
        if (pontosAcumulados < 0) {
        	throw new IllegalArgumentException("Os pontos acumulados não podem ser negativos.");
        }	
        this.pontosAcumulados = pontosAcumulados;
    }
	
	
	/**
     * Construtor para criar um cliente de fidelidade a partir de uma instância de cliente e pontos acumulados.
     *
     * @param cliente A instância do cliente que contém os dados como id, nome, cpf, email e telefone.
     * @param pontosAcumulados A quantidade de pontos acumulados pelo cliente.
     */
    public ClienteFidelidade(Cliente cliente, int pontosAcumulados) {
        super(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone());
        if (pontosAcumulados < 0) {
            throw new IllegalArgumentException("Os pontos acumulados não podem ser negativos.");
        }
        this.pontosAcumulados = pontosAcumulados;
    }

    public int getPontosAcumulados() {
        return pontosAcumulados;
    }

    public void setPontosAcumulados(int pontosAcumulados) {
        this.pontosAcumulados = pontosAcumulados;
    }
}
