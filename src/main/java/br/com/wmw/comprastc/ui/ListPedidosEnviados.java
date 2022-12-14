package br.com.wmw.comprastc.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dao.ClienteDAO;
import br.com.wmw.comprastc.domain.Cliente;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.service.PedidoService;
import br.com.wmw.comprastc.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;

public class ListPedidosEnviados extends ScrollContainer {

	private Container containerTopo, containerPedidos;
	private Button btVoltar;
	private Label lbPedidos, lbNome, lbPedido, lbStatus, lbDataEmissao, lbDataEntrega;
	private List<Pedido> pedidos = new ArrayList<>();
	private ClienteDAO clienteDAO = new ClienteDAO();
	private PedidoService pedidoService = new PedidoService();
	Cliente cliente = new Cliente();
	

	public ListPedidosEnviados() {

	}

	public ListPedidosEnviados(int id) throws SQLException {
		this.cliente = clienteDAO.detalharCliente(id);
	}

	public ListPedidosEnviados(Cliente cliente) {

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
			pedidos = pedidoService.listarPedidosEnviados();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		for(Pedido pedido : pedidos) {

			containerPedidos = new Container();
			containerPedidos.borderColor = Colors.GRAY;
			containerPedidos.setBorderStyle(Container.BORDER_TOP);
			containerPedidos.setBackColor(Colors.WHITE);
			containerPedidos.setInsets(25, 25, 25, 25);
			add(containerPedidos, LEFT, AFTER, FILL, PARENTSIZE + 20);


			try {
				cliente = clienteDAO.findByCodigoCliente(pedido.getCodigoCliente());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			lbNome = new Label("Cliente: " + cliente.getNome());
			containerPedidos.add(lbNome, SAME, AFTER, PREFERRED, PREFERRED);

			lbPedido = new Label("Pedido #" + pedido.getCodigoPedido());
			containerPedidos.add(lbPedido, SAME, AFTER, PREFERRED, PREFERRED);

			lbStatus = new Label("Status: " + pedido.getStatusPedido());
			containerPedidos.add(lbStatus, SAME, AFTER, PREFERRED, PREFERRED);

			lbDataEmissao = new Label("Data Emiss?o: " + pedido.getDataEmissao());
			containerPedidos.add(lbDataEmissao, SAME, AFTER, PREFERRED, PREFERRED);

			lbDataEntrega = new Label("Data de Entrega:" + pedido.getDataEntrega());
			containerPedidos.add(lbDataEntrega, SAME, AFTER, PREFERRED, PREFERRED);


		}


	}

}