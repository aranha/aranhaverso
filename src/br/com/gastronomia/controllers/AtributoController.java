package br.com.gastronomia.controllers;

import java.sql.SQLException;

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

import br.com.gastronomia.bo.AtributoBO;
import br.com.gastronomia.dao.AtributoDAO;
import br.com.gastronomia.dto.StandardResponseDTO;
import br.com.gastronomia.exception.PersistenciaException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Atributo;
import br.com.gastronomia.util.EncryptUtil;

@Path("atributos")
public class AtributoController {
	private AtributoBO atributoBO = new AtributoBO();
	private AtributoDAO atributoDAO = new AtributoDAO();
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
			return Response.ok().entity(atributoBO.listAtributos()).status(Response.Status.ACCEPTED).build();


		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@GET
	@Path("/ativos")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response listActives() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(atributoBO.listActivesAtributos()).status(Response.Status.ACCEPTED).build();


		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Path("/")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response create(Atributo atributo) throws PersistenciaException, ValidationException {

		try {
			atributoBO.createAtributo(atributo);
		} catch (Exception e) {
			return Response.ok().entity(new StandardResponseDTO(true, e.getMessage())).status(Response.Status.BAD_REQUEST).build();
		}

		return Response.ok().entity(new StandardResponseDTO(true, "Atributo "+atributo.getNome()+", criado com sucesso!")).status(Response.Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException {
		try {
			atributoBO.inactiveAtributo(id);

		} catch (ValidationException e) {

			return Response.ok().entity(new StandardResponseDTO(true, e.getMessage())).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Atributo excluído com sucesso!")).status(Response.Status.ACCEPTED).build();
	}
	@GET
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

		try {
			return Response.ok().entity(atributoBO.getAtributoById(id)).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {

			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@PUT
	@Path("/update")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response update(Atributo atributo) throws PersistenciaException, ValidationException {

		try {
			atributoBO.updateAtributo(atributo);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Atributo: "+atributo.getNome()+" editado com sucesso!")).status(Response.Status.ACCEPTED).build();

	}
}
