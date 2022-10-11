package br.com.wmw.comprastc;

import java.sql.SQLException;

import br.com.wmw.comprastc.api.ClienteAPI;
import br.com.wmw.comprastc.api.ProdutoAPI;
import br.com.wmw.comprastc.dados.DatabaseManager;
import br.com.wmw.comprastc.dao.ClienteDAO;
import br.com.wmw.comprastc.dao.ProdutoDAO;
import br.com.wmw.comprastc.ui.TelaMenu;
import totalcross.sys.Settings;
import totalcross.ui.MainWindow;

public class Principal extends MainWindow {
	
	ClienteDAO clienteDao = new ClienteDAO();
	ProdutoDAO produtoDao = new ProdutoDAO();
	ProdutoAPI produtoAPI = new ProdutoAPI();
	ClienteAPI clienteAPI = new ClienteAPI();

public Principal() {
	
	 setUIStyle(Settings.MATERIAL_UI);
		
}
	static {
		Settings.applicationId = "VNDS";
        Settings.appVersion = "1.0.00";
        Settings.iosCFBundleIdentifier = "com.wmw.treinamento";

	}

	
public void initUI() {
	
		try {
			ClienteAPI.getAllClientes();
			ProdutoAPI.receberProdutos();
			ProdutoDAO.deletarProduto();
			ClienteDAO.deletarCliente();
			DatabaseManager.carregarTabelas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		TelaMenu inicial = new TelaMenu();
		swap(inicial);
   
}

	
	
}
