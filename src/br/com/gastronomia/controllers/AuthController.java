package br.com.gastronomia.controllers;

import br.com.gastronomia.bo.UsuarioBO;
import br.com.gastronomia.dao.UsuarioDAO;
import br.com.gastronomia.dto.StandardResponseDTO;
import br.com.gastronomia.dto.UsuarioFormattedDTO;
import br.com.gastronomia.dto.UsuarioLoginDTO;
import br.com.gastronomia.exception.PersistenciaException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Token;
import br.com.gastronomia.model.Usuario;
import br.com.gastronomia.util.EncryptUtil;
import br.com.gastronomia.util.TokenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("auth")
public class AuthController {
	private Logger logger = Logger.getLogger("controller.AuthController");

	private UsuarioBO usuarioBO = new UsuarioBO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	private EncryptUtil encryptUtil = new EncryptUtil();
	@Context
	private HttpServletResponse response;
	@Context
	private HttpServletRequest request;
	private HttpSession session = null;

	@POST
	@Path("/login")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response login(UsuarioLoginDTO usuarioLogin) {
		Usuario usuario;
		Token token = new Token();

		try {
			ObjectMapper mapper = new ObjectMapper();
			usuario = usuarioBO.userExists(usuarioLogin);
			token.setToken(TokenGenerator.issueToken(mapper.writeValueAsString(usuario)));
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(e.getMessage()).status(UNAUTHORIZED).build();
		}

//		Map<String, Object> response = new HashMap<>(2);
//		response.put("token", token.getToken());
//		response.put("usuario", usuario);
//
//		return Response.ok(response).build();

		return Response.ok(usuario).build();
	}

	/**
	 * Desloga o usu�rio logado.
	 *
	 * @return Objeto com a resposta do
	 *         m�todo.{@link br.com.gastronomia.dto.StandardResponseDTO}
	 **/
	@GET
	@Path("/logout")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO logoutUser() {
		request.getSession().invalidate();
		return new StandardResponseDTO(true, "Deslogado");
	}

	/**
	 * Verifica se existe um usu�rio logado no sistema.
	 *
	 * @return Retorna o usu�rio logado.
	 * @throws IOException
	 **/
	@GET
	@Path("/me")
	@Produces("application/json; charset=UTF-8")
	public UsuarioFormattedDTO getMe() throws IOException {

		logger.debug("Session ME: " + new Date() + " - " + request.getSession().hashCode());

		System.out.println(request.getSession().getAttributeNames().toString());
		request.getSession().getAttribute("usuario");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

		if (usuario != null) {
			logger.debug("Usuario inserido na session: " + new Date() + " - " + usuario.toString());
			return UsuarioFormattedDTO.getFromUser(usuario);
		}

		logger.debug("Usuario não existe na session");
		response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
		response.flushBuffer();
		return null;
	}

	@POST
	@Path("/recoverPasswordRequest")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO recoverPasswordRequest(Usuario usuarioLogin) throws IOException {

		Usuario usuario;
		StandardResponseDTO responseJson = new StandardResponseDTO();

		try {
			usuario = usuarioBO.getUserByCpf(usuarioLogin);

			if (usuario.isStatus() == false) {
				responseJson.setMessage("inativo");
				responseJson.setSuccess(false);
				return responseJson;
			}

			StringBuffer reqURL = request.getRequestURL();
			String reqURI = request.getRequestURI();
			String baseURL = request.getRequestURL().substring(0, reqURL.length() - reqURI.length()) + "/";

			// passwordChangeBO.createPasswordChangeRequest(usuario, baseURL);

			responseJson.setSuccess(true);
			responseJson.setMessage(
					 usuario.getEmail());
		} catch (Exception e) {
			response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
			response.flushBuffer();
			responseJson.setSuccess(false);
			responseJson.setMessage(e.getMessage());
		}

		return responseJson;
	}

	@PUT
	@Path("/{encryptedMatricula}")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response authenticate(@PathParam("encryptedMatricula")String encryptedMatricula) throws PersistenciaException, ValidationException {
		try {
			String matricula = EncryptUtil.decrypt(encryptedMatricula);
			Usuario user = usuarioDAO.findUserByMatricula(matricula);
			usuarioBO.activateUser(user.getId());
			System.out.println("deu bom");
		} catch (Exception e) {
			e.printStackTrace();
            System.out.println("deu ruim");
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Usuario autenticado com sucesso!")).status(Response.Status.ACCEPTED).build();

	}
}
