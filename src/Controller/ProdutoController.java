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
    public void atualizarProduto(int id, String nome, String validade, float preco, Categoria categoria, int quantidade, boolean prateleira) {
        Produto produto = new Produto(id, nome, validade, preco, categoria, quantidade, prateleira);
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
}

//    diminiuir quantidade de produto qunado tiver uma compra(lembrando de não poder comprar mais do que tem no bd)
//     retirar e colocar produto na prateleira
//    outro banco de dados que me mostre o caixa da empresa com as compras que foram efetuadas
//    retorne validade que esta chegando ao fim pedindo os dias para verificar se o produto vai vencer
//    



