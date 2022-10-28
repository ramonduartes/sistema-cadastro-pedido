package br.com.wmw.comprastc.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.domain.Produto;
import br.com.wmw.comprastc.service.ProdutoService;
import br.com.wmw.comprastc.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;

public class ListarProdutoWindow extends ScrollContainer {

	private Container containerTopo, containerProduto;
	private Button btVoltar;
	private Label lbProdutos, lbProduto;
	private List<Produto> produtos = new ArrayList<>();

	public ListarProdutoWindow() {
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

		lbProdutos = new Label("Produtos", CENTER, Colors.BLACK, true);
		containerTopo.add(lbProdutos, CENTER, CENTER, PREFERRED, PREFERRED);

		try {
			produtos = new ProdutoService().listarProdutos();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (Produto produto: produtos) {

			containerProduto = new Container();
			containerProduto.borderColor = Colors.GRAY;
			containerProduto.setBorderStyle(Container.BORDER_TOP);
			containerProduto.setInsets(35, 35, 25, 25);
			add(containerProduto, LEFT, AFTER, FILL, PARENTSIZE + 13);

			lbProduto = new Label(produto.getNome());
			containerProduto.add(lbProduto, LEFT, SAME, PREFERRED, PREFERRED);

			lbProduto = new Label("R$ " + produto.getPreco()+ "");
			containerProduto.add(lbProduto, LEFT, AFTER, PREFERRED, PREFERRED);
		}

	}

}
