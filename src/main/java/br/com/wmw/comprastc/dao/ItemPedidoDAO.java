package br.com.wmw.comprastc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dados.DatabaseManager;
import br.com.wmw.comprastc.domain.ItemPedido;
import br.com.wmw.comprastc.dto.ItemPedidoDTO;
import totalcross.sql.Connection;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;

public class ItemPedidoDAO {

	public List<ItemPedido> listarItemPorId(int id) throws SQLException {
		Connection connection = DatabaseManager.getConnection();

		List<ItemPedido> itens = new ArrayList<>();
		Statement st = connection.createStatement();
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM ITEMPEDIDO WHERE COD_PEDIDO =" + id + "");
			try {
				while (rs.next()) {
					itens.add(new ItemPedido(rs.getInt("COD_PEDIDO")));
				}
			} finally {
				rs.close();
			}
		} finally {
			connection.close();
		}

		return itens;

	}

	public List<ItemPedidoDTO> listarItemPorIdDTO(int id) throws SQLException {
		Connection connection = DatabaseManager.getConnection();

		List<ItemPedidoDTO> itens = new ArrayList<>();
		Statement st = connection.createStatement();
		try {
			ResultSet rs = st.executeQuery("SELECT COD_ITEMPEDIDO, QUANTIDADE, PRECO_UNITARIO, DESC_PORCENTO, TOTAL, COD_PRODUTO, COD_PEDIDO FROM ITEMPEDIDO WHERE COD_PEDIDO = " + id);
			try {
				while (rs.next()) {
					ItemPedidoDTO item = new ItemPedidoDTO();
					item.setId(rs.getInt("COD_ITEMPEDIDO"));
					item.setQuantidade(rs.getInt("QUANTIDADE"));
					item.setPrecoUnitario(rs.getDouble("PRECO_UNITARIO"));
					item.setDesconto(rs.getDouble("DESC_PORCENTO"));
					item.setTotalItem(rs.getDouble("TOTAL"));
					item.setIdProduto(rs.getInt("COD_PRODUTO"));
					item.setIdPedido(rs.getInt("COD_PEDIDO"));
					itens.add(item);
				}
			} finally {
				rs.close();
			}
		} finally {
			connection.close();
		}

		return itens;

	}

	public int retornaExisteId(Integer id) throws SQLException {
		Connection connection = DatabaseManager.getConnection();

		int retorno = -1;
		try {
			ResultSet rs = connection.createStatement().executeQuery("SELECT COD_ITEMPEDIDO AS COD_ITEMPEDIDO FROM ITEMPEDIDO WHERE COD_ITEMPEDIDO=" + id + "");
			try {
				while (rs.next()) {
					retorno = rs.getInt("COD_ITEMPEDIDO");
				}
			} finally {
				rs.close();
			}
		} finally {
			connection.close();
		}

		return retorno;

	}

	public List<ItemPedido> findByCodigoPedido(Integer codigoPedido) throws SQLException {
		List<ItemPedido> itens = new ArrayList<>();

		Connection connection = DatabaseManager.getConnection();
		Statement st = connection.createStatement();
		try {
			ResultSet rs = st.executeQuery("SELECT COD_ITEMPEDIDO, QUANTIDADE, PRECO_UNITARIO, DESC_PORCENTO, TOTAL, COD_PRODUTO, COD_PEDIDO FROM ITEMPEDIDO WHERE COD_PEDIDO = " + codigoPedido);
			try {
				while(rs.next()) {
					ItemPedido item = new ItemPedido();
					item.setCodigoItemPedido(rs.getInt("COD_ITEMPEDIDO"));
					item.setQuantidade(rs.getInt("QUANTIDADE"));
					item.setPrecoUnitario(rs.getDouble("PRECO_UNITARIO"));
					item.setDesconto(rs.getDouble("DESC_PORCENTO"));
					item.setTotalItem(rs.getDouble("TOTAL"));
					item.setCodigoProduto(rs.getLong("COD_PRODUTO"));
					item.setCodigoPedido(rs.getInt("COD_PEDIDO"));
					itens.add(item);
				}
			} finally {
				rs.close();
			}
		} finally {
			st.close();
		}


		return itens;
	}

	public void inserirItem(ItemPedido itemPedido) throws SQLException {
		Connection dbcon = DatabaseManager.getConnection();

		try {
			dbcon.createStatement().execute("INSERT INTO ITEMPEDIDO (COD_PEDIDO, COD_PRODUTO, QUANTIDADE, PRECO_UNITARIO, DESC_PORCENTO, TOTAL) values ("
					+ itemPedido.getCodigoPedido() + ",	 "
					+ itemPedido.getCodigoProduto() + ", "
					+ itemPedido.getQuantidade() + ", "
					+ itemPedido.getPrecoUnitario() + ", "
					+ itemPedido.getDesconto() + ", "
					+ itemPedido.getTotalItem() + ")");
		} finally {
			dbcon.close();
		}
	}

	public void deletarItem(int id) throws SQLException {
		Connection dbcon = DatabaseManager.getConnection();
		try {
			dbcon.createStatement().execute("DELETE FROM ITEMPEDIDO WHERE COD_ITEMPEDIDO=" + id + "");
		} finally {
			dbcon.close();
		}
	}



}
