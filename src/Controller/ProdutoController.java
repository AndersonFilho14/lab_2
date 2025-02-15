package Controller;

import Model.Produto;
import Model.Categoria;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private List<Produto> listaProdutos;  // Renomeando para listaProdutos

    public ProdutoController() {
        listaProdutos = new ArrayList<>();  // Inicializa a lista
    }

    public Produto buscarProdutoPorNome(String nome) {
        for (Produto p : listaProdutos) {  // Agora usa listaProdutos corretamente
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
    public List<Produto> listarProdutos() {
        return listaProdutos;
    }

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public void removerProduto(Produto produto) {
        listaProdutos.remove(produto);
    }

    // Outros métodos como editar e listar
}


