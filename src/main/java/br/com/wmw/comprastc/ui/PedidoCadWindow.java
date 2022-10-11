package br.com.wmw.comprastc.ui;

import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.domain.ItemPedido;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.exception.PersistenceException;
import br.com.wmw.comprastc.service.PedidoService;
import br.com.wmw.comprastc.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;

	public class PedidoCadWindow extends ScrollContainer {
		
	    private Container containerTopo, containerCadastro, containerActions;
	    private Button btFechar, btAdicionar, btnSalvar;
	    private Label lbPedido, lbValorTotal, lbDataEntrega, lbProduto;
	    private Edit editDataEntrega;
	    private Pedido pedido;
	    private List<ItemPedido> itens = new ArrayList<>();
	    private PedidoService pedidoService = new PedidoService();
	    
	    public PedidoCadWindow(Pedido pedido) {
	        this.pedido = pedido;
	    }

	    public void initUI() {
	    	
	    	itens = pedido.getItens();

	        containerTopo = new Container();
	        add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

	        btFechar = new Button("Voltar");
	        btFechar.setBorder(Container.BORDER_NONE);
	        containerTopo.add(btFechar, LEFT, CENTER, PREFERRED, PREFERRED);
	        btFechar.addPressListener((e) -> {
	            MainWindow.getMainWindow().swap(new PedidoResumeWindow(pedido));
	        });

	        lbPedido = new Label("Pedido", CENTER, Colors.BLACK, true);
	        containerTopo.add(lbPedido, CENTER, CENTER, PREFERRED, PREFERRED);

	        containerCadastro = new Container();
	        containerCadastro.borderColor = Colors.GRAY;
	        containerCadastro.setBorderStyle(Container.BORDER_TOP);
	        containerCadastro.setInsets(25, 25, 25, 25);
	        add(containerCadastro, LEFT, AFTER, FILL, FILL);

	        lbValorTotal = new Label("Valor Total do Pedido: R$ " + pedido.getTotalPedido() );
	        containerCadastro.add(lbValorTotal, LEFT, AFTER, PREFERRED, PREFERRED);

	        lbProduto = new Label(pedidoService.retornaListaProdutos(pedido));
	        containerCadastro.add(lbProduto, LEFT, AFTER, PREFERRED, PREFERRED);

	        lbDataEntrega = new Label("Data Entrega: " );
	        containerCadastro.add(lbDataEntrega, LEFT, AFTER, PREFERRED, PREFERRED);

	        editDataEntrega = new Edit("99/99/9999");
			editDataEntrega.setMode(Edit.DATE, true);
			editDataEntrega.setValidChars("0123456789");
			editDataEntrega.lineColor = Colors.WHITE;
	        containerCadastro.add(editDataEntrega, SAME, AFTER, PREFERRED, PREFERRED);
	        editDataEntrega.addPressListener((e) -> {
	        pedido.setDataEntrega(editDataEntrega.getText());
	        });
	     

	        containerActions = new Container();
	        containerActions.setInsets(25, 25, 25, 25);
	        add(containerActions, LEFT, BOTTOM, FILL, PARENTSIZE + 15);

	        btAdicionar = new Button("Adicionar");
	        btAdicionar.setBackForeColors(Colors.BLUE, Colors.WHITE);
	        btAdicionar.borderColor = Colors.GRAY;
	        btAdicionar.setBorder(Container.BORDER_ROUNDED);

	        containerActions.add(btAdicionar, LEFT, CENTER, PARENTSIZE + 52 , PARENTSIZE + 95);
	        btAdicionar.addPressListener((e) -> {
	            MainWindow.getMainWindow().swap(new TelaCadastroPedido(pedido));
	        });

	        btnSalvar = new Button("Salvar Pedido");
	        btnSalvar.setBackForeColors(Colors.BLUE, Colors.WHITE);
	        btnSalvar.setBorder(Container.BORDER_ROUNDED);
	        containerActions.add(btnSalvar, RIGHT, CENTER, PARENTSIZE + 47 , PARENTSIZE + 95);
	        btnSalvar.addPressListener((e) -> {
	        	
					try {
						if (pedidoService.verificaDataEntrega(editDataEntrega.getText(), pedido)) {
						pedidoService.inserirPedido(pedido);
						MainWindow.getMainWindow().swap(new PedidoViewWindow(pedido));
						}
					} catch (PersistenceException e1) {
						e1.printStackTrace();
					}
	        });


	    }
	}
	

