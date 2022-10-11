package br.com.wmw.comprastc.domain;

public class Produto {
	private long id;
	private String nome;
	private double preco;
	
	public Produto(long id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Produto() {
	}

	public long getCodigo() {
		return id;
	}

	public void setCodigo(long codigo) {
		this.id = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}


