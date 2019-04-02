package br.com.gastronomia.exception;

/**
 * Excecoes de Banco de Dados
 * @author Cassio
 *
 */
public class PersistenciaException extends Exception{

	private static final long serialVersionUID = -3517170452748690517L;


	public PersistenciaException(String erro) {
		super(erro);
	}
	
	public PersistenciaException(Exception e) {
		super( e.getMessage());
	}

	
	public PersistenciaException(String erro, Exception e) {
		super(erro, e);
	}

}
