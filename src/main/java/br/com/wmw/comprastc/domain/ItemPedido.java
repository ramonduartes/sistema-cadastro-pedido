package br.com.wmw.comprastc.domain;

public class ItemPedido {
	
	private Integer codigoItemPedido;
	private long codigoProduto;
	private Integer codigoPedido;
	private Integer quantidade = 0;
	private double precoUnitario;
	private double desconto = 0.0;
	private double totalItem;
	
	public ItemPedido() {
		
	}
	
	public ItemPedido(Integer codigoPedido, long CodigoProduto, Integer quantidade, double precoUnitario, double desconto) {
		setCodigoPedido(codigoPedido);
		setCodigoProduto(CodigoProduto);
		setQuantidade(quantidade);
		setPrecoUnitario(precoUnitario);
		setDesconto(desconto);
		
	}
	
	
	
	public Integer getCodigoItemPedido() {
		return codigoItemPedido;
	}



	public void setCodigoItemPedido(Integer codigoItemPedido) {
		this.codigoItemPedido = codigoItemPedido;
	}



	public ItemPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	
	public long getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public Integer getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(Integer codigoPedido) {
		this.codigoPedido = codigoPedido;
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
	
	

	

}
