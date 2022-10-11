package br.com.wmw.comprastc.dto;

public class ItemPedidoDTO {

	private int cod_itempedido;
    private int cod_produto;
    private int cod_pedido;
    private Integer quantidade;
    private double precoUnitario;
    private double desconto;
    private double totalItem;
    
    public ItemPedidoDTO(int cod_itempedido, int cod_produto, int cod_pedido, Integer quantidade, double precoUnitario, double Desconto, double totalItem) {
    	setCod_itempedido(cod_itempedido);
    	setCod_produto(cod_produto);
    	setCod_pedido(cod_pedido);
    	setQuantidade(quantidade);
    	setPrecoUnitario(precoUnitario);
    	setDesconto(Desconto);
    	setTotalItem(totalItem);
    }
    
    public ItemPedidoDTO() {
    	
    }
    
    public ItemPedidoDTO(int cod_pedido) {
		this.cod_pedido = cod_pedido;
	}
    

	public int getCod_itempedido(int cod_itempedido) {
		return cod_itempedido;
	}
	public void setCod_itempedido(int cod_itempedido) {
		this.cod_itempedido = cod_itempedido;
	}
	public int getCod_produto(int cod_produto) {
		return cod_produto;
	}
	public void setCod_produto(int cod_produto) {
		this.cod_produto = cod_produto;
	}
	public int getCod_pedido(int cod_pedido) {
		return cod_pedido;
	}
	public void setCod_pedido(int cod_pedido) {
		this.cod_pedido = cod_pedido;
	}
	public Integer getQuantidade(Integer quantidade) {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrecoUnitario(double precoUnitario) {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public double getDesconto(double Desconto) {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public double getTotalItem(double totalItem) {
		return totalItem;
	}
	public void setTotalItem(double totalItem) {
		this.totalItem = totalItem;
	}

  

    
}
