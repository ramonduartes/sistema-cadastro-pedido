package br.com.wmw.comprastc.api;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.domain.Cliente;
import br.com.wmw.comprastc.exception.ConnectionException;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONException;
import totalcross.json.JSONFactory;
import totalcross.net.HttpStream;
import totalcross.net.URI;
import totalcross.net.UnknownHostException;
import totalcross.sys.Vm;

public class ClienteAPI {
	
	public ClienteAPI() {	
	}

	public static List<Cliente> getAllClientes() {
		List<Cliente> clientesList = new ArrayList<>();
		try (ByteArrayStream bas = new ByteArrayStream(4096)){
            HttpStream.Options options = new HttpStream.Options();
            options.httpType = HttpStream.GET;
            options.setContentType("application/json");
            
            HttpStream httpStream = new HttpStream(new URI("http://localhost:8080/clientes"), options);
            bas.readFully(httpStream, 10, 2048);
            String data = new String(bas.getBuffer(), 0, bas.available());
            
            if(httpStream.responseCode == 200) clientesList = (JSONFactory.asList(data, Cliente.class));
            
		} catch (UnknownHostException | ArrayIndexOutOfBoundsException | InstantiationException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException | JSONException | NoSuchMethodException | SecurityException e) {
			Vm.debug(e.getMessage());
		} catch (IOException e) {
			throw new ConnectionException("API indisponível para o cadastro de clientes.");
		}
		
		return clientesList;
	}
    }
