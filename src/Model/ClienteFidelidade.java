package Model;

import Controller.ClienteController; 

/**
 * Representa um cliente com benefícios de fidelidade, associando-se a um cliente e armazenando pontos acumulados.
 */
public class ClienteFidelidade {
    private int clienteId; // ID do cliente associado
    private int pontosAcumulados;

    /**
     * Construtor para criar um cliente de fidelidade a partir do ID do cliente e pontos acumulados.
     * 
     * @param clienteId O identificador único do cliente associado.
     * @param pontosAcumulados A quantidade de pontos acumulados pelo cliente.
     * @param clienteController O controlador para verificar a existência do cliente.
     */
    public ClienteFidelidade(int clienteId, int pontosAcumulados, ClienteController clienteController) {
        if (pontosAcumulados < 0) {
            throw new IllegalArgumentException("Os pontos acumulados não podem ser negativos.");
        }
        // Valida se o cliente com o ID fornecido existe
        if (!clienteController.existeCliente(clienteId)) {
            throw new IllegalArgumentException("Cliente com o ID informado não existe.");
        }

        this.clienteId = clienteId;
        this.pontosAcumulados = pontosAcumulados;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getPontosAcumulados() {
        return pontosAcumulados;
    }

    public void setPontosAcumulados(int pontosAcumulados) {
        this.pontosAcumulados = pontosAcumulados;
    }
}
