package br.com.wmw.comprastc.ui;

import br.com.wmw.comprastc.dados.Sincronizar;
import br.com.wmw.comprastc.util.Colors;
import br.com.wmw.comprastc.util.Images;
import br.com.wmw.comprastc.util.MaterialConstants;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.MainWindow;


public class TelaMenu extends Container {
	   private Container containerHeader, containerFooter;
	    private ImageControl logo;


	    public TelaMenu() {
	    }

	    public void initUI() {
	        Images.carregarImagens(fmH);
	        containerHeader = new Container();
	        add(containerHeader, LEFT, TOP, FILL, FILL);
	        setBackForeColors(Colors.WHITE, Colors.WHITE);

	        logo = new ImageControl(Images.logoHeader);
	        logo.scaleToFit = true;
	        logo.transparentBackground = true;
	        add(logo, CENTER - (MaterialConstants.COMPONENT_SPACING), TOP + MaterialConstants.BORDER_SPACING,
	                PARENTSIZE + 50, PARENTSIZE + 30);

	      
	        Container containerBody = new Container();
	        containerBody.transparentBackground = true;
	        containerHeader.add(containerBody, AFTER + MaterialConstants.BORDER_SPACING, TOP + 90, FILL - MaterialConstants.BORDER_SPACING,
	                PARENTSIZE + 720);

	        Button btPedidos = new Button("Pedidos em Aberto");
	        btPedidos.setBackForeColors(Colors.BLUE, Colors.WHITE);
	        containerBody.add(btPedidos, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
	        btPedidos.addPressListener((e) -> {
	            MainWindow.getMainWindow().swap(new ListarPedidoWindow());
	        });

	        Button btCadastrarPedido = new Button("Cadastrar Pedidos");
	        btCadastrarPedido.setBackForeColors(Colors.BLUE, Colors.WHITE);
	        containerBody.add(btCadastrarPedido, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
	        btCadastrarPedido.addPressListener((e) -> {
	            MainWindow.getMainWindow().swap(new ListarClienteWindow());
	        });

	        Button btSincronizar = new Button("Sincronizar dados");
	        btSincronizar.setBackForeColors(Colors.BLUE, Colors.WHITE);
	        containerBody.add(btSincronizar, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
	        btSincronizar.addPressListener((e) -> {
	            Sincronizar sincronizar = new Sincronizar();
	            sincronizar.run();
	        });
	        
	        Button btProdutos = new Button("Produtos");
	        btProdutos.setBackForeColors(Colors.BLUE, Colors.WHITE);
	        containerBody.add(btProdutos, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
	        btProdutos.addPressListener((e) -> {
		            MainWindow.getMainWindow().swap(new ListarProdutoWindow());
	        });
	        
	        Button btPedidosFinalizados = new Button("Pedidos Finalizados");
	        btPedidosFinalizados.setBackForeColors(Colors.BLUE, Colors.WHITE);
	        containerBody.add(btPedidosFinalizados, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
	        btPedidosFinalizados.addPressListener((e) -> {
	        	MainWindow.getMainWindow().swap(new ListPedidosFinalizados());
	        });
	        
	        Button btPedidosEnviados = new Button("Pedidos Enviados");
	        btPedidosEnviados.setBackForeColors(Colors.BLUE, Colors.WHITE);
	        containerBody.add(btPedidosEnviados, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
	        btPedidosEnviados.addPressListener((e) -> {
	        	MainWindow.getMainWindow().swap(new ListPedidosEnviados());
	        });
	        
	        containerFooter = new Container();
			add(containerFooter, CENTER - (MaterialConstants.COMPONENT_SPACING), BOTTOM + MaterialConstants.BORDER_SPACING,
	                PARENTSIZE + 50, PARENTSIZE + 15);
	        
	        
	        Button btSair = new Button("Sair");
	        btSair.setBackForeColors(Colors.BLUE, Colors.WHITE);
	        containerFooter.add(btSair, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
	        btSair.addPressListener((e) -> {
	        	MainWindow.exit(0);
	        });
	    }
	    
	    
	    
}

