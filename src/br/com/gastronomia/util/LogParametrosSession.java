package br.com.gastronomia.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Classe para fazer log dos parametros enviados por query string.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public class LogParametrosSession {
	private static Logger logger = Logger.getLogger("util.LogParametrosSession");

	public  static void logParametros(HttpServletRequest request) throws ServletException, IOException {
		// Set response content type

		Enumeration paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			logger.debug("Nome Parametro: " + paramName );
			String[] paramValues = request.getParameterValues(paramName);
			// Read single valued data
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					logger.debug("Sem Valor");
				else
					logger.debug("valor: " + paramValue);
			} else {
				// Read multiple valued data
				for (int i = 0; i < paramValues.length; i++) {
					logger.debug("valor("+i+"): "  + paramValues[i]);
				}
			}
		}
	}
}
