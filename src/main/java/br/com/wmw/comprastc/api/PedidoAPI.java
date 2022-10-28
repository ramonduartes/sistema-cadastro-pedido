package br.com.wmw.comprastc.api;

import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.wmw.comprastc.dados.Response;
import br.com.wmw.comprastc.dao.ItemPedidoDAO;
import br.com.wmw.comprastc.dao.PedidoDAO;
import br.com.wmw.comprastc.dto.PedidoDTO;
import br.com.wmw.comprastc.exception.ConnectionException;
import br.com.wmw.comprastc.exception.PersistenceException;
import br.com.wmw.comprastc.service.PedidoService;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONObject;
import totalcross.net.HttpStream;
import totalcross.net.URI;

public class PedidoAPI {
	
	public static final String CONTENT_TYPE_JSON = "application/json";
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ItemPedidoDAO itemDAO = new ItemPedidoDAO();
    private PedidoService pedidoService = new PedidoService();

    public int enviarPedidosBackEnd() throws PersistenceException {

        List<PedidoDTO> pedidos;

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String msg = "";

        HttpStream httpStream = null;

        try {

            if (pedidoDAO.retornaUltimoId() == 0)
                return 200;
            
            pedidos = pedidoDAO.listarPedidoDTONaoSincronizados();
            for (PedidoDTO pedido : pedidos) {
         
                if (pedido.getStatusPedido().equals("FECHADO")){
                    pedido.addItems(itemDAO.listarItemPorIdDTO(pedido.getCodigoPedido()));

                    HttpStream.Options options = new HttpStream.Options();
                    options.httpType = HttpStream.POST;
                    options.setContentType(CONTENT_TYPE_JSON);
                    JSONObject jsonObject = new JSONObject(pedido);

                    System.out.println(jsonObject.toString());
                    options.data = jsonObject.toString();

                    httpStream = new HttpStream(new URI("http://localhost:8080/pedidos/"), options);
                    ByteArrayStream bas = new ByteArrayStream(4096);
                    bas.readFully(httpStream, 10, 2048);
                    String data = new String(bas.getBuffer(), 0, bas.available());

                    Response<PedidoDTO> response = new Response<>();
                    response.responseCode = httpStream.responseCode;

                    if (httpStream.responseCode == 200) {
                       pedidoService.updatePedidoEnviado(pedido);
                    }
                    else {
                        throw new RuntimeException(data);
                    }
                }

            }

        } catch (IOException e1) {
           throw new ConnectionException("API indisponível para o envio dos pedidos.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (httpStream == null)
            return 0;
        else
            return httpStream.responseCode;

    }
}
