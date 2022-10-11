package br.com.wmw.comprastc.util;

import totalcross.io.IOException;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

public class Images {
	
	public static Image logoHeader, iconeProcurar, clienteRecente, iconeEntrega, iconeEditar, iconeDeletar, iconeSincronizar, iconeListagem;
	
	public Images() {
		
	}
	public static Image getClienteRecente() {
		return clienteRecente;
	}
	public static Image getLogoHeader() {
		return logoHeader;
	}

	public static Image getIconeProcurar() {
		return iconeProcurar;
	}

	public static Image getIconeEntrega() {
		return iconeEntrega;
	}

	public static Image getIconeEditar() {
		return iconeEditar;
	}

	public static Image getIconeDeletar() {
		return iconeDeletar;
	}

	public static Image getIconeSincronizar() {
		return iconeSincronizar;
	}
	


	public static void carregarImagens(int fmH) {
		try {
			logoHeader = new Image("images/logo-wmw.png");
			iconeProcurar = new Image("images/icone-procurar.png");
			iconeEntrega = new Image("images/icone-entrega.png");
			iconeEditar = new Image("images/icone-lapis.png");
			iconeDeletar = new Image("images/icone-lixeira.png");
			iconeSincronizar = new Image("images/icone-sincronizar.png");
			iconeListagem = new Image("images/icone-listagem.png");
			clienteRecente = new Image("images/clienterecenteOff.png");
		} catch (ImageException | IOException e) {
            e.printStackTrace();
		}
		
	}

	public static Image getIconeListagem() {
		return iconeListagem;
	}

}
