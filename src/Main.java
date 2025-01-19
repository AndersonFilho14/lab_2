import java.sql.SQLException;

import Controller.ClienteController;
import Model.Cliente;
import Model.DAO.ClienteDao;
import View.ClienteView;
import View.LoginView;


public class Main {

	public static void main(String[] args) {
		ClienteController clienteControler = new ClienteController();
		LoginView loginView = new LoginView(clienteControler);
		loginView.exibirMenu();
		
	}
}
