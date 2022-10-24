package br.com.wmw.comprastc.ui;

import java.sql.SQLException;

import br.com.wmw.comprastc.dao.ProdutoDAO;
import br.com.wmw.comprastc.domain.Cliente;
import br.com.wmw.comprastc.domain.ItemPedido;
import br.com.wmw.comprastc.domain.Pedido;
import br.com.wmw.comprastc.domain.Produto;
import br.com.wmw.comprastc.service.ItemPedidoService;
import br.com.wmw.comprastc.util.Colors;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;

public class TelaCadastroPedido extends ScrollContainer {
	
	  	private Container containerHead, containerBody, containerActions;
	    Button btnFechar, btnAdicionar, btnSalvar;
	    private Label lbCadastrarPedido, lbProduto, lbPrecoUnitario, precoUnitario, precoTotalItem, lbQuantidade, lbDesconto, lbValorTotalItem;
	    private Edit editQuantidade, editDesconto;
	    private Pedido pedido = new Pedido();
	    private ComboBox simpleComboBox;
	    private Produto produto;
	    private ItemPedido item;
	    private ItemPedidoService service = new ItemPedidoService();
	    
	    public TelaCadastroPedido(Pedido pedido) {
	    	this.pedido = pedido;
	    }
	  
	public TelaCadastroPedido(Cliente cliente) {
			this.pedido.setCodigoCliente(cliente.getId());
		}
	
	public TelaCadastroPedido(ItemPedido itemPedido, Pedido pedido) {
		this.item = itemPedido;
		this.pedido = pedido;
	}

