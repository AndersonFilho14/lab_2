package Controller;

import Model.DAO.FuncionarioDao;
import Model.DAO.ProdutoDao;
import Model.Cliente;
import Model.Funcionario;
import Model.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    FuncionarioDao funcionarioDao = new FuncionarioDao();

    /**
     * Adiciona um novo funcionário.
     * 
     * @param nome      Nome do funcionário.
     * @param email     Email do funcionário.
     * @param senha     Senha do funcionário.
     * @param cpf       CPF do funcionário.
     * @param telefone  Telefone do funcionário.
     * @param cargo     Cargo do funcionário (número).
     */
    public void adicionarFuncionario(String nome, String email, String senha, String cpf, String telefone, int cargo) {
        Funcionario funcionario = new Funcionario(nome, email, senha, cpf, telefone);
        funcionario.setCargo(cargo);
        try {
        	funcionarioDao.adicionar(funcionario);
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar funcionário: " + e.getMessage());
        }
    }

    /**
     * Retorna a lista de funcionários cadastrados.
     * 
     * @return Lista de funcionários.
     */
    public List<Funcionario> listarFuncionarios() {
        try {
            return funcionarioDao.listar();
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
            return null;
        }
    }

    /**
     * Atualiza os dados de um funcionário.
     * 
     * @param id         ID do funcionário a ser atualizado.
     * @param nome       Novo nome.
     * @param email      Novo email.
     * @param senha      Nova senha.
     * @param cpf        Novo CPF.
     * @param telefone   Novo telefone.
     * @param cargo      Novo cargo (número).
     * @param empregado  Novo status de empregado.
     */
    public void atualizarFuncionario(int id, String nome, String email, String senha, String cpf, String telefone, int cargo, boolean empregado) {
        Funcionario funcionario = new Funcionario(nome, email, senha, cpf, telefone);
        funcionario.setCargo(cargo);
        funcionario.setEmpregado(empregado);
        try {
        	funcionarioDao.atualizar(funcionario, id);
            System.out.println("Funcionário atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    /**
     * Remove um funcionário com base no ID.
     * 
     * @param id ID do funcionário a ser removido.
     */
    public void deletarFuncionario(int id) {
        try {
        	funcionarioDao.deletar(id);
            System.out.println("Funcionário removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao remover funcionário: " + e.getMessage());
        }
    }

    /**
     * Realiza o login do funcionário.
     * 
     * @param email Email do funcionário.
     * @param senha Senha do funcionário.
     * @return Objeto Funcionario se o login for bem-sucedido, caso contrário null.
     */
    public Funcionario loginFuncionario(String email, String senha) {
        try {
            return funcionarioDao.consultarFuncionarioPorEmailESenha(email, senha);
        } catch (SQLException e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Demite o funcionário pelo nome, definindo o campo empregado como 0.
     * 
     * @param nome Nome do funcionário a ser demitido.
     */
    public void demitirFuncionario(String nome) {
    	try {
    		funcionarioDao.demitirFuncionarioPorNome(nome); 
    		System.out.println("Funcionário ,"+nome+ " , demitido com sucesso!");
    	} catch(SQLException e) {
    		System.err.println("Erro ao realizar demissão: " + e.getMessage());
    	}
    }
    
    /**
     * Contrata (ou reemprega) o funcionário pelo nome, definindo o campo empregado como 1.
     * 
     * @param nome Nome do funcionário a ser contratado.
     */
    public void empregarFuncionario(String nome){
    	try {
    		funcionarioDao.empregarFuncionarioPorNome(nome); 
    		System.out.println("Funcionário, " + nome+", contratado com sucesso!");
    	} catch(SQLException e) {
    		System.err.println("Erro ao realizar contratação: " + e.getMessage());
    	}
    }

    /**
     * Alterar cargo para o novo valor informado.
     * 
     * @param nome      Nome do funcionário.
     * @param novoCargo Novo cargo (número) que será atribuído.
     */
    public void mudarCargoFuncionarioNome(String nome, int novoCargo) {
        try {
            funcionarioDao.mudarCargoFuncionarioPorNome(nome, novoCargo);
            System.out.println("Funcionário promovido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao promover funcionário: " + e.getMessage());
        }
    }

    public void getProdutosAdicionadosPeloFuncionario_id(int id) {
		ProdutoDao produtoDao = new ProdutoDao();
		List<Produto> produtos = produtoDao.buscarProdutoPeloid_funcionario(id);
		
		if (produtos.isEmpty()) {
	        System.out.println("Este funcionário ainda não adicionou nenhum produto.");
	    } else {
	        System.out.println("Produtos adicionados:");
	        for (Produto produto : produtos) {
	            System.out.println(produto);
	           }
    	}
    }
    
    /**
     * Busca um funcionário pelo seu ID.
     * 
     * @param id ID do funcionário.
     * @return O objeto Funcionario correspondente ou null se não encontrado.
     */
    public Funcionario buscarFuncionarioPorId(int id) {
        try {
            return funcionarioDao.buscarFuncionarioPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário por id: " + e.getMessage());
            return null;
        }
    }
}