package br.com.wmw.comprastc.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import totalcross.sql.ResultSet;

public class Cliente {

	public Integer id;
	public String nome;
	public String tipoPessoa;
	public String cnpj_cpf;
	public String telefone;
	public String email;
	public List<Pedido> pedidos = new ArrayList<>();

	public Cliente(ResultSet rsTemp) throws SQLException {
		setId(rsTemp.getInt("COD_CLIENTE"));
		setNome(rsTemp.getString("NOME"));
		setTipoPessoa(rsTemp.getString("TIPO_PESSOA"));
		setCnpj_cpf(rsTemp.getString("CNPJ_CPF"));
		setTelefone(rsTemp.getString("TELEFONE"));
		setEmail(rsTemp.getString("EMAIL"));
    }

	public Cliente(int id, String nome) {
		this.id = id;
		this.nome = nome;
}
	public Cliente() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCnpj_cpf() {
		return cnpj_cpf;
	}

	public void setCnpj_cpf(String cnpj_cpf) {
		this.cnpj_cpf = cnpj_cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	
}

	