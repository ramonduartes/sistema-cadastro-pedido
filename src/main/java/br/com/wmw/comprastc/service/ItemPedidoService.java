package br.com.wmw.comprastc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dao.ProdutoDAO;
import br.com.wmw.comprastc.domain.ItemPedido;
import br.com.wmw.comprastc.domain.Produto;
import totalcross.ui.dialog.MessageBox;
	
	public class ItemPedidoService {
	    private ProdutoDAO produtoDAO = new ProdutoDAO();
	    private List<Produto> produtos = new ArrayList<>();

	    public String[] retornaListaProdutos() throws SQLException {
	        produtos = produtoDAO.buscarProdutos();
	        String items[] = new String[produtos.size()];
	        int i = 0;
	        for (Produto produto : produtos) {
	            items[i] = produto.getNome();
	            i++;
	        }
	        return items;
	    }

	    public ItemPedido calculaValorTotalItem(String quantidade, String desconto, ItemPedido item) {
	    	if (item != null) {
	    		if(quantidade.isEmpty()) {
		    		quantidade = "0";
		    	}
		    	if (desconto.isEmpty()) {
		    		desconto = "0";
		    	}
		    	int quantidadeInt = Integer.valueOf(quantidade);
		    	
		    	double descontoDouble = Double.valueOf(desconto);
		    	double totalDesconto = item.getPrecoUnitario() * (descontoDouble / 100);
				double totalItem = quantidadeInt * (item.getPrecoUnitario() - totalDesconto );
				item.setTotalItem(Math.round(totalItem * 100.0)/100.0);
				item.setQuantidade(quantidadeInt);
				item.setDesconto(descontoDouble);	   
				
	    	}
	    	
			
			return item;
		}
		

		public boolean verificaSeTemUmItemNoPedido(ItemPedido item) {
			if (item != null) {
	            return true;
	        } else {
	            MessageBox mbItem = new MessageBox("Mensagem", "Insira um produto.", new String[] { "Fechar" });
	            mbItem.popup();
	            return false;
	        }
	    }

		public boolean verificaQuantidadeMinPedido(ItemPedido item) {
			if (item.getQuantidade() >= 1) {
	            return true;
	        } else {
	            MessageBox mbQuantidade = new MessageBox("Mensagem", "Voc? tem que inserir pelo menos 1 item no pedido", new String[] { "Fechar" });
	            mbQuantidade.popup();
	            return false;
	        }
	    }
		
		public Boolean verificaSeNaoTemDescontoIgualOuMaiorQue100PorCento(ItemPedido itempedido) {
			if (itempedido.getDesconto() <= 99) {
				return true;
			} else {
				MessageBox mbDesconto = new MessageBox("Mensagem", "Nao ? permitido desconto igual ou superior a 100%", new String[] { "Fechar" });
				mbDesconto.popup();
				return false;
			}
			
		}

    }


		  
