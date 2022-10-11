package br.com.wmw.comprastc.api;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.domain.Produto;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONException;
import totalcross.json.JSONFactory;
import totalcross.net.HttpStream;
import totalcross.net.URI;
import totalcross.net.UnknownHostException;
import totalcross.sys.Vm;

public class ProdutoAPI {
	
	public ProdutoAPI() {
	}

	public static List<Produto> receberProdutos() {
		List<Produto> produtosList = new ArrayList<>();
		try (ByteArrayStream bas = new ByteArrayStream(4096)){
            HttpStream.Options options = new HttpStream.Options();
            options.httpType = HttpStream.GET;
            
            HttpStream httpStream = new HttpStream(new URI("http://localhost:8080/produtos"), options);
            bas.readFully(httpStream, 10, 2048);
            String data = new String(bas.getBuffer(), 0, bas.available());
            
            if(httpStream.responseCode == 200) produtosList = JSONFactory.asList(data, Produto.class);
            
		} catch (UnknownHostException | ArrayIndexOutOfBoundsException | InstantiationException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException | JSONException | NoSuchMethodException | SecurityException e) {
			Vm.debug(e.getMessage());
		} catch (IOException e) {
			Vm.debug("Dispositivo sem conex�o e/ou API indispon�vel para recebimento dos cadastros de produtos.");
		}
		return produtosList;
	}
}
