import java.sql.SQLException;

import Controller.ClienteController;
import Controller.ProdutoController;
import Model.Categoria;
import Model.Cliente;
import Model.Produto;
import Model.DAO.ClienteDao;
import View.ClienteView;
import View.LoginView;
import View.ProdutoView;
import Controller.FuncionarioController;
import View.FuncionarioView;



public class Main {

	public static void main(String[] args) {
		
LoginView loginView = new LoginView();
loginView.exibirMenu();

//         Criando produto usando o método de fábrica
//        Produto carne = new Produto("Carne Bovina", "2025-12-31", (float) 30.00 , Categoria.COMIDA, 100);
//
//        // Exibindo o produto
//        System.out.println(carne.toString());
//		ProdutoController produtoController = new ProdutoController();
//		ProdutoView pv = new ProdutoView(produtoController);
//		pv.exibirMenu();

//FuncionarioController funcionarioController = new FuncionarioController();
//FuncionarioView funcionarioView = new FuncionarioView(funcionarioController);
//funcionarioView.exibirMenuGerente();
//funcionarioView.exibirMenuFuncionario();
		

	}
}
