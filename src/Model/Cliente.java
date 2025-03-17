package Model;

/**
 * Representa um cliente, que herda atributos básicos de Pessoa
 * e possui um sistema de fidelidade com pontos acumulados.
 */
public class Cliente extends Pessoa {
    private int pontosFidelidade;

    /**
     * Construtor para criar um cliente com os dados fornecidos.
     * Inicializa os pontos de fidelidade com zero.
     * 
     * @param nome O nome do cliente.
     * @param email O email do cliente.
     * @param senha A senha do cliente.
     * @param cpf O CPF do cliente.
     * @param telefone O telefone do cliente.
     */
    public Cliente(String nome, String email, String senha, String cpf, String telefone) {
        super(nome, email, senha, cpf, telefone);
        this.pontosFidelidade = 0; 
    }


    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void setPontosFidelidade(int pontosFidelidade) {
        if (pontosFidelidade < 0) {
            throw new IllegalArgumentException("Pontos de fidelidade não podem ser negativos.");
        }
        this.pontosFidelidade = pontosFidelidade;
    }


    public void adicionarPontos(int pontos) {
        if (pontos < 0) {
            throw new IllegalArgumentException("Não é possível adicionar pontos negativos.");
        }
        this.pontosFidelidade += pontos;
    }


    public void removerPontos(int pontos) {
        if (pontos < 0 || pontos > this.pontosFidelidade) {
            throw new IllegalArgumentException("Quantidade de pontos inválida para remoção.");
        }
        this.pontosFidelidade -= pontos;
    }

}
