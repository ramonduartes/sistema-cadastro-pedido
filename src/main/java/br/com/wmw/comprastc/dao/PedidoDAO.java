package br.com.wmw.comprastc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dados.DatabaseManager;
import br.com.wmw.comprastc.domain.ItemPedido;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.domain.StatusPedido;
import br.com.wmw.comprastc.dto.PedidoDTO;
import br.com.wmw.comprastc.exception.PersistenceException;
import totalcross.sql.Connection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Vm;

public class PedidoDAO {



	public void inserirPedido(Pedido pedido) throws SQLException, PersistenceException {
		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO PEDIDO (DATA_EMISSAO, DATA_ENTREGA, TOTAL_PEDIDO, COD_CLIENTE, STATUS_PEDIDO) VALUES (?, ?, ?, ?, ?)");
		ps.setString(1, pedido.getDataEmissao());
		ps.setString(2, pedido.getDataEntrega());
		ps.setDouble(3, pedido.getTotalPedido());
		ps.setInt(4, pedido.getCodigoCliente());
		ps.setString(5, pedido.getStatusPedido().toString());
		int affectedRows = ps.executeUpdate();
		if(affectedRows == 0) throw new PersistenceException("Erro ao inserir o pedido.");
		ps.close();
	}

	public void atualizarPedido(Pedido pedido) throws SQLException {
		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE PEDIDO SET DATA_ENTREGA = ?, TOTAL_PEDIDO = ?, COD_CLIENTE = ?, STATUS_PEDIDO = ? WHERE COD_PEDIDO = ?");
		ps.setString(1, pedido.getDataEntrega());
		ps.setDouble(2, pedido.getTotalPedido());
		ps.setInt(3, pedido.getCodigoCliente());
		ps.setString(4, pedido.getStatusPedido().toString());
		ps.setInt(5, pedido.getCodigoPedido());
		ps.executeUpdate();
		ps.close();
	}



	public int retornaExisteId(Integer codigoPedido) throws SQLException {
		Connection dbcon = DatabaseManager.getConnection();

		int id_retorno = -1;
		ResultSet rsTemp = null;
		try {
			rsTemp = dbcon.createStatement().executeQuery("select COD_PEDIDO as COD_PEDIDO from pedido where COD_PEDIDO =" + codigoPedido + "");
			while (rsTemp.next()) {
				id_retorno = rsTemp.getInt("COD_PEDIDO");
			}
		} finally {
			dbcon.close();
		}

		return id_retorno;

	}

	public void deletarPedido(Integer codigoPedido) throws SQLException {
		Connection connection = DatabaseManager.getConnection();
		try {
			connection.createStatement().execute("DELETE FROM PEDIDO WHERE COD_PEDIDO=" + codigoPedido + "");
		} finally {
			connection.close();
		}
	}

	public List<Pedido> listarPedidoPorCodigo(Integer id) throws SQLException {
		Connection connection = DatabaseManager.getConnection();
		List<Pedido> pedidos = new ArrayList<Pedido>();
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM PEDIDO WHERE COD_CLIENTE =" + id + "");
		Pedido pedido = new Pedido();
		while (rs.next()) {
			pedidos.add(pedido);
			connection.close();

		}
		return pedidos;

	}

