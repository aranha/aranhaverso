package br.com.gastronomia.exception;

/**
 * 
 * Excecoes de validacao.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 08/06/2017
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidationException(String msg) {
		super(msg);
	}

	public ValidationException(String msg, Exception e) {
		super(msg, e);
	}
}
