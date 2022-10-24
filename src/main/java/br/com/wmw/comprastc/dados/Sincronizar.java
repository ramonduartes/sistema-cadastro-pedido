package br.com.wmw.comprastc.dados;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.wmw.comprastc.dao.ClienteDAO;
import br.com.wmw.comprastc.dao.ProdutoDAO;
import br.com.wmw.comprastc.dto.ClienteDTO;
import br.com.wmw.comprastc.dto.ProdutoDTO;
import br.com.wmw.comprastc.util.Erro;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONException;
import totalcross.json.JSONFactory;
import totalcross.net.HttpStream;
import totalcross.net.URI;
import totalcross.ui.Window;
import totalcross.ui.dialog.ProgressBox;

public class Sincronizar extends Thread {
	

	
	
   public void run() {
   
      ProgressBox pb = new ProgressBox(null,"Sincronizando dados...",null);
      pb.transitionEffect = Window.TRANSITION_NONE;
      try
      {
    	 DatabaseManager.carregarTabelas();
         receberClientes();
         receberProdutos();
         
         
      }
      catch (Exception e)
      {
         Erro.trataExcecao(e);
      }
      finally
      {
         pb.unpop();
      }
   }


   private void receberClientes() throws SQLException {
	   String url = "http://localhost:8080/clientes";
	   try {

			HttpStream.Options options = new HttpStream.Options();
			options.httpType = HttpStream.GET;

			HttpStream httpStream = new HttpStream(new URI(url), options);
			try (ByteArrayStream bas = new ByteArrayStream(4096)) {
				bas.readFully(httpStream, 10, 2048);
				String data = new String(bas.getBuffer(), 0, bas.available());

				Response<ClienteDTO[]> response = new Response<>();
				response.responseCode = httpStream.responseCode;

				if (httpStream.responseCode == 200) {
					response.data = (ClienteDTO[]) (JSONFactory.parse(data, ClienteDTO[].class));
					List<ClienteDTO> listaCliente = new ArrayList<ClienteDTO>();
					listaCliente.addAll(Arrays.asList(response.data));
					ClienteDAO.deletarCliente();
					for (ClienteDTO clienteDTO : listaCliente) {
						ClienteDAO.inserirCliente(clienteDTO);
					}
				}
			} catch (IllegalArgumentException | JSONException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
		
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
	
}
   
   private void receberProdutos() throws SQLException {
	   String url = "http://localhost:8080/produtos";
	   try {

			HttpStream.Options options = new HttpStream.Options();
			options.httpType = HttpStream.GET;

			HttpStream httpStream = new HttpStream(new URI(url), options);
			try (ByteArrayStream bas = new ByteArrayStream(4096)) {
				bas.readFully(httpStream, 10, 2048);
				String data = new String(bas.getBuffer(), 0, bas.available());

				Response<ProdutoDTO[]> response = new Response<>();
				response.responseCode = httpStream.responseCode;

				if (httpStream.responseCode == 200) {
					response.data = (ProdutoDTO[]) (JSONFactory.parse(data, ProdutoDTO[].class));
					List<ProdutoDTO> listaProduto = new ArrayList<ProdutoDTO>();
					listaProduto.addAll(Arrays.asList(response.data));
					ProdutoDAO.deletarProduto();
					for (ProdutoDTO produtoDTO : listaProduto) {
						ProdutoDAO.inserirProduto(produtoDTO);
						
					} 
				}
			} catch (IllegalArgumentException | JSONException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
}


   
}
