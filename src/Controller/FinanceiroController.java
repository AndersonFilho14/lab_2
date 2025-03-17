package Controller;

import java.util.List;

import Model.Financeiro;
import Model.DAO.FinanceiroDao;

public class FinanceiroController {
    private FinanceiroDao financeiroDao;

    public FinanceiroController() {
        this.financeiroDao = new FinanceiroDao();
    }
    
  
    public boolean registrarCompra(double valorCompra, int idCliente){
        if (valorCompra < 0) {
            System.out.println("Erro: O valor da compra deve ser maior que zero.");
            return false;
        }
        if (idCliente <= 0) {
            System.out.println("Erro: ID do cliente inválido.");
            return false;
        }
        
        Financeiro financeiro = new Financeiro(0, valorCompra, idCliente, null);
        boolean sucesso = financeiroDao.inserirFinanceiro(financeiro);
        if (sucesso) {
            System.out.println("Compra registrada no financeiro! ID do cliente :  " + financeiro.getId());
        } else {
            System.out.println("Erro ao registrar a compra.");
        }
        return sucesso;
    }


    
    public void listarCompras() {
        List<Financeiro> lista = financeiroDao.listarFinanceiro();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma compra registrada no financeiro.");
            return;
        }
        System.out.println("Lista de Compras Registradas:");
        for (Financeiro f : lista) {
            System.out.println("ID: " + f.getId() +
                               " | Cliente: " + f.getIdCliente() +
                               " | Valor: R$" + f.getValorCompra() +
                               " | Data: " + f.getDataCompra());
        }
    }
}
