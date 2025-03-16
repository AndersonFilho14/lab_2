package Model;

public class Financeiro {
    private int id;
    private double valorCompra;
    private int idCliente;
    private String dataCompra;

    public Financeiro(int id, double valorCompra, int idCliente, String dataCompra) {
        this.id = id;
        this.valorCompra = valorCompra;
        this.idCliente = idCliente;
        this.dataCompra = dataCompra;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}


}
