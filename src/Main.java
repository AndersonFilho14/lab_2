import Controller.ClienteController;
import Model.Cliente;
import View.ViewCliente;

public class Main {

	public static void main(String[] args) {
		ClienteController clienteControler = new ClienteController();
		ViewCliente viewcliente = new ViewCliente(clienteControler);
	        viewcliente.exibirMenu();


	}

}
