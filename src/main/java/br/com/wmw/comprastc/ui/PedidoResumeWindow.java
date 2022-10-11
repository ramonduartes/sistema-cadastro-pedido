package br.com.wmw.comprastc.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dao.ProdutoDAO;
import br.com.wmw.comprastc.domain.ItemPedido;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.domain.Produto;
import br.com.wmw.comprastc.service.PedidoService;
import br.com.wmw.comprastc.util.Colors;
import br.com.wmw.comprastc.util.Images;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.dialog.MessageBox;

public class PedidoResumeWindow extends Container {
	
	private Container containerFooter, containerTopo, containerActions, containerBody;
	private Button btVoltar, btSalvar, btAdicionar, btEditar, btDeletar;
	private Label lbItensPedido, lbValor, lbQuantidade, lbListaItens;
	private Pedido pedido;
	private PedidoService pedidoService = new PedidoService();
	private List<ItemPedido> listaItens = new ArrayList<>();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
 


	public PedidoResumeWindow(Pedido pedido) {
		this.pedido = pedido;
		
		
		
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

        lbListaItens = new Label("Lista dos Itens", CENTER, Colors.BLACK, true);
        containerTopo.add(lbListaItens, CENTER, CENTER, PREFERRED, PREFERRED);

       listaItens = pedido.getItens();

        for (ItemPedido itemPedido: listaItens) {

        	containerBody = new Container();
        	containerBody.borderColor = Colors.GRAY;
        	containerBody.setBorderStyle(Container.BORDER_TOP);
        	containerBody.setInsets(35, 35, 25, 25);
            add(containerBody, LEFT, AFTER, FILL, PARENTSIZE + 15);
            
	         try {
	        	 	Produto produto = produtoDAO.buscaProdutoPorId(itemPedido.getCodigoProduto());
	                lbItensPedido = new Label(produto.getNome());
	                containerBody.add(lbItensPedido, LEFT, SAME, PREFERRED, PREFERRED);
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }

            lbQuantidade = new Label("Quantidade:" + itemPedido.getQuantidade());
            containerBody.add(lbQuantidade, LEFT, AFTER, PREFERRED, PREFERRED);

            lbValor = new Label("Total: R$ " + itemPedido.getTotalItem());
            containerBody.add(lbValor, LEFT, AFTER, PREFERRED, PREFERRED);
      

        containerActions = new Container();
        containerActions.setInsets(0, 25, 50, 0);
        add(containerActions, RIGHT, SAME, PARENTSIZE + 35, PARENTSIZE + 22);

        btEditar = new Button(Images.iconeEditar);
        btEditar.setBorder(Container.BORDER_NONE);
        containerActions.add(btEditar, LEFT, AFTER, PREFERRED, PREFERRED);
        btEditar.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new TelaCadastroPedido(itemPedido));
        });

        btDeletar = new Button(Images.iconeDeletar);
        btDeletar.setBorder(Container.BORDER_NONE);
        containerActions.add(btDeletar, RIGHT, SAME, PREFERRED, PREFERRED);
        btDeletar.addPressListener((e) -> {

        	pedido.getItens().remove(itemPedido);

            MessageBox mb = new MessageBox("Message", "Item excluído com sucesso.", new String[]{"Fechar"});
            mb.popup();

            MainWindow.getMainWindow().swap(new PedidoResumeWindow(pedido));

        });
        }
        
        containerFooter = new Container();
        containerFooter.setInsets(15, 15, 25, 25);
        add(containerFooter, LEFT, BOTTOM, FILL, PARENTSIZE + 15);
        
        btAdicionar = new Button("Adicionar +");
        btAdicionar.setBackForeColors(Colors.BLUE, Colors.WHITE);
        btAdicionar.borderColor = Colors.GRAY;
        btAdicionar.setBorder(Container.BORDER_ROUNDED);
        
        containerFooter.add(btAdicionar, LEFT, CENTER, PARENTSIZE + 52 , PARENTSIZE + 95);
        btAdicionar.addPressListener((e) -> {
                    MainWindow.getMainWindow().swap(new TelaCadastroPedido(pedido));
                
        });
        
        btSalvar = new Button("Salvar");
        btSalvar.setBackForeColors(Colors.BLUE, Colors.WHITE);
        btSalvar.setBorder(Container.BORDER_ROUNDED);
        containerFooter.add(btSalvar, RIGHT, CENTER, PARENTSIZE + 47 , PARENTSIZE + 95);
        btSalvar.addPressListener((e) -> {
        	double valorTotal = pedidoService.calculaValorTotal(pedido);
        	pedido.setTotalPedido(valorTotal);
           
                    MainWindow.getMainWindow().swap(new PedidoCadWindow(pedido));
               
        });
	 
 }
 
}