	public void initUI() {
        ProdutoDAO produtoDao = new ProdutoDAO();

		containerHead = new Container();
        add(containerHead, LEFT, TOP, FILL, PARENTSIZE + 8);

        btnFechar = new Button("Cancelar");
        btnFechar.setBorder(Container.BORDER_NONE);
        containerHead.add(btnFechar, LEFT, CENTER, PREFERRED, PREFERRED);
        	 btnFechar.addPressListener((e) -> {

                 MessageBox messageBox = new MessageBox("Mensagem", "Você irá perder o seu pedido. Deseja continuar mesmo assim?", new String[] { "Sim", "Não" });
                 messageBox.popup();

                 if(messageBox.getPressedButtonIndex() == 0) {
                     MainWindow.getMainWindow().swap(new ListarClienteWindow());
                 }
             });
        lbCadastrarPedido = new Label("Cadastrar Pedido", CENTER, Colors.BLACK, true);
        containerHead.add(lbCadastrarPedido, CENTER, CENTER, PREFERRED, PREFERRED);
        
        containerBody = new Container();
        containerBody.borderColor = Colors.GRAY;
        containerBody.setBorderStyle(Container.BORDER_TOP);
        containerBody.setInsets(25, 25, 25, 25);
        add(containerBody, LEFT, AFTER, FILL, FILL);

        lbProduto = new Label("Selecione o Produto");
        lbProduto.setInsets(0, 0, 25, 5);
        containerBody.add(lbProduto, LEFT, AFTER, PREFERRED, PREFERRED);

        ComboBox.usePopupMenu = false;
        simpleComboBox = new ComboBox(service.retornaListaProdutos());
        simpleComboBox.caption = "Produtos";
        simpleComboBox.setBorderStyle(Container.BORDER_LOWERED);
        simpleComboBox.setInsets(5,5,5,5);
        containerBody.add(simpleComboBox, LEFT, AFTER, FILL, PREFERRED);

        lbPrecoUnitario = new Label("Preço Unitário");
        lbProduto.setInsets(0, 0, 25, 0);
        containerBody.add(lbPrecoUnitario, SAME, AFTER, PREFERRED, PREFERRED);

        precoUnitario = new Label("R$ 0.0");
        precoUnitario.setInsets(5,0,25,0);
        containerBody.add(precoUnitario, SAME, AFTER, PARENTSIZE, PREFERRED);
        
        simpleComboBox.addPressListener((e) -> {
            Object obj = simpleComboBox.getSelectedItem();
            String str = obj.toString();
            produto = produtoDao.buscarProdutoPorNome(str);
			item = new ItemPedido();
			item.setCodigoProduto(produto.getId());
			item.setPrecoUnitario(produto.getPreco());

			precoUnitario.setText("R$ " + produto.getPreco() + "");
			editQuantidade.caption = "0";
			editQuantidade.setText(""+item.getQuantidade());
			editDesconto.caption = "0";
			editDesconto.setText(""+item.getDesconto());
			precoTotalItem.setText("R$ " + item.getTotalItem()+ "");
        });
      
        lbQuantidade = new Label("Quantidade");
        lbQuantidade.setInsets(0, 0, 0, 25);
        containerBody.add(lbQuantidade, SAME, AFTER, PREFERRED, PREFERRED);

        editQuantidade = new Edit();
        editQuantidade.caption = "0";
        editQuantidade.setMode(Edit.CURRENCY);
        editQuantidade.setKeyboard(Edit.KBD_NUMERIC);
        containerBody.add(editQuantidade, SAME, AFTER, PREFERRED, PREFERRED);
        
        lbDesconto = new Label("Desconto em Porcentagem");
        lbDesconto.setInsets(0, 0, 25, 3);
        containerBody.add(lbDesconto, SAME, AFTER, PREFERRED, PREFERRED);

        editDesconto = new Edit();
        editDesconto.caption = "0";
        editDesconto.setMode(Edit.CURRENCY);
        editDesconto.setKeyboard(Edit.KBD_CALCULATOR);
        containerBody.add(editDesconto, SAME, AFTER, PREFERRED, PREFERRED);

        lbValorTotalItem = new Label("Valor Total");
        lbValorTotalItem.setInsets(0, 0, 25, 3);
        containerBody.add(lbValorTotalItem, SAME, AFTER, PREFERRED, PREFERRED);

        precoTotalItem = new Label("0.0");
        precoTotalItem.setInsets(5,0,0,0);
        containerBody.add(precoTotalItem, SAME, AFTER, FILL, PREFERRED);

        editQuantidade.addPressListener((e) -> {
            item = service.calculaValorTotalItem(editQuantidade.getText(), editDesconto.getText(), item);           
			precoTotalItem.setText("R$ " + item.getTotalItem() + "");
        });
        
        editDesconto.addPressListener((e) -> {

            item.setPrecoUnitario(produto.getPreco());
            item = service.calculaValorTotalItem(editQuantidade.getText(), editDesconto.getText(), item);    

            precoUnitario.setText("R$ " + item.getPrecoUnitario() + "");
            precoTotalItem.setText("R$ " + item.getTotalItem()+ "");

        });
        
        if(item != null ) {
            
        	try {
				produto = produtoDao.buscaProdutoPorId(item.getCodigoProduto());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
 			item.setCodigoProduto(produto.getId());
 			item.setPrecoUnitario(produto.getPreco());
        	precoUnitario.setText("R$ " + produto.getPreco() + "");
			editQuantidade.caption = "0";
			editQuantidade.setText(""+item.getQuantidade());
			editDesconto.caption = "0";
			editDesconto.setText(""+item.getDesconto());
			precoTotalItem.setText("R$ " + item.getTotalItem()+ "");
        	
        }
        
        containerActions = new Container();
        containerActions.setInsets(15, 15, 25, 25);
        add(containerActions, LEFT, BOTTOM, FILL, PARENTSIZE + 15);

        btnAdicionar = new Button("Adicionar");
        btnAdicionar.setBackForeColors(Colors.BLUE, Colors.WHITE);
        btnAdicionar.borderColor = Colors.BLACK;
        btnAdicionar.setBorder(Container.BORDER_ROUNDED);
        containerActions.add(btnAdicionar, LEFT, CENTER, PARENTSIZE + 52 , PARENTSIZE + 95);
        btnAdicionar.addPressListener((e) -> {

            if (service.verificaSeTemUmItemNoPedido(item)){
                if(service.verificaQuantidadeMinPedido(item)) {
                	pedido.getItens().remove(item);
                	pedido.getItens().add(item);
                	item = new ItemPedido();
                    
                    MainWindow.getMainWindow().swap(new TelaCadastroPedido(pedido));
                }
            }
        });

        btnSalvar = new Button("Resumo do Pedido");
        btnSalvar.setBackForeColors(Colors.BLUE, Colors.WHITE);
        btnSalvar.setBorder(Container.BORDER_ROUNDED);
        containerActions.add(btnSalvar, RIGHT, CENTER, PARENTSIZE + 47 , PARENTSIZE + 95);
        btnSalvar.addPressListener((e) -> {  
                    MainWindow.getMainWindow().swap(new PedidoResumeWindow(pedido));
          
        });



	}
	
}

