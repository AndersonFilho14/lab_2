package Model;

public class Funcionario extends Pessoa {
		private int id;
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
			public int getId() {
		    return id;
		}
		
			public void setId(int id) {
		    this.id = id;
		}
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

