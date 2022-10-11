package br.com.wmw.comprastc.dto;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {
	public Integer id;
	public String nome;
	public String email;
	public String telefone;
	public String tipoPessoa;
	public String cnpj_cpf;
	private List<PedidoDTO> pedidos = new ArrayList<>();
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<PedidoDTO> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoDTO> pedidos) {
		this.pedidos = pedidos;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	 @Override
	    public String toString() {
	        final StringBuffer sb = new StringBuffer("ClienteDTO{");
	        sb.append("id=").append(id);
	        sb.append(", nome='").append(nome).append('\'');
	        sb.append(", email='").append(email).append('\'');
	        sb.append(", telefone'").append(telefone).append('\'');
	        sb.append(", tipoPessoa='").append(tipoPessoa).append('\'');
	        sb.append(", cnpj_cpf='").append(cnpj_cpf).append('\'');
	        sb.append(", pedidos=").append(pedidos);
	        sb.append('}');
	        return sb.toString();
	    }
	
	

}
