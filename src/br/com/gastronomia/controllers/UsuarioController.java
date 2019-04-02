package br.com.gastronomia.controllers;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import br.com.gastronomia.bo.UsuarioBO;
import br.com.gastronomia.dao.UsuarioDAO;
import br.com.gastronomia.dto.StandardResponseDTO;
import br.com.gastronomia.exception.PersistenciaException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Usuario;
import br.com.gastronomia.util.EncryptUtil;

@Path("usuarios")
public class UsuarioController {
	private UsuarioBO usuarioBO = new UsuarioBO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private EncryptUtil encryptUtil = new EncryptUtil();

	@Context
	private HttpServletRequest request;
	private HttpSession session;

	@GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response list() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(usuarioBO.listUser()).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@GET
	@Path("/professores")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response listProfs() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(usuarioBO.listProf()).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@GET
	@Path("/alunos")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response listAlunos() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(usuarioBO.listAlunos()).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Path("/")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response create(Usuario usuario) throws PersistenciaException, ValidationException {
		try {
			usuarioBO.createUser(usuario);
		} catch (Exception e) {
			return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
		}

		return Response.ok().entity(new StandardResponseDTO(true, "Usuario "+usuario.getNome()+ " criado com sucesso!")).status(Response.Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

		try {
			usuarioBO.deactivateUser(id);
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Usuario desativado com sucesso!")).status(Response.Status.ACCEPTED).build();

	}

	@GET
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

		try {
			return Response.ok().entity(usuarioBO.getUserById(id)).status(Response.Status.ACCEPTED).build();

		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@PUT
	@Path("/update")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response update(Usuario usuario) throws PersistenciaException, ValidationException {

		try {
			usuarioBO.updateUser(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Usuario "+usuario.getNome()+ " editado com sucesso!")).status(Response.Status.ACCEPTED).build();
	}

	@POST
	@Path("/redefinir/{email}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response redefinirSenha(@PathParam("email") String email) throws PersistenciaException, ValidationException {

		try {
			Usuario myUser = usuarioDAO.findUserByEmail(email);
			usuarioBO.esqueceuSenha(myUser.getEmail(),myUser.getNome(), myUser.getId());
			return Response.ok().status(Response.Status.ACCEPTED).build();

		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}


}
