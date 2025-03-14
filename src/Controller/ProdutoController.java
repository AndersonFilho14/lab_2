package Controller;

import Model.DAO.ProdutoDao;
import Model.Categoria;
import Model.Produto;
import java.util.List;

public class ProdutoController {
    private ProdutoDao produtoDao;

    public ProdutoController() {
        this.produtoDao = new ProdutoDao();
    }

    // Criar um novo produto
    public void adicionarProduto(String nome, String validade, float preco, Categoria categoria, int quantidade, int idFuncionario) {
        Produto produto = new Produto(nome, validade, preco, categoria, quantidade);
        produtoDao.inserirProduto(produto, idFuncionario);
        System.out.println("Produto adicionado com sucesso!");
    }

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoDao.listarProdutos();
    }

    // Atualizar um produto existente
    public void atualizarProduto(Produto produto) {
        produtoDao.atualizarProduto(produto);
        System.out.println("Produto atualizado com sucesso!");
    }

    // Remover um produto pelo ID
    public void removerProduto(int id) {
        produtoDao.deletarProduto(id);
        System.out.println("Produto removido com sucesso!");
    }

    // Buscar um produto pelo ID
    public Produto buscarProdutoPorNome(String nome) {
        return produtoDao.buscarProdutoPorNome(nome);
    }
    
    public Produto buscarProdutoPorId(int id) {
        return produtoDao.buscarProdutoPorId(id);
    }    
    
    public void comprarProduto(int id, int quantidade) {
        Produto produto = produtoDao.buscarProdutoPorId(id);
        if (produto != null && produto.getQuantidade() >= quantidade) {
            produtoDao.subtrairQuantidade(id, quantidade);
            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Quantidade insuficiente em estoque do produto"+ produto.getNome());
        }
    }
    
    public void colocarNaPrateleira(int id) {
        produtoDao.alterarPrateleira(id, true);
        System.out.println("Produto colocado na prateleira com sucesso!");
    }

    public void retirarDaPrateleira(int id) {
        produtoDao.alterarPrateleira(id, false);
        System.out.println("Produto retirado da prateleira com sucesso!");
    }
    
    public List<Produto> produtosComValidadeProxima(int dias) {
        return produtoDao.validadeProxima(dias);
    }
    
    public List<Produto> listarProdutosNaPrateleira() {
        return produtoDao.listarProdutosNaPrateleira();
    }

    
}

//    diminiuir quantidade de produto qunado tiver uma compra(lembrando de não poder comprar mais do que tem no bd)
//     retirar e colocar produto na prateleira
//    outro banco de dados que me mostre o caixa da empresa com as compras que foram efetuadas
//    retorne validade que esta chegando ao fim pedindo os dias para verificar se o produto vai vencer
//    



