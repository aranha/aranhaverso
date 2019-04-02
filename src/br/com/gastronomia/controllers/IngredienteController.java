package br.com.gastronomia.controllers;

import br.com.gastronomia.bo.IngredienteBO;
import br.com.gastronomia.dto.IngredienteCadastroDTO;
import br.com.gastronomia.dto.StandardResponseDTO;
import br.com.gastronomia.exception.PersistenciaException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Ingrediente;
import br.com.gastronomia.model.IngredienteAtributo;
import br.com.gastronomia.model.Usuario;
import br.com.gastronomia.util.EncryptUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("ingredientes")
public class IngredienteController {
	private IngredienteBO ingredienteBO = new IngredienteBO();
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
			return Response.ok().entity(ingredienteBO.listIngrediente()).status(Response.Status.ACCEPTED).build();

		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("/")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response create(Ingrediente ingrediente) throws PersistenciaException, ValidationException {
		try {
			ingredienteBO.createIngrediente(ingrediente);
		} catch (Exception e) {
			return Response.ok().entity(new StandardResponseDTO(true, e.getMessage())).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Ingrediente "+ingrediente.getNome()+", criado com sucesso!")).status(Response.Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

		try {
			ingredienteBO.inactiveIngrediente(id);

		} catch (Exception e) {

			return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Ingrediente desativado com sucesso!")).status(Response.Status.ACCEPTED).build();

	}
	@GET
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

		try {
			return Response.ok().entity(ingredienteBO.getIngredienteById(id)).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@PUT
	@Path("/update")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response update(Ingrediente ingrediente) throws PersistenciaException, ValidationException {
		try {
			ingredienteBO.updateIngrediente(ingrediente);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Ingrediente "+ingrediente.getNome()+ " editado com sucesso!")).status(Response.Status.ACCEPTED).build();

	}
}
