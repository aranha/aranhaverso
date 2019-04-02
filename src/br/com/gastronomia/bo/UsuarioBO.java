package br.com.gastronomia.bo;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.gastronomia.util.TipoDeUsuario;
import br.com.gastronomia.dao.UsuarioDAO;
import br.com.gastronomia.dto.UsuarioLoginDTO;
import br.com.gastronomia.exception.UsuarioInativoException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Usuario;
import org.hibernate.exception.ConstraintViolationException;
import br.com.gastronomia.util.EncryptUtil;
import br.com.gastronomia.util.MensagemContantes;
import br.com.gastronomia.util.Validator;
import br.com.gastronomia.util.SendMail;

public class UsuarioBO {
	
	private UsuarioDAO usuarioDAO;

	public UsuarioBO() {
		usuarioDAO = new UsuarioDAO();
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public boolean validateCPF(Usuario usuario) throws ValidationException {
		if (Validator.validaCpf.fazConta(usuario.getCpf()))
			return true;
		throw new ValidationException("invalido");

	}

	public boolean createUser(Usuario usuario) throws NoSuchAlgorithmException, ValidationException {
		if (usuario != null || !usuario.getSenha().isEmpty()) {
			String encryptedPassword = EncryptUtil.encrypt2(usuario.getSenha());
			usuario.setSenha(encryptedPassword);

			SendMail sendMail = new SendMail();
			String subject = "Confirmação de email";
//			String body = "localhost:8080/auth/" + EncryptUtil.encrypt2(String.valueOf(usuario.getMatricula()));
			String body = "Bem Vindo ao NUTRITECH. Acesse  o link http://www.homo.ages.pucrs.br/nutritech/ para começar";
			sendMail.envio(usuario.getEmail(), usuario.getNome(), subject, body);

			try {
				usuarioDAO.save(usuario);
			}
			catch (ConstraintViolationException e) {
				switch (e.getConstraintName()) {
					case "cpf_uc":
						throw new ValidationException("CPF inserido já cadastrado");
					case "email_uc":
						throw new ValidationException("Email inserido já cadastrado");
					case "matricula_uc":
						throw new ValidationException("Matricula inserida já cadastrada");
				}
			}
			catch (Exception e) {
				throw new ValidationException(e.getMessage());
			}
		}
		return true;
	}

	public void esqueceuSenha(String email, String nome, long id){
		String hash = email + System.nanoTime();

		SendMail sendMail = new SendMail();
		String subject = "NUTRITECH - Redefinir senha";
		String body = "Acesse  o link para redefinir sua senha: http://www.homo.ages.pucrs.br/nutritech/#/usuario/"+id;
		sendMail.envio(email,"",  subject, body);
	}

	public long deactivateUser(long id) throws ValidationException {
		return usuarioDAO.alterStatus(id, false);
	}

	public long activateUser(long id) throws ValidationException {
		return usuarioDAO.alterStatus(id, true);
	}

	public long updateUser(Usuario usuario) throws ValidationException, NoSuchAlgorithmException {
		if (usuario != null) {
			String encryptedPassword = null;
			if (usuario.getSenha().isEmpty()) {
				encryptedPassword = usuarioDAO.findUserByID(usuario.getId()).getSenha();
			} else {
				encryptedPassword = EncryptUtil.encrypt2(usuario.getSenha());
			}
			usuario.setSenha(encryptedPassword);
			return usuarioDAO.updateUser(usuario);
		}
		throw new ValidationException("invalido");

	}

	public Usuario validate(Usuario newUsuario) {
		return newUsuario;
	}

	public Usuario userExists(UsuarioLoginDTO usuarioLogin) throws NoSuchAlgorithmException, ValidationException, UsuarioInativoException {
		usuarioLogin.setSenha(EncryptUtil.encrypt2(usuarioLogin.getSenha()));
		Usuario returnedUsuario = null;
		try {
			returnedUsuario = usuarioDAO.findUserByEmail(usuarioLogin.getEmail());
		} catch(Exception e) {
			throw new ValidationException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
		}
		if (!usuarioLogin.getSenha().equals(returnedUsuario.getSenha()))
			throw new ValidationException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
		if (!returnedUsuario.isStatus())
			throw new UsuarioInativoException("Usuário Inativo");
		return returnedUsuario;
	}

	public HashMap<String, List<Usuario>> listUser() {
		ArrayList<Usuario> usuarios = null;
		HashMap<String, List<Usuario>> listUsers = new HashMap<String, List<Usuario>>();
		usuarios = (ArrayList<Usuario>) usuarioDAO.listAll(Usuario.class);
		listUsers.put("Usuarios", usuarios);
		return listUsers;
	}

	public HashMap<String, List<Usuario>> listProf() {
		ArrayList<Usuario> usuarios = null;
		HashMap<String, List<Usuario>> listUsers = new HashMap<String, List<Usuario>>();
		usuarios = (ArrayList<Usuario>) usuarioDAO.listUsersByType(TipoDeUsuario.PROFESSOR);
		listUsers.put("Usuarios", usuarios);
		return listUsers;
	}

	public HashMap<String, List<Usuario>> listAlunos() {
		ArrayList<Usuario> usuarios = null;
		HashMap<String, List<Usuario>> listUsers = new HashMap<String, List<Usuario>>();
		usuarios = (ArrayList<Usuario>) usuarioDAO.listUsersByType(TipoDeUsuario.USUARIO);
		listUsers.put("Usuarios", usuarios);
		return listUsers;
	}


	public Usuario getUserByCpf(Usuario usuarioLogin) throws ValidationException {
		if (usuarioLogin != null) {
			return usuarioDAO.findUserByCPF(usuarioLogin.getCpf());
		}
		throw new ValidationException("CPF Invalido");
	}
	
	public Usuario getUserByEmail(Usuario usuarioLogin) throws ValidationException {
		if (usuarioLogin != null) {
			return usuarioDAO.findUserByEmail(usuarioLogin.getEmail());
		}
		throw new ValidationException("Email Invalido");
	}
	
	public Usuario getUserByMatricula(Usuario usuarioLogin) throws ValidationException {
		if (usuarioLogin != null) {
			return usuarioDAO.findUserByMatricula(usuarioLogin.getMatricula());
		}
		throw new ValidationException("Email Invalido");
	}
	
	public Usuario getUserById(long id) throws ValidationException {
		if (id > 0) {
			Usuario usuario = usuarioDAO.findUserByID(id);
			usuario.setSenha("");
			return usuario;
		}
		throw new ValidationException("invalido");

	}

}
