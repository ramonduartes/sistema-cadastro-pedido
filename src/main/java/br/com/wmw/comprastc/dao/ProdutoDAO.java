package br.com.wmw.comprastc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dados.DatabaseManager;
import br.com.wmw.comprastc.domain.Produto;
import br.com.wmw.comprastc.dto.ProdutoDTO;
import totalcross.sql.Connection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Vm;

public class ProdutoDAO {
	
	public static void deletarProduto() throws SQLException {
		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("DELETE FROM PRODUTO");
		ps.executeUpdate();
		ps.close();
	}

	public static void inserirProduto(ProdutoDTO produtoDTO) throws SQLException {
		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO PRODUTO (COD_PRODUTO, NOME, PRECO) VALUES (?, ?, ?)");
					ps.setInt(1, produtoDTO.getId());
					ps.setString(2, produtoDTO.getNome());
					ps.setDouble(3, produtoDTO.getPreco());
		
		ps.executeUpdate();
		ps.close();		
	}

	
	public List<Produto> buscarProdutos() {
		List<Produto> listaProdutos = new ArrayList<>();
		
		try {
			Connection connection = DatabaseManager.getConnection();
			try {
				Statement st = connection.createStatement();
				try {
					try(ResultSet rs = st.executeQuery("SELECT COD_PRODUTO, NOME, PRECO FROM PRODUTO")) {
						while (rs.next()) {
							listaProdutos.add(new Produto(rs.getInt("COD_PRODUTO"), rs.getString("NOME"), rs.getDouble("PRECO")));
						}
					}
				} finally {
					st.close();
				}
			} finally {
				connection.close();
			}
			
		} catch (final SQLException e) {
			Vm.debug(e.getMessage());
		}
		return listaProdutos;
	}
	
	public Produto buscarProdutoPorNome(String nome) {
		
		Produto produto = new Produto();
		try{
			Connection connection = DatabaseManager.getConnection();
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT COD_PRODUTO, NOME, PRECO FROM PRODUTO WHERE NOME = ?");
				try {
					ps.setString(1, nome);
					try(ResultSet rs = ps.executeQuery()){
						while(rs.next()) {
							produto.setCodigo(rs.getLong("COD_PRODUTO"));
							produto.setNome(rs.getString("NOME"));
							produto.setPreco(rs.getDouble("PRECO"));
						}
					}
				} finally {
					ps.close();
				}
			} finally {
				connection.close();
			}
		}catch(final SQLException e) {
			Vm.debug(e.getMessage());
		}
		return produto;
	}

	public Produto buscaProdutoPorId(long id ) throws SQLException {
		Produto produto = new Produto();
		try{
			Connection connection = DatabaseManager.getConnection();
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT COD_PRODUTO, NOME, PRECO FROM PRODUTO WHERE COD_PRODUTO = ?");
				try {
					ps.setLong(1, id);
					try(ResultSet rs = ps.executeQuery()){
						while(rs.next()) {
							produto.setCodigo(rs.getLong("COD_PRODUTO"));
							produto.setNome(rs.getString("NOME"));
							produto.setPreco(rs.getDouble("PRECO"));
						}
					}
				} finally {
					ps.close();
				}
			} finally {
				connection.close();
			}
		}catch(final SQLException e) {
			Vm.debug(e.getMessage());
		}
		return produto;
	}

	 
}
