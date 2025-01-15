package Model;

/**
 * Classe que representa um funcionário com informações essenciais e cálculo de salário.
 */
public class Funcionario {

    /**
     * Tipos possíveis de funcionário:
     * - ASSALARIADO: Recebe um salário fixo.
     * - COMISSIONADO: Recebe uma porcentagem sobre as vendas realizadas.
     * - POR_HORA: Recebe com base nas horas trabalhadas.
     * - BASE_COMISSAO: Recebe um salário base mais comissão sobre vendas.
     */
    public enum TipoFuncionario {
        ASSALARIADO, COMISSIONADO, POR_HORA, BASE_COMISSAO
    }

    private int id; // Identificador único do funcionário.
    private String nome; // Nome do funcionário.
    private String cpf; // CPF do funcionário.
    private TipoFuncionario tipoFuncionario; // Tipo do funcionário.
    private double salarioBase; // Salário base (aplicável a alguns tipos).
    private int horasTrabalhadas; // Número de horas trabalhadas.
    private int vendasRealizadas; // Número de vendas realizadas.

    /**
     * Construtor da classe.
     * @param id Identificador único.
     * @param nome Nome do funcionário.
     * @param cpf CPF do funcionário.
     * @param tipoFuncionario Tipo do funcionário (enum).
     * @param salarioBase Salário base.
     * @param horasTrabalhadas Horas trabalhadas (se aplicável).
     * @param vendasRealizadas Vendas realizadas (se aplicável).
     */
    public Funcionario(int id, String nome, String cpf, TipoFuncionario tipoFuncionario, 
                       double salarioBase, int horasTrabalhadas, int vendasRealizadas) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.tipoFuncionario = tipoFuncionario;
        this.salarioBase = salarioBase;
        this.horasTrabalhadas = horasTrabalhadas;
        this.vendasRealizadas = vendasRealizadas;
    }

    // Getters e Setters omitidos por brevidade.

    /**
     * Calcula o salário do funcionário com base no tipo.
     * @return O valor do salário calculado.
     */
    public double calcularSalario() {
        switch (tipoFuncionario) {
            case ASSALARIADO:
                return salarioBase;
            case COMISSIONADO:
                return vendasRealizadas * 0.1;
            case POR_HORA:
                return horasTrabalhadas * 20;
            case BASE_COMISSAO:
                return salarioBase + (vendasRealizadas * 0.05);
            default:
                throw new IllegalArgumentException("Tipo de funcionário inválido: " + tipoFuncionario);
        }
    }
 }
