package br.com.wmw.comprastc.dados;

import java.sql.SQLException;

import totalcross.db.sqlite.SQLiteUtil;
import totalcross.sql.Connection;
import totalcross.sql.Statement;
import totalcross.sys.Settings;
import totalcross.sys.Vm;

public class DatabaseManager {

	public static SQLiteUtil sqLiteUtil;

	static {
		try {
			sqLiteUtil = new SQLiteUtil(Settings.appPath, "vendasapp.db");
			carregarTabelas();
		} catch (SQLException e) {
			Vm.debug(e.getMessage());
		}
	}

	public static Connection getConnection() throws SQLException {
		return sqLiteUtil.con();
	}

	public static void carregarTabelas() throws SQLException {
		Statement st = getConnection().createStatement();
		st.execute("CREATE TABLE IF NOT EXISTS PRODUTO(COD_PRODUTO BIGINT PRIMARY KEY, NOME VARCHAR, PRECO DECIMAL)");
		st.execute("CREATE TABLE IF NOT EXISTS CLIENTE (COD_CLIENTE BIGINT PRIMARY KEY, EMAIL VARCHAR, NOME VARCHAR, TELEFONE VARCHAR, TIPO_PESSOA VARCHAR, CNPJ_CPF VARCHAR) ");
		st.execute("CREATE TABLE IF NOT EXISTS PEDIDO (COD_PEDIDO INTEGER PRIMARY KEY AUTOINCREMENT, DATA_EMISSAO VARCHAR, DATA_ENTREGA VARCHAR, TOTAL_PEDIDO DECIMAL, COD_CLIENTE BIGINT, STATUS_PEDIDO VARCHAR, FOREIGN KEY(COD_CLIENTE) REFERENCES CLIENTE(COD_CLIENTE))");
		st.execute("CREATE TABLE IF NOT EXISTS ITEMPEDIDO (COD_ITEMPEDIDO INTEGER PRIMARY KEY AUTOINCREMENT, QUANTIDADE INTEGER, PRECO_UNITARIO DECIMAL, DESC_PORCENTO DECIMAL, COD_PRODUTO BIGINT, COD_PEDIDO BIGINT, TOTAL DECIMAL, FOREIGN KEY(COD_PRODUTO) REFERENCES PRODUTO(COD_PRODUTO), FOREIGN KEY(COD_PEDIDO) REFERENCES PEDIDO(COD_PRODUTO))");
		st.close();
	}




}

