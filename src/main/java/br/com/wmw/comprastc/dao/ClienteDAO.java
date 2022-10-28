package br.com.wmw.comprastc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dados.DatabaseManager;
import br.com.wmw.comprastc.domain.Cliente;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.dto.ClienteDTO;
import br.com.wmw.comprastc.exception.PersistenceException;
import totalcross.sql.Connection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;

public class ClienteDAO {

	public static void deletarCliente() throws SQLException {
		
		Connection connection = DatabaseManager.getConnection();
		try {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM CLIENTE");
		ps.executeUpdate();
		ps.close();
		} finally {
			connection.close();
		}
		
	}

	public static void inserirCliente(ClienteDTO cliente) throws SQLException, PersistenceException {

		Connection connection = DatabaseManager.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO CLIENTE (COD_CLIENTE, EMAIL, NOME, TELEFONE, TIPO_PESSOA, CNPJ_CPF) VALUES (?, ?, ?, ?, ?, ?)");
			try {		
				ps.setInt(1, cliente.getId());
				ps.setString(2, cliente.getEmail());
				ps.setString(3, cliente.getNome());
				ps.setString(4, cliente.getTelefone());
				ps.setString(5, cliente.getTipoPessoa());
				ps.setString(6, cliente.getCnpj_cpf());
				int linhasAfetadas = ps.executeUpdate();
				if(linhasAfetadas == 0) throw new PersistenceException("Erro ao inserir o cliente.");
				
			} finally {
				ps.close();
			}
		} finally {
			connection.close();
		}

	}

	public List<Cliente> buscarClientes() throws Exception {

		Connection connection = DatabaseManager.getConnection();
		List<Cliente> clientes = new ArrayList<>();
		try {
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM CLIENTE");
			try {
				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setId(rs.getInt("COD_CLIENTE"));
					cliente.setNome(rs.getString("NOME"));
					cliente.setTipoPessoa(rs.getString("TIPO_PESSOA"));
					cliente.setCnpj_cpf(rs.getString("CNPJ_CPF"));
					cliente.setTelefone("TELEFONE");
					cliente.setEmail(rs.getString("EMAIL"));
					clientes.add(cliente);
				}
			} finally {
				rs.close();
			}
		} finally {
			connection.close();
		}

		return clientes;

	}


	public Cliente findByCodigoCliente(int codigo) throws SQLException {
		Cliente cliente = new Cliente();

		Connection connection = DatabaseManager.getConnection();
		try {
			PreparedStatement pst = connection.prepareStatement("SELECT COD_CLIENTE, NOME FROM CLIENTE WHERE COD_CLIENTE = ?");
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

		return cliente;
	}

	public Cliente detalharCliente(int id) throws SQLException {
		Connection connection = DatabaseManager.getConnection();

		Cliente cliente = new Cliente();
		List<Pedido> pedidos = new ArrayList<>();
		try {
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM CLIENTE WHERE COD_CLIENTE=" + id + "");
			try {
				while (rs.next()) {
					cliente = new Cliente();
					cliente.setId(rs.getInt("COD_CLIENTE"));
				}
				rs = connection.createStatement().executeQuery("SELECT * FROM PEDIDO WHERE COD_CLIENTE=" + id + "");
				while (rs.next()) {
					pedidos.add(new Pedido());
					cliente.setPedidos(pedidos);
				}
			} finally {
				rs.close();
			}

		} finally {
			connection.close();
		}
		return cliente;

	}

	public Cliente[] findAllOnFormatArray() throws SQLException {
		final List<Cliente> clientesList = new ArrayList<>();
		Connection connection = DatabaseManager.getConnection();
		Statement st = connection.createStatement();
		try {
			ResultSet rs = st.executeQuery("SELECT COD_CLIENTE, NOME FROM CLIENTE");
			try {
				while(rs.next()) {
					clientesList.add(new Cliente(rs.getInt("COD_CLIENTE"), rs.getString("NOME")));
				}
			} finally {
				rs.close();
			}

		} finally {
			connection.close();
		}

		Cliente[] clientesArray = new Cliente[clientesList.size()];
		for(int i = 0; i < clientesList.size(); i++) {
			clientesArray[i] = clientesList.get(i);
		}
		return clientesArray;


	}		

}
