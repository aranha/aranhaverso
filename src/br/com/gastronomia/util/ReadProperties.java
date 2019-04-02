package br.com.gastronomia.util;

import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	public static Properties read() {
		InputStream inputStream;

		Properties prop = new Properties();
		try {
			String propFileName = "config.properties";
					//"resources/config.properties";
// --/Users/cassio.trindade/git/modeloapi/test/resources/config.properties
			inputStream = ReadProperties.class.getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return prop;
	}
}
