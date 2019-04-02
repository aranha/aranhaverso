package br.com.gastronomia.dao;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Usuario;
import br.com.gastronomia.util.MensagemContantes;
import org.hibernate.Session;
import br.com.gastronomia.util.TipoDeUsuario;
import br.com.gastronomia.db.GenericHibernateDAO;
import br.com.gastronomia.db.HibernateUtil;

public class UsuarioDAO extends GenericHibernateDAO<Usuario> {

	public Usuario findUserByCPF(String cpf) {
		return (Usuario) findSingleObject("cpf", Usuario.class, cpf);
	}

	public Usuario findUserByEmail(String email) {
		return (Usuario) findSingleObject("email", Usuario.class, email);
	}
	
	public Usuario findUserByMatricula(String matricula) {
		return (Usuario) findSingleObject("matricula", Usuario.class, matricula);
	}

	public List<Usuario> listUsersByType(TipoDeUsuario tipo) {
		return (List<Usuario>) findMultipleObjects("tipo", Usuario.class, tipo.name());
	}


	public long removeUser(Usuario usuario) throws ValidationException {
		return remove(usuario);
	}

	public Usuario findUserByID(long id) throws ValidationException  {
		return (Usuario) findId(id, Usuario.class);
	}

	public long updateUser(Usuario usuario) throws ValidationException  {
		return merge(usuario);
	}

	public long alterStatus(long id, boolean status) throws ValidationException  {
		Usuario usuario = findUserByID(id);
		usuario.setStatus(status);
		return merge(usuario);
	}
}
