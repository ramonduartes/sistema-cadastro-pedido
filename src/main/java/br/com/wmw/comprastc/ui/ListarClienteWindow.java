package br.com.wmw.comprastc.ui;

import br.com.wmw.comprastc.dao.ClienteDAO;
import br.com.wmw.comprastc.domain.Cliente;
import br.com.wmw.comprastc.service.ClienteService;
import br.com.wmw.comprastc.util.Colors;
import br.com.wmw.comprastc.util.Images;
import br.com.wmw.comprastc.util.MaterialConstants;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;

public class ListarClienteWindow extends ScrollContainer {


	private Container containerHeader, containerBody, containerFooter;
	private Button btVoltar, btAvancar;
	private Label lbClientes;
	private ImageControl clienteImage;
	private ComboBox simpleComboBox;
	private ClienteService clienteService = new ClienteService();
	private Cliente[] clientesArray;
	Cliente clienteSelecionado;



	public ListarClienteWindow() {

		ClienteDAO clienteDao = new ClienteDAO();
		clientesArray = clienteDao.findAllOnFormatArray();
		simpleComboBox = new ComboBox(clientesArray);
	}

	public void initUI() {


		Images.carregarImagens(fmH);
		containerHeader = new Container();
		add(containerHeader, LEFT, TOP, FILL, FILL);


		clienteImage = new ImageControl(Images.clienteRecente);
		add(clienteImage, LEFT, TOP + 5 );

		lbClientes = new Label("Selecione Um Cliente");
		containerHeader.add(lbClientes, CENTER+MaterialConstants.COMPONENT_SPACING, TOP+3);


		containerBody = new Container();
		add(containerBody, LEFT+MaterialConstants.BORDER_SPACING, BOTTOM, FILL-MaterialConstants.BORDER_SPACING,
				PARENTSIZE + 80);
		ComboBox.usePopupMenu = false;

		try {
			simpleComboBox = new ComboBox(clienteService.retornaListaClientes());
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		simpleComboBox.caption = "Clientes";
		simpleComboBox.setBorderStyle(Container.BORDER_LOWERED);
		simpleComboBox.setInsets(5,5,5,5);
		containerBody.add(simpleComboBox, LEFT, AFTER, FILL, PREFERRED);
		
		simpleComboBox.addPressListener((e) -> {
			clienteSelecionado = clientesArray[simpleComboBox.getSelectedIndex()];			

		});
		containerFooter = new Container();
		containerFooter.borderColor = Colors.GRAY;
		containerFooter.setBorderStyle(Container.BORDER_TOP);
		containerFooter.setInsets(15, 15, 25, 25);
		add(containerFooter, LEFT, BOTTOM, FILL, PARENTSIZE + 15);
		btVoltar = new Button("Voltar");
		btVoltar.setBackForeColors(Colors.BLUE, Colors.WHITE);
		btVoltar.borderColor = Colors.GRAY;
		btVoltar.setBorder(Container.BORDER_ROUNDED);
		containerFooter.add(btVoltar, LEFT, CENTER, PARENTSIZE + 52 , PARENTSIZE + 95);
		btVoltar.addPressListener((e) -> {
			MainWindow.getMainWindow().swap(new TelaMenu());
		});

		btAvancar = new Button("Avançar");
		btAvancar.setBackForeColors(Colors.BLUE, Colors.WHITE);
		btAvancar.borderColor = Colors.GRAY;
		btAvancar.setBorder(Container.BORDER_ROUNDED);
		containerFooter.add(btAvancar, RIGHT, CENTER, PARENTSIZE + 47 , PARENTSIZE + 95);
		btAvancar.addPressListener((e) -> {
			MainWindow.getMainWindow().swap(new TelaCadastroPedido(clienteSelecionado));

		});


	}  
}

