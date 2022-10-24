package br.com.wmw.comprastc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.wmw.comprastc.domain.ItemPedido;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.domain.Produto;
import br.com.wmw.comprastc.exception.PersistenceException;
import totalcross.util.InvalidDateException;

	public class TestesUnitarios {

	    private PedidoService pedidoService;
	    private ItemPedidoService itemPedidoService;
	    List<Produto> produtos = new ArrayList<>();

	    @BeforeEach
	    public void inicializa() {
	        this.pedidoService = new PedidoService();
	        this.itemPedidoService = new ItemPedidoService();
	    }
	    
	   
	    @Test
	    void deveriaDevolverVerdadeiroSeTemUmItem() {
	        Boolean resultado = pedidoService.verificaSeTemMinimoUmItem(criarPedidoComItem());
	        assertEquals(true, resultado);
	    }
	    
	    @Test
	    void deveriaRetornarDescontoItem() {
	    	ItemPedido item = itemPedidoService.calculaValorTotalItem("1", "10", criarPedidoComItem().getItens().get(0));
	    	 assertEquals(225.0, item.getTotalItem());
	    }
	    
	    @Test
	    void deveriaDevolverFalsoSeNaoTemUmItem() {
	        Boolean resultado = pedidoService.verificaSeTemMinimoUmItem(criarPedidoComDataEntrega("20/10/2022"));
	        assertEquals(false, resultado);
	    }
	    
	    @Test
	    void deveriaDevolverVerdadeiroSeTemUmProduto() {
	    	boolean resultado = itemPedidoService.verificaSeTemUmItemNoPedido(criarPedidoComItem().getItens().get(0));
	    	assertEquals(true, resultado);
	    }
	    
	    
	    @Test
	    void deveriaRetornarVerdadeiroDataIgualAHoje() throws PersistenceException, InvalidDateException {
	    	Boolean resultado = pedidoService.verificaDataEntrega("21/10/2022", criarPedidoComDataEntrega("20/10/2022"));
	    	assertEquals(true, resultado);
	    }
	    
	    @Test
	    void deveriaRetornarVerdadeiroDataMaiorQueHoje() throws PersistenceException, InvalidDateException {
	    	Boolean resultado = pedidoService.verificaDataEntrega("01/01/2023", criarPedidoComDataEntrega("01/01/2023"));
	    	assertEquals(true, resultado);
	    }
	    
	    @Test
	    void deveriaCalcularValorTotalPedido() {
	    	double resultado = pedidoService.calculaValorTotal(criarPedidoComDoisItens());
	    	assertEquals(850, resultado);
	    	
	    }
	   
	    private Pedido criarPedidoComDataEntrega(String dataEntrega) {
	        Pedido pedido = new Pedido(1, "10/10/2022", dataEntrega);
	        return pedido;
	    }
	    
	    private Pedido criarPedidoComDoisItens() {
	        Pedido pedido = new Pedido(1, "10/10/2022", "15/12/2022");
	        ItemPedido item1 = new ItemPedido();
	        item1.setTotalItem(500);
	        ItemPedido item2 = new ItemPedido();
	        item2.setTotalItem(350);
	        pedido.setItens(List.of(item1, item2));
	        return pedido;
	    }


	    private Pedido criarPedidoComItem() {
	        Pedido pedido = criarPedidoComDataEntrega("04/01/2023");
	        pedido.getItens().add(new ItemPedido(1, 1, 5, 250.0, 10.0));
	        return pedido;
	    }

	}