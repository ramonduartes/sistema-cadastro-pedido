package br.com.wmw.comprastc.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dao.ClienteDAO;
import br.com.wmw.comprastc.dao.PedidoDAO;
import br.com.wmw.comprastc.domain.Cliente;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.service.ClienteService;
import br.com.wmw.comprastc.service.PedidoService;
import br.com.wmw.comprastc.util.Colors;
import br.com.wmw.comprastc.util.Images;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;

public class ListarPedidoWindow extends ScrollContainer {
	
	 private Container containerTopo, containerCliente, containerActions;
	    Button btn1, btn2, btnLixeira, btnLapis, btEditar, btDeletar, btEnviar;
	    private Label page, nome, lbPedido, lbStatus, lbDataEmissao, lbDataEntrega;
	    private List<Pedido> pedidos = new ArrayList<>();
	    private ClienteDAO clienteDAO = new ClienteDAO();
	    private PedidoService pedidoService = new PedidoService();
	    private PedidoDAO pedidoDAO = new PedidoDAO();
	    private Cliente cliente;
	    private Pedido pedido;

	    public ListarPedidoWindow() {
	    	
	    }
	    
	    public ListarPedidoWindow(int id) throws SQLException {
	        this.cliente = clienteDAO.detalharCliente(id);
	    }

	    public ListarPedidoWindow(Cliente cliente) {
	        
	    }

	    public void initUI() {


	        containerTopo = new Container();
	        add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

	        btn1 = new Button("Voltar");
	        btn1.setBorder(Container.BORDER_NONE);
	        containerTopo.add(btn1, LEFT, CENTER, PREFERRED, PREFERRED);
	        btn1.addPressListener((e) -> {
	            MainWindow.getMainWindow().swap(new TelaMenu());
	        });

	        page = new Label("Pedidos", CENTER, Colors.BLACK, true);
	        containerTopo.add(page, CENTER, CENTER, PREFERRED, PREFERRED);
	        
	        try {
				pedidos = pedidoService.listarPedidosAberto();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

	        for(Pedido pedido : pedidos) {

	        containerCliente = new Container();
	        containerCliente.borderColor = Colors.GRAY;
	        containerCliente.setBorderStyle(Container.BORDER_TOP);

	        containerCliente.setInsets(25, 25, 25, 25);
	        add(containerCliente, LEFT, AFTER, FILL, PARENTSIZE + 20);

	        
	        Cliente cliente = clienteDAO.findByCodigoCliente(pedido.getCodigoCliente());
			nome = new Label("Cliente: " + cliente.getNome());
			containerCliente.add(nome, SAME, AFTER, PREFERRED, PREFERRED);
	        
			lbPedido = new Label("Pedido #" + pedido.getCodigoPedido());
			containerCliente.add(lbPedido, SAME, AFTER, PREFERRED, PREFERRED);

	        lbStatus = new Label("Status: " + pedido.getStatusPedido());
	        containerCliente.add(lbStatus, SAME, AFTER, PREFERRED, PREFERRED);

	        lbDataEmissao = new Label("Data Emissão: " + pedido.getDataEmissao());
	        containerCliente.add(lbDataEmissao, SAME, AFTER, PREFERRED, PREFERRED);
	        
	        lbDataEntrega = new Label("Data de Entrega:" + pedido.getDataEntrega());
	        containerCliente.add(lbDataEntrega, SAME, AFTER, PREFERRED, PREFERRED);
	        
	        
	        
	        containerActions = new Container();
	        containerActions.setInsets(0, 25, 50, 0);
	        add(containerActions, RIGHT, SAME, PARENTSIZE + 35, PARENTSIZE + 22);
	        

	        btEditar = new Button(Images.iconeEditar);
	        btEditar.setBorder(Container.BORDER_NONE);
	        containerActions.add(btEditar, LEFT, AFTER, PREFERRED, PREFERRED);
	        btEditar.addPressListener((e) -> {
	            MainWindow.getMainWindow().swap(new PedidoResumeWindow(pedido));
	        });

	        btDeletar = new Button(Images.iconeDeletar);
	        btDeletar.setBorder(Container.BORDER_NONE);
	        containerActions.add(btDeletar, RIGHT, SAME, PREFERRED, PREFERRED);
	        btDeletar.addPressListener((e) -> {

	        	try {
					pedidoDAO.deletarPedido(pedido.getCodigoPedido());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

	            MessageBox mb = new MessageBox("Message", "Pedido excluído com sucesso.", new String[]{"Fechar"});
	            mb.popup();

	            MainWindow.getMainWindow().swap(new ListarPedidoWindow());

	        });
	        }
	        
	     
	    }
	  
}