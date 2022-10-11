package br.com.wmw.comprastc.service;

import java.sql.SQLException;
import java.util.List;

import br.com.wmw.comprastc.api.PedidoAPI;
import br.com.wmw.comprastc.api.ProdutoAPI;
import br.com.wmw.comprastc.dados.DatabaseManager;
import br.com.wmw.comprastc.dados.Sincronizar;
import br.com.wmw.comprastc.domain.Produto;
import totalcross.sql.Connection;
import totalcross.ui.dialog.MessageBox;

public class APIService {

//	   MessageBox mb;
//	    public void execute(){
//	        try {
//	            Connection dbcon = DatabaseManager.getConnection();
//
//	            
//	            
//
//	            if (clienteResponse == 200 && produtoResponse == 200 && pedidoResponse == 200) {
//	                mb = new MessageBox("Message", "Dados sincronizados com sucesso.", new String[]{"Fechar"});
//	            } else {
//	                mb = new MessageBox("Message", "Dados não sincronizados. Servidor indisponível.", new String[]{"Fechar"});
//	            }
//
//	            mb.popup();
//	        } catch (SQLException ex) {
//	            throw new RuntimeException(ex);
//	        }
//	    }
//		public void run() {
//			// TODO Auto-generated method stub
//			
//		}
}
