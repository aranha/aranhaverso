package br.com.gastronomia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe de utilidades diversas.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 *
 **/
public class Util {
	private static ResourceBundle configDB = ResourceBundle.getBundle(Constantes.AMBIENTE_PROPERTIES);
	
	public Util() {
		
	}
	
	public static String concatenaMensagensRequest(HttpServletRequest request, Exception e, String msg) {
		String msgErro = "";
		if (request.getAttribute(msg) != null) {
			msgErro = (String) request.getAttribute(msg);
		}
		msgErro += e.getMessage() + "<br/>" ;
		return msgErro;
	}

	public static boolean isCPF(String CPF) {
		// considera-se erro CPF's formados por uma seq��ncia de n�meros iguais 
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11)) return(false); char dig10, dig11; int sm, i, r, num, peso; 
		// "try" - protege o c�digo para eventuais erros de convers�o de tipo (int) 
		try { // Calculo do 1o. Digito Verificador 
			sm = 0; peso = 10; 
			for (i=0; i<9; i++) {
				// converte o i-�simo caractere do CPF em um numero: 
				// por exemplo, transforma o caractere '0' no inteiro 0 
				// (48 eh a posicao de '0' na tabela ASCII) 
				num = (int)(CPF.charAt(i) - 48); sm = sm + (num * peso); peso = peso - 1; 
			} r = 11 - (sm % 11); 
			if ((r == 10) || (r == 11)) dig10 = '0'; 
			else dig10 = (char)(r + 48); 
			// converte no respectivo caractere num�rico 
			// Calculo do 2o. Digito Verificador 
			sm = 0; peso = 11; 
			for(i=0; i<10; i++) { 
				num = (int)(CPF.charAt(i) - 48); sm = sm + (num * peso); peso = peso - 1; } r = 11 - (sm % 11); 
				if ((r == 10) || (r == 11)) dig11 = '0'; 
				else dig11 = (char)(r + 48); 
				// Verifica se os d�gitos calculados conferem com os d�gitos informados. 
				if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) return(true); 
				else 
					return(false); 
		} catch (InputMismatchException erro) { 
			return(false); 
		} 

	} 
	
	public static String imprimeCPF(String cpf) { 
		return(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11)); 
	}
	

	
	public static Date stringToDate(String s) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data;
		try {
			data = formatter.parse(s);
		} catch (ParseException e) {
			throw new ParseException("INvalidado", e.getErrorOffset());
		}

			
		return data;
	}
	
	public static Date stringToDateTime(String s) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Date data;
		try {
			data = formatter.parse(s);
		} catch (ParseException e) {
			throw new ParseException("invalido", e.getErrorOffset());
		}

			
		return data;
	}
	
	public static String dateToString(Date d) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String data;
		data = formatter.format(d);

		return data;
	}
	
	
	public static String removeEspacamento(String tituloLivro) {
		if(tituloLivro.equals(""))
			return null;
		return tituloLivro.replaceAll("\\s","");		
	}
	
	public static String getVersion() {
		String version = configDB.getString(Constantes.VERSAO_SISTEMA);
	return version;
}

	public static String generateUUID() {

		final String uuid = UUID.randomUUID().toString().replaceAll("-", "");

		return uuid;
	}
	
}
