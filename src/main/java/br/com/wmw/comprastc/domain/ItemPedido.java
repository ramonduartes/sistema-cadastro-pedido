package br.com.wmw.comprastc.domain;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(codigoItemPedido, codigoPedido, codigoProduto, desconto, precoUnitario, quantidade,
				totalItem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(codigoItemPedido, other.codigoItemPedido)
				&& Objects.equals(codigoPedido, other.codigoPedido) && codigoProduto == other.codigoProduto
				&& Double.doubleToLongBits(desconto) == Double.doubleToLongBits(other.desconto)
				&& Double.doubleToLongBits(precoUnitario) == Double.doubleToLongBits(other.precoUnitario)
				&& Objects.equals(quantidade, other.quantidade)
				&& Double.doubleToLongBits(totalItem) == Double.doubleToLongBits(other.totalItem);
	}
	
}
