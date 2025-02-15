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


public class Main {

	public static void main(String[] args) {
//		ClienteController clienteControler = new ClienteController();
//		LoginView loginView = new LoginView(clienteControler);
//		loginView.exibirMenu();
//		

        // Criando produto usando o método de fábrica
//        Produto carne = new Produto("Carne Bovina", "2025-12-31", (float) 30.00 , Categoria.COMIDA);
//
//        // Exibindo o produto
//        System.out.println(carne.toString());
		ProdutoController produtoController = new ProdutoController();
		ProdutoView pv = new ProdutoView(produtoController);
		pv.exibirMenu();
	}
}
