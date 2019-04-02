package br.com.gastronomia.testsDAO;

import br.com.gastronomia.dao.UsuarioDAO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Usuario;

/**
 * 
 * @author cassio.trindade
 * Classe para teste do DAO Hibernate
 */
public class TestUserDAO {
	public static void main(String[] args) {
		
		Usuario usuario = new Usuario();
		usuario.setCpf("99000090909");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			System.out.println(usuarioDAO.save(usuario));
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
}
