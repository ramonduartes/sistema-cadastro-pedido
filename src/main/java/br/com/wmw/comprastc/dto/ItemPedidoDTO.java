package br.com.wmw.comprastc.dto;

public class ItemPedidoDTO {

	private int id;
    private int idProduto;
    private int idPedido;
    private Integer quantidade;
    private double precoUnitario;
    private double desconto;
    private double totalItem;
    
    public ItemPedidoDTO(int id, int idProduto, int idPedido, Integer quantidade, double precoUnitario, double Desconto, double totalItem) {
    	setId(id);
    	setIdProduto(idProduto);
    	setIdPedido(idPedido);
    	setQuantidade(quantidade);
    	setPrecoUnitario(precoUnitario);
    	setDesconto(Desconto);
    	setTotalItem(totalItem);
    }
    
    public ItemPedidoDTO() {
    	
    }
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(double totalItem) {
		this.totalItem = totalItem;
	}

	@Override
	public String toString() {
		return "ItemPedidoDTO [cod_itempedido=" + id + ", cod_produto=" + idProduto + ", cod_pedido="
				+ idPedido + ", quantidade=" + quantidade + ", precoUnitario=" + precoUnitario + ", desconto="
				+ desconto + ", totalItem=" + totalItem + "]";
	}

  

    
}
