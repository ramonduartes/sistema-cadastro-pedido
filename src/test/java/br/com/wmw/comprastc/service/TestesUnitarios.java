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

	public class TestesUnitarios {

	    private PedidoService pedidoService;
	    private ItemPedidoService itemPedidoService;
	    private List<Produto> produtos = new ArrayList<>();

	    @BeforeEach
	    public void inicializa() {
	        this.pedidoService = new PedidoService();
	        this.itemPedidoService = new ItemPedidoService();
	    }
	    
	    @Test
	    void deveriaDevolverVerdadeiroSeTemUmItem() {
	        Boolean resultado = pedidoService.verificaSeTemMinimoUmItem(criarItem());
	        assertEquals(true, resultado);
	    }
	    
	    @Test
	    void deveriaRetornarDescontoItem() {
	    	ItemPedido item = itemPedidoService.calculaValorTotalItem("1", "10", criarItem().getItens().get(0));
	    	 assertEquals(225.0, item.getTotalItem());
	    }
	    
	    @Test
	    void deveriaDevolverFalsoSeTemUmItem() {
	        Boolean resultado = pedidoService.verificaSeTemMinimoUmItem(criarPedido("01/01/2022"));
	        assertEquals(false, resultado);
	    }
	    
	    @Test
	    void deveriaDevolverVerdadeiroSeTemUmProduto() {
	    	boolean resultado = itemPedidoService.verificaItem(criarItem().getItens().get(0));
	    	assertEquals(true, resultado);
	    }
	    
	    
	    @Test
	    void deveriaRetornarVerdadeiroDataIgualAHoje() throws PersistenceException {
	    	Boolean resultado = pedidoService.verificaDataEntrega("11/10/2022", criarPedido("11/10/2022"));
	    	assertEquals(true, resultado);
	    }
	    
	    @Test
	    void deveriaRetornarVerdadeiroDataMaiorQueHoje() throws PersistenceException {
	    	Boolean resultado = pedidoService.verificaDataEntrega("01/01/2023", criarPedido("01/01/2023"));
	    	assertEquals(true, resultado);
	    }
	    
//	    @Test
//	    void deveriaCalcularValorTotalPedido() {
//	    	double resultado = pedidoService.calculaValorTotal(criarPedido("01/01/2023"));
//	    	
//	    }
	   
	    private Pedido criarPedido(String dataEntrega) {
	        Pedido pedido = new Pedido(1, "10/10/2022", "15/12/2022");
	        return pedido;
	    }


	    private Pedido criarItem() {
	        Pedido pedido = criarPedido("04/01/2023");
	        pedido.getItens().add(new ItemPedido(1, 1, 5, 250.0, 10.0));
	        return pedido;
	    }

	}