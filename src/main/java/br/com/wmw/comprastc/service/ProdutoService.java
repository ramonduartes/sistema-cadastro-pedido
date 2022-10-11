package br.com.wmw.comprastc.service;

import java.util.List;

import br.com.wmw.comprastc.dao.ProdutoDAO;
import br.com.wmw.comprastc.domain.Produto;

public class ProdutoService {

	  private ProdutoDAO produtoDAO = new ProdutoDAO();

	    public List<Produto> listarProdutos(){
	        List<Produto> produtos;
	        produtos = produtoDAO.buscarProdutos();
	        return produtos;
	    }
}
