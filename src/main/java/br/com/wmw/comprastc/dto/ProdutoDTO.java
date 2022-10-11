package br.com.wmw.comprastc.dto;

public class ProdutoDTO {
	
	 	public Integer id;
	    public String nome;
	    public double preco;

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

	    public double getPreco() {
	        return preco;
	    }

	    public void setPreco(double preco) {
	        this.preco = preco;
	    }

	    @Override
	    public String toString() {
	        final StringBuffer sb = new StringBuffer("ProdutoDTO{");
	        sb.append("id=").append(id);
	        sb.append(", nome='").append(nome).append('\'');
	        sb.append(", preco=").append(preco);
	        sb.append('}');
	        return sb.toString();
	    }
	}