package br.com.wmw.comprastc.service;


import java.sql.SQLException;
import java.util.List;

import br.com.wmw.comprastc.dao.ItemPedidoDAO;
import br.com.wmw.comprastc.dao.PedidoDAO;
import br.com.wmw.comprastc.dao.ProdutoDAO;
import br.com.wmw.comprastc.domain.ItemPedido;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.domain.Produto;
import br.com.wmw.comprastc.domain.StatusPedido;
import br.com.wmw.comprastc.dto.PedidoDTO;
import br.com.wmw.comprastc.exception.PersistenceException;
import br.com.wmw.comprastc.util.DateUtils;
import totalcross.sys.Settings;
import totalcross.ui.dialog.MessageBox;
import totalcross.util.InvalidDateException;

public class PedidoService {

	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private PedidoDAO pedidoDAO = new PedidoDAO();
	private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();


	public PedidoService() {

	}

	public List<Pedido> listarPedidosAberto() throws SQLException {
		List<Pedido> pedidosAberto;
		pedidosAberto = pedidoDAO.findAllByStatus(StatusPedido.ABERTO);
		return pedidosAberto;
	} 

	public List<Pedido> listarPedidosFechados() throws SQLException {
		List<Pedido> pedidosFechados;
		pedidosFechados = pedidoDAO.findAllByStatus(StatusPedido.FECHADO);
		return pedidosFechados;

	} 

	public List<Pedido> listarPedidosEnviados() throws SQLException {
		List<Pedido> pedidosEnviados;
		pedidosEnviados = pedidoDAO.findAllByStatus(StatusPedido.ENVIADO);
		return pedidosEnviados;

	} 

	public void updatePedidoEnviado(PedidoDTO pedido) throws PersistenceException {
		pedido.setStatusPedido("ENVIADO");
		new PedidoDAO().updatePedidoDTO(pedido);
	}

	public String retornaListaProdutos(Pedido pedido) {
		String itens = "";
		for (ItemPedido item : pedido.getItens()) {
			for (Produto produto : produtoDAO.buscarProdutos()) {
				if (produto.getCodigo() == item.getCodigoProduto())
					itens += produto.getNome();
			}
			itens += " : " + item.getQuantidade() + " unidades " + "\n";
		}

		return itens;
	}


	public double calculaValorTotal(Pedido pedido) {
		double sum = 0;
		for(ItemPedido item : pedido.getItens()) {
			sum += item.getTotalItem();
		}
		return sum;
	}


	public Pedido atualizarPedido(Pedido pedido) throws PersistenceException {
		try {
			if (pedidoDAO.retornaExisteId(pedido.getCodigoPedido()) != -1){
				pedidoDAO.atualizarPedido(pedido);
				for (ItemPedido item: pedido.getItens()) {
					if (itemPedidoDAO.retornaExisteId(item.getCodigoPedido()) == -1){
						item.setCodigoPedido(pedido.getCodigoPedido());
						itemPedidoDAO.inserirItem(item);
					}
				}
			} else {
				pedidoDAO.inserirPedido(pedido);
				pedido.setCodigoPedido(pedido.getCodigoPedido());
				for (ItemPedido item: pedido.getItens()) {
					item.setCodigoPedido(pedido.getCodigoPedido());
					itemPedidoDAO.inserirItem(item);
				}
			}
			pedido.getItens().clear();
			pedido.getItens().addAll(itemPedidoDAO.listarItemPorId(pedido.getCodigoPedido()));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return pedido;
	}


	public void inserirPedido(Pedido pedido) throws PersistenceException  {
		try {
			if (pedidoDAO.retornaExisteId(pedido.getCodigoPedido()) != -1){
				pedidoDAO.atualizarPedido(pedido);
				for (ItemPedido item: pedido.getItens()) {
					if (itemPedidoDAO.retornaExisteId(item.getCodigoPedido()) == -1){
						item.setCodigoPedido(pedido.getCodigoPedido());
						itemPedidoDAO.inserirItem(item);
					}
				}
			} else {
				pedidoDAO.inserirPedido(pedido);
				pedido.setCodigoPedido(pedidoDAO.retornaUltimoId());
				for (ItemPedido item: pedido.getItens()) {
					item.setCodigoPedido(pedido.getCodigoPedido());
					itemPedidoDAO.inserirItem(item);
				}
			}
			pedido.getItens().clear();
			pedido.getItens().addAll(itemPedidoDAO.listarItemPorId(pedido.getCodigoPedido()));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}


	public Pedido deletarItem(int id, Pedido pedido, ItemPedido itemPedido) {
		try {
			if (itemPedidoDAO.retornaExisteId(id) != -1) {
				pedido.getItens().remove(itemPedido);
				itemPedidoDAO.deletarItem(id);
				MessageBox mb = new MessageBox("Mensagem", "Item excluído com sucesso.", new String[]{"Fechar"});
				mb.popup();
				return pedido;
			} else {
				MessageBox mb = new MessageBox("Mensagem", "ERRO: Item não excluído.", new String[]{"Fechar"});
				mb.popup();
				return pedido;
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}



	public Boolean verificaDataEntrega(String dataEntrega, Pedido pedido) throws PersistenceException, InvalidDateException {
		if (dataEntrega.isEmpty()) {
			return false;
		} 
		if(!DateUtils.isDataAtualOuFutura(dataEntrega, Settings.DATE_DMY)) {
			return false;
		}	
		pedido.setDataEntrega(dataEntrega);
		return true;
	}

	public boolean verificaSeTemMinimoUmItem(Pedido pedido) {
		return pedido.getItens().size() > 0;

	}

	public void fecharPedido(Pedido pedido){
		try {
			pedidoDAO.fecharPedido(pedido);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

