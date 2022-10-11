package br.com.wmw.comprastc.domain;

import java.util.ArrayList;
import java.util.List;

import totalcross.sys.Settings;
import totalcross.util.Date;

public class Pedido  {
	
	private Integer codigoPedido;
	private Integer codigoCliente;
	private String dataEmissao;
	private String dataEntrega;
	private double totalPedido;
	private StatusPedido statusPedido = StatusPedido.ABERTO;
	private int sincronizado = 0;
	private List<ItemPedido> itens =  new ArrayList<>();
	
	public Pedido() {
		dataEmissao = new Date().toString(Settings.DATE_DMY, "/");
	}
	
	public Pedido(Integer codigoPedido, String dataEmissao, String dataEntrega) {
		setCodigoPedido(codigoPedido);
		setDataEmissao(dataEmissao);
		setDataEntrega(dataEntrega);
	}
	
	public Integer getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(Integer codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
	public void addItem(ItemPedido item) {
		itens.add(item);
	}

	public int getSincronizado() {
		return sincronizado;
	}

	public void setSincronizado(int sincronizado) {
		this.sincronizado = sincronizado;
	}
	
	
}
