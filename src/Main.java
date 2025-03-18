import java.sql.SQLException;
import java.time.chrono.IsoChronology;
import java.util.List;

import Controller.ClienteController;
import Controller.ProdutoController;
import Model.Categoria;
import Model.Cliente;
import Model.Funcionario;
import Model.Produto;
import Model.DAO.ClienteDao;
import Model.DAO.ProdutoDao;
import View.ClienteView;
import View.LoginView;
import View.ProdutoView;
import Controller.FuncionarioController;
import View.FuncionarioView;



public class Main {

	public static void main(String[] args) {
//		
//LoginView loginView = new LoginView();
//loginView.exibirMenu();

// 			Testes
// CLiente

// FUncionario
FuncionarioController funcionarioController = new FuncionarioController();
FuncionarioView funcionarioView = new FuncionarioView(funcionarioController);
Funcionario funcionario =  funcionarioController.loginFuncionario("marcio@email.com", "mercado123"); // funcionario
Funcionario gerente =  funcionarioController.loginFuncionario("gerente@email.com", "mercado123"); // gerente
funcionarioView.exibirMenuGerente(gerente);
funcionarioView.exibirMenuFuncionario(funcionario);

//  Produto

// Compra

// Login
//	Não é preciso

	}
}

