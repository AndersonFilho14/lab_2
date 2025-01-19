import Controller.ClienteController;
import Model.Cliente;
import View.ClienteView;

public class Main {

	public static void main(String[] args) {
		ClienteController clienteControler = new ClienteController();
		ClienteView viewcliente = new ClienteView(clienteControler);
	        viewcliente.exibirMenu();


	}

}
