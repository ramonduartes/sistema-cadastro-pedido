package br.com.wmw.comprastc.dto;

import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {
	
	private Integer codigoPedido;
	private Integer codigoCliente;
	private String dataEmissao;
	private String dataEntrega;
	private double totalPedido;
	private String statusPedido;
	private List<ItemPedidoDTO> itens = new ArrayList<>();

	public PedidoDTO() {
		
	}
	    
	    
	    public void addItem(ItemPedidoDTO itemPedidoDTO){
	        itens.add(itemPedidoDTO);
	    }
	    public void addItems(List<ItemPedidoDTO> itemPedidoDTOS){
	        itens.addAll(itemPedidoDTOS);
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
		
		public String getStatusPedido() {
			return statusPedido;
		}


		public void setStatusPedido(String statusPedido) {
			this.statusPedido = statusPedido;
		}


		public List<ItemPedidoDTO> getItens() {
	        return itens;
	    }

	    public void setItens(List<ItemPedidoDTO> itens) {
	        this.itens = itens;
	    }
	

		@Override
		public String toString() {
			return "PedidoDTO [codigoPedido=" + codigoPedido + ", codigoCliente=" + codigoCliente + ", dataEmissao="
					+ dataEmissao + ", dataEntrega=" + dataEntrega + ", totalPedido=" + totalPedido + ", statusPedido="
					+ statusPedido + ", itens=" + itens + "]";
		}
		
		

	   

}
