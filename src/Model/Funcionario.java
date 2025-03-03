package Model;

public class Funcionario extends Pessoa {
	    private int cargo;
	    private boolean empregado;


public Funcionario(String nome, String email, String senha, String cpf, String telefone) {
	        super(nome, email, senha, cpf, telefone);  
	        
	        this.cargo = 1;
	        this.empregado = true; 

	        // Validação adicional
	        if (cargo < 1) {
	            throw new IllegalArgumentException("Cargo não pode ser menor que 1.");
	        }
}

<<<<<<< Updated upstream
	    public int getCargo() {
	        return cargo;
	    }

	    public void setCargo(int cargo) {
	        if (cargo < 1) {
	            throw new IllegalArgumentException("Cargo não pode ser menor que 1.");
	        }
	        this.cargo = cargo;
	    }

	    public boolean getEmpregado() {
	        return empregado;
	    }


		public void setEmpregado(boolean empregado) {
			this.empregado = empregado;
		}


	}

=======
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
		this.cpf = cpf;
	}

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public int getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(int horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public int getVendasRealizadas() {
		return vendasRealizadas;
	}

	public void setVendasRealizadas(int vendasRealizadas) {
		this.vendasRealizadas = vendasRealizadas;
	}

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
>>>>>>> Stashed changes