	public List<Pedido> findAll() {
		List<Pedido> pedidosList = new ArrayList<>();
		try {
			Connection connection = DatabaseManager.getConnection();
			try{
				Statement st = connection.createStatement();
				try {
					ResultSet rs = st.executeQuery("SELECT COD_PEDIDO, COD_CLIENTE, STATUS_PEDIDO, DATA_ENTREGA, DATA_EMISSAO, TOTAL_PEDIDO"
							+ " FROM PEDIDO");
					while(rs.next()) {
						Pedido pedido = new Pedido();
						pedido.setCodigoPedido(rs.getInt("COD_PEDIDO"));
						pedido.setCodigoCliente(rs.getInt("COD_CLIENTE"));
						pedido.setStatusPedido(StatusPedido.getByNome(rs.getString("STATUS_PEDIDO")));
						pedido.setDataEntrega(rs.getString("DATA_ENTREGA"));
						pedido.setDataEmissao(rs.getString("DATA_EMISSAO"));
						pedido.setTotalPedido(rs.getDouble("TOTAL_PEDIDO"));

						List<ItemPedido> itensPedido = new ItemPedidoDAO().findByCodigoPedido(pedido.getCodigoPedido());
						pedido.setItens(itensPedido);

						pedidosList.add(pedido);
					}

				} finally {
					st.close();
				}

			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			Vm.debug(e.getMessage());
		}

		return pedidosList;	
	}
	

	private static void updatePedidoEnviado(Pedido pedido) throws PersistenceException {
		pedido.setStatusPedido(StatusPedido.ENVIADO);
		new PedidoDAO().update(pedido);
	}

	private void update(Pedido pedido) throws PersistenceException {
		try {
			Connection connection = DatabaseManager.getConnection();
			try {
				PreparedStatement ps = connection.
						prepareStatement("UPDATE PEDIDO SET DATA_ENTREGA = ?, COD_CLIENTE = ?, STATUS_PEDIDO = ?, TOTAL_PEDIDO = ? WHERE COD_PEDIDO = ?");
				try {
					ps.setString(1, pedido.getDataEntrega());
					ps.setLong(2, pedido.getCodigoCliente());
					ps.setLong(3, pedido.getStatusPedido().ordinal());
					ps.setDouble(4, pedido.getTotalPedido());
					ps.setDouble(5, pedido.getCodigoPedido());

					int affectedRows = ps.executeUpdate();
					if(affectedRows == 0) throw new PersistenceException("Erro ao atualizar o pedido.");
				} finally {
					ps.close();
				}

			} finally {
				connection.close();
			}

		} catch(SQLException e) {
			Vm.debug(e.getMessage());
		}
	}
	public Pedido detalharPedido(int id) throws SQLException {
		Connection dbcon = DatabaseManager.getConnection();

		Pedido pedido = null;
		ResultSet rs = null;

		rs = dbcon.createStatement().executeQuery("SELECT * FROM PEDIDO WHERE COD_PEDIDO=" + id + "");
		while (rs.next()) {
			pedido = new Pedido();
			pedido.setCodigoCliente(rs.getInt("COD_CLIENTE"));
			pedido.setCodigoPedido(rs.getInt("COD_PEDIDO"));
			pedido.setStatusPedido(StatusPedido.getByNome(rs.getString("STATUS_PEDIDO")));
			pedido.setDataEmissao(rs.getString("DATA_EMISSAO"));
			pedido.setDataEntrega(rs.getString("DATA_ENTREGA"));
			pedido.setTotalPedido(rs.getDouble("TOTAL_PEDIDO"));

		}


		return pedido;

	}

	public int retornaUltimoId() throws SQLException {
		Connection dbcon = DatabaseManager.getConnection();

		int id = 0;
		ResultSet rsTemp = null;
		try {
			rsTemp = dbcon.createStatement().executeQuery("SELECT MAX(COD_PEDIDO) AS COD_PEDIDO FROM PEDIDO");
			while (rsTemp.next()) {
				id = rsTemp.getInt("COD_PEDIDO");
			}
		} finally {
			dbcon.close();
		}

		return id;

	}

	public void fecharPedido(Pedido pedido) throws SQLException {
		Connection dbcon = DatabaseManager.getConnection();

		try {
			dbcon.createStatement().execute("UPDATE PEDIDO SET STATUS_PEDIDO='"
					+ StatusPedido.FECHADO
					+ "' WHERE COD_PEDIDO=" + pedido.getCodigoPedido() + "");
		} finally {
			dbcon.close();
		}
	}

	public List<Pedido> findAllByStatus(StatusPedido statusPedido) throws SQLException {
		List<Pedido> pedidosList = new ArrayList<>();
		Connection connection = DatabaseManager.getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT COD_PEDIDO, COD_CLIENTE, STATUS_PEDIDO, DATA_ENTREGA, DATA_EMISSAO, TOTAL_PEDIDO FROM PEDIDO WHERE STATUS_PEDIDO = ?");
		pst.setString(1, statusPedido.toString());
		try (ResultSet rs = pst.executeQuery()){
			while(rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setCodigoPedido(rs.getInt("COD_PEDIDO"));
				pedido.setCodigoCliente(rs.getInt("COD_CLIENTE"));
				pedido.setStatusPedido(StatusPedido.getByNome(rs.getString("STATUS_PEDIDO")));
				pedido.setDataEntrega(rs.getString("DATA_ENTREGA"));
				pedido.setDataEmissao(rs.getString("DATA_EMISSAO"));
				pedido.setTotalPedido(rs.getDouble("TOTAL_PEDIDO"));

				List<ItemPedido> itensPedido = new ItemPedidoDAO().findByCodigoPedido(pedido.getCodigoPedido());
				pedido.setItens(itensPedido);

				pedidosList.add(pedido);
			}
		} catch (final SQLException e) {
			Vm.debug(e.getMessage());
		} finally {
			pst.close();
			connection.close();
		}
		return pedidosList;

	}

	 public List<PedidoDTO> listarPedidoDTONaoSincronizados() throws SQLException {
	        List<PedidoDTO> pedidos = new ArrayList<>();
	        Connection dbcon = DatabaseManager.getConnection();
	        try {
	            ResultSet rs = dbcon.createStatement().executeQuery("SELECT COD_PEDIDO, COD_CLIENTE, STATUS_PEDIDO, DATA_ENTREGA, DATA_EMISSAO, TOTAL_PEDIDO FROM PEDIDO WHERE SINCRONIZADO = 0");
	            try {
	                while (rs.next()) {
	                    PedidoDTO pedido = new PedidoDTO();
	                    pedido.setCodigoPedido(rs.getInt("COD_PEDIDO"));
	    				pedido.setCodigoCliente(rs.getInt("COD_CLIENTE"));
	    				pedido.setStatusPedido(rs.getString("STATUS_PEDIDO"));
	    				pedido.setDataEntrega(rs.getString("DATA_ENTREGA"));
	    				pedido.setDataEmissao(rs.getString("DATA_EMISSAO"));
	    				pedido.setTotalPedido(rs.getDouble("TOTAL_PEDIDO"));
	                    pedidos.add(pedido);
	                }
	            } finally {
	                rs.close();
	            }
	        } finally {
	            dbcon.close();
	        }

	        return pedidos;

	    }


}

