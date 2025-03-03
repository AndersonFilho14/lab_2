package Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que representa um pedido feito por um cliente.
 * Contém informações sobre o cliente, itens do pedido, data de emissão e status do pedido.
 */
public class Pedido {
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private Date dataEmissao;
    private StatusPedido status;

    /**
     * Construtor para criar um novo pedido.
     * @param id O identificador único do pedido.
     * @param cliente O cliente que fez o pedido.
     * @param itens A lista de itens no pedido.
     * @param dataEmissao A data de emissão do pedido.
     * @param status O status atual do pedido.
     */
    public Pedido(int id, Cliente cliente, List<ItemPedido> itens, Date dataEmissao, StatusPedido status) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A lista de itens não pode ser vazia.");
        }
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.dataEmissao = dataEmissao;
        this.status = status;
    }
    /**
     * Define os possíveis status de um pedido.
     */
    enum StatusPedido {
        EM_ANDAMENTO, CONCLUIDO, CANCELADO
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	/**
     * Calcula o valor total do pedido somando os subtotais de cada item.
     * @return O valor total do pedido.
     */
	public double calcularValorTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }
}