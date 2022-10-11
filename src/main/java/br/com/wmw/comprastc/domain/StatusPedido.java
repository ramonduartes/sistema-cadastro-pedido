package br.com.wmw.comprastc.domain;

public enum StatusPedido {

ABERTO, FECHADO, ENVIADO, REJEITADO;

	public static StatusPedido getByNome(String nome) {
		StatusPedido statusPedido = null;
	     for (StatusPedido s : values()) {
	           if (s.toString().equals(nome)) {
	                 statusPedido = s;
	           }
	     }
	     return statusPedido;
	}
}
