package br.com.wmw.comprastc.service;

import java.util.ArrayList;
import java.util.List;

import br.com.wmw.comprastc.dao.ClienteDAO;
import br.com.wmw.comprastc.domain.Cliente;

public class ClienteService {
	
	    private ClienteDAO clienteDAO = new ClienteDAO();
	    List<Cliente> clientes = new ArrayList<Cliente>();
	    
	    public String[] retornaListaClientes() throws Exception {
	        clientes = clienteDAO.buscarClientes();
	        String items[] = new String[clientes.size()];
	        int i = 0;
	        for (Cliente cliente : clientes) {
	            System.out.println(cliente.getNome());
	            items[i] = cliente.getNome();
	            i++;
	        }
	        return items;
	    }
	    
	    
}
