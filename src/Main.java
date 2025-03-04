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

        // Exibindo o produto
//		ProdutoController produtoController = new ProdutoController();
//		ProdutoView pv = new ProdutoView(produtoController);
//		pv.exibirMenu();

		ProdutoDao produtoDao = new ProdutoDao();
//		Produto biscoito = new Produto(4,"Biscoito treloso de flocos, 120g", "2025-03-30", 1.90f, Categoria.COMIDA, 50, true);
//		produtoDao.inserirProduto(biscoito, 2);
//		produtoDao.atualizarProduto(biscoito);
//		Produto a = produtoDao.buscarProdutoPorNome("treloso");
//		System.out.println(a);
//		produtoDao.subtrairQuantidade(3, 1);
//		produtoDao.diminuirPreco(3, 0.03f);
//		produtoDao.somarPreco(3, 0.03f);
//		produtoDao.somarQuantidade(3, 1);
//		produtoDao.alterarPrateleira(2,true);
		int x =30;
		List<Produto> vencendo =  produtoDao.validadeProxima(x );
		if (vencendo.isEmpty()) {
			System.out.println("Não tem produtos vencendo nos " + x + " dias");
		} else {
		System.out.println(vencendo);
		}
	}
}
