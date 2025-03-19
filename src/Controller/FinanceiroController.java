package Controller;

import java.util.List;
import Model.Financeiro;
import Model.DAO.FinanceiroDao;

public class FinanceiroController {
    private FinanceiroDao financeiroDao;

    public FinanceiroController() {
        this.financeiroDao = new FinanceiroDao();
    }
    
    
    public boolean registrarCompra(double valorCompra, int idCliente) {
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
            System.out.println("Compra registrada no financeiro! ID da compra: " + financeiro.getId());
        } else {
            System.out.println("Erro ao registrar a compra.");
        }
        return sucesso;
    }

    
    public void listarCompras() {
    	List<Financeiro> lista = financeiroDao.gerarRelatorioGeral();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma compra registrada no financeiro.");
            return;
        }

        double totalCompras = 0;
        int numeroCompras = 0;
        double maiorCompra = 0;

        System.out.println("\n Lista de Compras Registradas:");
        for (Financeiro f : lista) {
            System.out.println("ID: " + f.getId() +
                               " | Cliente: " + f.getIdCliente() +
                               " | Valor: R$" + f.getValorCompra() +
                               " | Data: " + f.getDataCompra());
            
            totalCompras += f.getValorCompra();
            numeroCompras++;
            if (f.getValorCompra() > maiorCompra) {
                maiorCompra = f.getValorCompra();
            }
        }

        System.out.println("\n === Resumo do Relatório: ===");
        System.out.println("Total de Compras: R$" + totalCompras);
        System.out.println("Número de Compras: " + numeroCompras);
        System.out.println("Maior Compra: R$" + maiorCompra);
    }

    
    public void listarComprasUltimosDias(int dias) {
    	if (dias <= 0) {
            System.out.println("Erro: O número de dias deve ser maior que zero.");
            return;
        }

        List<Financeiro> lista = financeiroDao.gerarRelatorioUltimosDias(dias);

        if (lista.isEmpty()) {
            System.out.println("Nenhuma compra registrada nos últimos " + dias + " dias.");
            return;
        }

        double totalCompras = 0;
        double maiorCompra = 0;
        int numeroCompras = 0;

        System.out.println("\n Compras registradas nos últimos " + dias + " dias:");
        for (Financeiro f : lista) {
            System.out.println("ID: " + f.getId() +
                               " | Cliente: " + f.getIdCliente() +
                               " | Valor: R$" + f.getValorCompra() +
                               " | Data: " + f.getDataCompra());

            totalCompras += f.getValorCompra();
            if (f.getValorCompra() > maiorCompra) {
                maiorCompra = f.getValorCompra();
            }
            numeroCompras++;
        }
        
        System.out.println("\n === Resumo do Relatório: ===");
        System.out.println("Total de Compras: R$" + totalCompras);
        System.out.println("Número de Compras: " + numeroCompras);
        System.out.println("Maior Compra: R$" + maiorCompra);
    }
}
