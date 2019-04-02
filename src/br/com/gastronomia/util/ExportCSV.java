package br.com.gastronomia.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ExportCSV {

	private static void generateCsvFile(String sFileName) {
		try {
			FileWriter writer = new FileWriter(sFileName);

			writer.append("DisplayName");
			writer.append(',');
			writer.append("Age");
			writer.append('\n');

			writer.append("MKYONG");
			writer.append(',');
			writer.append("26");
			writer.append('\n');

			writer.append("YOUR NAME");
			writer.append(',');
			writer.append("29");
			writer.append('\n');

			// generate whatever data you want

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static void exportData(String filename) throws ClassNotFoundException, SQLException {

		Connection conexao = null;
		conexao = ConexaoUtil.getConexao();
		Statement stmt;
		String query;
		try {
			stmt = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			query = "SELECT id_usuario,nome,matricula into OUTFILE  '" + filename + "' FIELDS TERMINATED BY ',' FROM tb_usuario t";
			stmt.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
			stmt = null;
		} finally {
			conexao.close();
		}
	}


	public static void writeToCSV(List<Map> objectList) {
		String CSV_SEPARATOR = ",";
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("c:\\CAWT\\results.csv"), "UTF-8"));
			for (Map objectDetails : objectList) {
				StringBuffer oneLine = new StringBuffer();
				Iterator it = objectDetails.values().iterator();

				while (it.hasNext()) {
					Object value = it.next();

					if(value !=null){
						oneLine.append(value.toString());
					}

					if (it.hasNext()) {
						oneLine.append(CSV_SEPARATOR);
					}
				}
				bw.write(oneLine.toString());
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	
}
