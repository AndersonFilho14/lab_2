import java.sql.SQLException;
import java.time.chrono.IsoChronology;
import java.util.List;

import Controller.ClienteController;
import Controller.ProdutoController;
import Model.Categoria;
import Model.Cliente;
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

ClienteController clienteController = new ClienteController();
ClienteView clienteView = new ClienteView(clienteController);
clienteView.exibirMenu();
//
//FuncionarioController funcionarioController = new FuncionarioController();
//FuncionarioView funcionarioView = new FuncionarioView(funcionarioController);
//funcionarioView.exibirMenuGerente();
        // Exibindo o produto
//		ProdutoController produtoController = new ProdutoController();
//		ProdutoView pv = new ProdutoView(produtoController);
//		pv.exibirMenu();

//		ProdutoDao produtoDao = new ProdutoDao();
//		List<Produto> vencendo =  produtoDao.validadeProxima(x );
//		if (vencendo.isEmpty()) {
//			System.out.println("Não tem produtos vencendo nos " + x + " dias");
//		} else {
//		System.out.println(vencendo);
	}
}

