package br.com.wmw.comprastc.ui;

import java.sql.SQLException;

import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.exception.PersistenceException;
import br.com.wmw.comprastc.service.PedidoService;
import br.com.wmw.comprastc.util.Colors;
import br.com.wmw.comprastc.util.MaterialConstants;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.dialog.MessageBox;

public class PedidoViewWindow extends Container {

	private Container containerHead, containerPedido,  containerFooter;
	private Button btVoltar, btSalvar, btFinalizar;
	private Label lbHeader, lbDataEmissao, lbDataEntrega, lbValorTotal, lbStatus;
	private PedidoService pedidoService = new PedidoService();	
	private Pedido pedido;

	public PedidoViewWindow(Pedido pedido) {
		this.pedido = pedido;

	}

	public void initUI() {

		containerHead = new Container();
		add(containerHead, LEFT, TOP, FILL, PARENTSIZE + 10);

		btVoltar = new Button("Cancelar");
		btVoltar.setBorder(BORDER_NONE);
		containerHead.add(btVoltar, LEFT, CENTER, PREFERRED, PREFERRED);
		btVoltar.addPressListener((e) -> {
			MainWindow.getMainWindow().swap(new TelaMenu());

		});

		lbHeader = new Label("Confirmar o Envio");
		containerHead.add(lbHeader, CENTER, CENTER, PREFERRED, PREFERRED);



		containerPedido = new Container();
		containerPedido.borderColor = Colors.GRAY;
		containerPedido.setBorderStyle(Container.BORDER_TOP);

		containerPedido.setInsets(25, 25, 25, 25);
		add(containerPedido, LEFT, AFTER, FILL, PARENTSIZE + 25);

		lbDataEmissao = new Label("Data Emissão: " + pedido.getDataEmissao());
		containerPedido.add(lbDataEmissao, SAME , AFTER, PREFERRED, PREFERRED);

		lbDataEntrega = new Label("Data Entrega: " + pedido.getDataEntrega());
		containerPedido.add(lbDataEntrega, SAME , AFTER, PREFERRED, PREFERRED);

		lbValorTotal = new Label("Valor Total: " + pedido.getTotalPedido());
		containerPedido.add(lbValorTotal, SAME , AFTER, PREFERRED, PREFERRED);

		lbStatus = new Label("Status: " + pedido.getStatusPedido());
		containerPedido.add(lbStatus, SAME , AFTER, PREFERRED, PREFERRED);


		if(pedido.getStatusPedido().ABERTO != null ) {
			containerFooter = new Container();
			containerFooter.borderColor = Colors.GRAY;
			containerFooter.setBorderStyle(Container.BORDER_TOP);
			containerFooter.setInsets(35, 35, 25, 25);
			add(containerFooter, LEFT+MaterialConstants.BORDER_SPACING, BOTTOM-MaterialConstants.BORDER_SPACING, 
					FILL-MaterialConstants.BORDER_SPACING, PARENTSIZE+10);

			btSalvar = new Button("Salvar");
			btSalvar.setBackForeColors(Colors.BLUE, Colors.WHITE);
			btSalvar.borderColor = Colors.GRAY;
			btSalvar.setBorder(Container.BORDER_ROUNDED);
			containerFooter.add(btSalvar, LEFT, BOTTOM);
			btSalvar.addPressListener((e) -> {
				try {
					pedidoService.atualizarPedido(pedido);
					MainWindow.getMainWindow().swap(new ListarPedidoWindow());
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}


			});

			btFinalizar = new Button("Finalizar");
			btFinalizar.setBackForeColors(Colors.BLUE, Colors.WHITE);
			btFinalizar.borderColor = Colors.GRAY;
			btFinalizar.setBorder(Container.BORDER_ROUNDED);
			containerFooter.add(btFinalizar, RIGHT, BOTTOM);
			btFinalizar.addPressListener((e) -> {
				try {
					pedidoService.atualizarPedido(pedido);
					if (pedidoService.verificaSeTemMinimoUmItem(pedido)) {
						pedidoService.fecharPedido(pedido);
						MainWindow.getMainWindow().swap(new ListPedidosFinalizados(pedido.getCodigoCliente()));
					} else {
						MessageBox mb = new MessageBox("Message", "Pedido precisa de no mínimo 1 item.", new String[]{"Fechar"});
						mb.popup();
					}
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
			});


		}

	}

}
