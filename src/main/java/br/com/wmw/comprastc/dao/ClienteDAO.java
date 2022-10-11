package br.com.wmw.comprastc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dados.DatabaseManager;
import br.com.wmw.comprastc.domain.Cliente;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.dto.ClienteDTO;
import totalcross.sql.Connection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Vm;

public class ClienteDAO {
	
	public static void deletarCliente() throws SQLException {
		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("DELETE FROM CLIENTE");
		ps.executeUpdate();
		ps.close();
	}

	public static void inserirCliente(ClienteDTO cliente) throws SQLException {
		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO CLIENTE (COD_CLIENTE, EMAIL, NOME, TELEFONE, TIPO_PESSOA, CNPJ_CPF) VALUES (?, ?, ?, ?, ?, ?)");
				ps.setInt(1, cliente.getId());
				ps.setString(2, cliente.getEmail());
				ps.setString(3, cliente.getNome());
				ps.setString(4, cliente.getTelefone());
				ps.setString(5, cliente.getTipoPessoa());
				ps.setString(6, cliente.getCnpj_cpf());
		
		ps.executeUpdate();
		ps.close();
		
		
	}
	
	public List<Cliente> buscarClientes() throws Exception {

		Connection connection = DatabaseManager.getConnection();
        List<Cliente> clientes = new ArrayList<>();
        ResultSet rsTemp = null;
        try {
            rsTemp = connection.createStatement().executeQuery("SELECT * FROM CLIENTE");
            while (rsTemp.next()) {
                Cliente cliente = new Cliente(rsTemp);
                clientes.add(cliente);
            }
        } finally {
            connection.close();
        }

        return clientes;

    }

	
	public Cliente buscarPorCodigo(int id) throws SQLException {
		Cliente cliente = new Cliente();
		Connection connection = DatabaseManager.getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT COD_CLIENTE, NOME FROM CLIENTE WHERE COD_CLIENTE = ?");
		pst.setInt(1, id);
		try (ResultSet rs = pst.executeQuery()){
			while(rs.next()) {
				cliente.setId(rs.getInt("COD_CLIENTE"));
				cliente.setNome(rs.getString("NOME"));
			}
				pst.close();
				connection.close();
			} catch (final SQLException e) {
				Vm.debug(e.getMessage());
			}
			return cliente;
		
	}
	
	public Cliente findByCodigoCliente(int codigo) {
		Cliente cliente = new Cliente();
		try{
			Connection connection = DatabaseManager.getConnection();
			try {
				final PreparedStatement pst = connection.prepareStatement("SELECT COD_CLIENTE, NOME FROM CLIENTE WHERE COD_CLIENTE = ?");
				try {
					pst.setInt(1, codigo);
					try(ResultSet rs = pst.executeQuery()){
						while(rs.next()) {
							cliente.setId(rs.getInt("COD_CLIENTE"));
							cliente.setNome(rs.getString("NOME"));
						}
					}
				} finally {
					pst.close();
				}
			} finally {
				connection.close();
			}
		}catch(final SQLException e) {
			Vm.debug(e.getMessage());
		}
		return cliente;
	}

	public Cliente detalharCliente(int id) throws SQLException {
        Connection dbcon = DatabaseManager.getConnection();

        Cliente cliente = null;
        List<Pedido> pedidos = new ArrayList<>();
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select * from cliente where COD_CLIENTE=" + id + "");
            while (rsTemp.next()) {
                 cliente = new Cliente(rsTemp);
            }
            rsTemp = dbcon.createStatement().executeQuery("select * from pedido where COD_CLIENTE=" + id + "");
            while (rsTemp.next()) {
                pedidos.add(new Pedido());
                cliente.setPedidos(pedidos);
            }


        } finally {
            dbcon.close();
        }
        return cliente;

	}
	
	public Cliente detalharClienteById(int id) throws SQLException {
		Statement st = DatabaseManager.getConnection().createStatement();
		Cliente cliente = new Cliente();
		List<Pedido> pedidos = new ArrayList<Pedido>();
		ResultSet rs = st.executeQuery("SELECT * FROM CLIENTE WHERE COD_CLIENTE = " + id +"");
		while (rs.next()) {
			pedidos.add(new Pedido());
			cliente.setPedidos(pedidos);
			
		}
		return cliente;
	}
	
	public Cliente[] findAllOnFormatArray() {
		final List<Cliente> clientesList = new ArrayList<>();
		try{
			Connection connection = DatabaseManager.getConnection();
			try {
				Statement st = connection.createStatement();
				try(ResultSet rs = st.executeQuery("SELECT COD_CLIENTE, NOME FROM CLIENTE")){
					while(rs.next()) {
						clientesList.add(new Cliente(rs.getInt("COD_CLIENTE"), rs.getString("NOME")));
					}
				}
			} finally {
				connection.close();
			}
		} catch (final SQLException e) {
			Vm.debug(e.getMessage());
		}
		Cliente[] clientesArray = new Cliente[clientesList.size()];
		for(int i = 0; i < clientesList.size();i++) {
			clientesArray[i] = clientesList.get(i);
		}
		return clientesArray;
	}


}