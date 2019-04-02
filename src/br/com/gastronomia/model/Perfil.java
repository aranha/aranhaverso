package br.com.gastronomia.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Classe de modelo para validar a senha do usuario.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public class Perfil extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	private String passwordToValidate;

	public Perfil() {
		super();
	}

	@JsonIgnore
	public String getPasswordToValidate(){
		return passwordToValidate;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setPasswordToValidate(String passwordToValidate) {
		this.passwordToValidate = passwordToValidate;
	}



}
