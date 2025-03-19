import java.sql.SQLException;
import java.time.chrono.IsoChronology;
import java.util.List;

import Controller.CarrinhoController;
import Controller.ClienteController;
import Controller.ProdutoController;
import Model.Carrinho;
import Model.Categoria;
import Model.Cliente;
import Model.Funcionario;
import Model.Produto;
import Model.DAO.ClienteDao;
import Model.DAO.ProdutoDao;
import View.ClienteView;
import View.CompraView;
import View.LoginView;
import View.ProdutoView;
import Controller.FuncionarioController;
import View.FuncionarioView;



public class Main {

	public static void main(String[] args) {
		
//	    ------ Projeto Principal ------
LoginView loginView = new LoginView();
loginView.exibirMenu();

// ======  Testes  ======   
// ------ CLiente  ------
//ClienteController clienteController = new ClienteController();
//ClienteView clienteView = new ClienteView(clienteController);
//clienteView.exibirMenu();

// ------ FUncionario ------
//FuncionarioController funcionarioController = new FuncionarioController();
//FuncionarioView funcionarioView = new FuncionarioView(funcionarioController);
//Funcionario funcionario =  funcionarioController.loginFuncionario("marcio@email.com", "mercado123"); // funcionario
//Funcionario gerente =  funcionarioController.loginFuncionario("gerente@email.com", "mercado123"); // gerente
//funcionarioView.exibirMenuGerente(gerente);
//funcionarioView.exibirMenuFuncionario(funcionario);

//  ------ Produto  ------
//ProdutoController produtoController = new ProdutoController();
//ProdutoView produtoView = new ProdutoView(produtoController);
//FuncionarioController funcionarioController = new FuncionarioController();
//Funcionario funcionario =  funcionarioController.loginFuncionario("marcio@email.com", "mercado123"); // funcionario
//Funcionario gerente =  funcionarioController.loginFuncionario("gerente@email.com", "mercado123"); // gerente
//produtoView.exibirMenuGerente(gerente);
//produtoView.exibirMenuFuncionario(funcionario);
		
// ------  Compra  ------
//ProdutoController produtoController = new ProdutoController();
//Carrinho carrinho = new Carrinho();
//CarrinhoController carrinhoController = new CarrinhoController(carrinho,produtoController);
//CompraView compraView = new CompraView(carrinhoController);
//compraView.exibirMenuCompras(10);

	}
}

