package Controller;

import Model.DAO.FuncionarioDao;
import Model.Cliente;
import Model.Funcionario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    FuncionarioDao funcionarioDao = new FuncionarioDao();


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

    public List<Funcionario> listarFuncionarios() {
        try {
            return funcionarioDao.listar();
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
            return null;
        }
    }

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

    public void deletarFuncionario(int id) {
        try {
        	funcionarioDao.deletar(id);
            System.out.println("Funcionário removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao remover funcionário: " + e.getMessage());
        }
    }

    public Funcionario loginFuncionario(String email, String senha) {
        try {
            return funcionarioDao.consultarFuncionarioPorEmailESenha(email, senha);
        } catch (SQLException e) {
            System.err.println("Erro ao realizar login: " + e.getMessage());
            return null;
        }
    }
}
