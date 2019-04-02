package br.com.gastronomia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 
 * @author cassio trindade
 *
 */
public class ConexaoUtil {

	private static ResourceBundle configDB = ResourceBundle.getBundle(Constantes.AMBIENTE_PROPERTIES);

	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());

		return DriverManager.getConnection(configDB.getString(Constantes.CONEXAO_BD_URL), configDB.getString(Constantes.CONEXAO_BD_USER),
				configDB.getString(Constantes.CONEXAO_BD_PASSWORD));
	}
	public static void main(String[] args) {
		try {
			System.out.println(getConexao());
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
	
}
