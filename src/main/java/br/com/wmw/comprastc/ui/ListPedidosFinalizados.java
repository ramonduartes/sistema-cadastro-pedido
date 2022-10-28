package br.com.wmw.comprastc.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.api.PedidoAPI;
import br.com.wmw.comprastc.dao.ClienteDAO;
import br.com.wmw.comprastc.dao.PedidoDAO;
import br.com.wmw.comprastc.domain.Cliente;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.exception.PersistenceException;
import br.com.wmw.comprastc.service.PedidoService;
import br.com.wmw.comprastc.util.Colors;
import br.com.wmw.comprastc.util.Images;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;

public class ListPedidosFinalizados extends ScrollContainer {

	private Container containerTopo, containerPedidos, containerActions;
	private Button btVoltar, btEditar, btDeletar, btSincronizar;
	private Label lbPedidos, lbNome, lbPedido, lbStatus, lbDataEmissao, lbDataEntrega;
	private List<Pedido> pedidos = new ArrayList<>();
	private ClienteDAO clienteDAO = new ClienteDAO();
	private PedidoDAO pedidoDAO = new PedidoDAO();
	private PedidoService pedidoService = new PedidoService();
	Cliente cliente = new Cliente();
	private PedidoAPI pedidoAPI = new PedidoAPI();

	public ListPedidosFinalizados() {

	}

	public ListPedidosFinalizados(int id) throws SQLException {
		this.cliente = clienteDAO.detalharCliente(id);
	}

	public ListPedidosFinalizados(Cliente cliente) {

	}

	public void initUI() {


		containerTopo = new Container();
		add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

		btVoltar = new Button("Voltar");
		btVoltar.setBorder(Container.BORDER_NONE);
		containerTopo.add(btVoltar, LEFT, CENTER, PREFERRED, PREFERRED);
		btVoltar.addPressListener((e) -> {
			MainWindow.getMainWindow().swap(new TelaMenu());
		});

		lbPedidos = new Label("Pedidos", CENTER, Colors.BLACK, true);
		containerTopo.add(lbPedidos, CENTER, CENTER, PREFERRED, PREFERRED);

		try {
			pedidos = pedidoService.listarPedidosFechados();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}



		for(Pedido pedido : pedidos) {

			containerPedidos = new Container();
			containerPedidos.borderColor = Colors.GRAY;
			containerPedidos.setBorderStyle(Container.BORDER_TOP);

			containerPedidos.setInsets(25, 25, 25, 25);
			add(containerPedidos, LEFT, AFTER, FILL, PARENTSIZE + 20);


			try {
				cliente = clienteDAO.findByCodigoCliente(pedido.getCodigoCliente());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			lbNome = new Label("Cliente: " + cliente.getNome());
			containerPedidos.add(lbNome, SAME, AFTER, PREFERRED, PREFERRED);

			lbPedido = new Label("Pedido #" + pedido.getCodigoPedido());
			containerPedidos.add(lbPedido, SAME, AFTER, PREFERRED, PREFERRED);

			lbStatus = new Label("Status: " + pedido.getStatusPedido());
			containerPedidos.add(lbStatus, SAME, AFTER, PREFERRED, PREFERRED);

			lbDataEmissao = new Label("Data Emissão: " + pedido.getDataEmissao());
			containerPedidos.add(lbDataEmissao, SAME, AFTER, PREFERRED, PREFERRED);

			lbDataEntrega = new Label("Data de Entrega:" + pedido.getDataEntrega());
			containerPedidos.add(lbDataEntrega, SAME, AFTER, PREFERRED, PREFERRED);



			containerActions = new Container();
			containerActions.setInsets(0, 25, 50, 0 );
			add(containerActions, RIGHT + 10, SAME, PARENTSIZE + 35, PARENTSIZE + 30);


			btEditar = new Button(Images.iconeEditar);
			btEditar.setBorder(Container.BORDER_NONE);
			btEditar.setEnabled(false);
			containerActions.add(btEditar, LEFT, SAME, PREFERRED, PREFERRED);
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

				MainWindow.getMainWindow().swap(new ListPedidosFinalizados());

			});
			
			btSincronizar = new Button(Images.iconeSincronizar);
			btSincronizar.setBorder(Container.BORDER_NONE);
			containerActions.add(btSincronizar, CENTER, AFTER, PREFERRED, PREFERRED);
			btSincronizar.addPressListener((e) -> {
				try {
					pedidoAPI.enviarPedidosBackEnd();
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
				MainWindow.getMainWindow().swap(new ListPedidosFinalizados());
				
			});
		}


	}

}