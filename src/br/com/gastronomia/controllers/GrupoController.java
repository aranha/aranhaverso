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

import br.com.gastronomia.bo.GrupoReceitasBO;
import br.com.gastronomia.dao.GrupoReceitasDAO;
import br.com.gastronomia.dto.StandardResponseDTO;
import br.com.gastronomia.exception.PersistenciaException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.GrupoReceitas;
import br.com.gastronomia.util.EncryptUtil;

@Path("grupos")
public class GrupoController {
	private GrupoReceitasBO grupoReceitasBO = new GrupoReceitasBO();
	private GrupoReceitasDAO grupoReceitasDAO = new GrupoReceitasDAO();
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
			return Response.ok().entity(grupoReceitasBO.listGroups()).status(Response.Status.ACCEPTED).build();


		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}@GET
	@Path("/ativos")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response listActived() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(grupoReceitasBO.listGroupsActived()).status(Response.Status.ACCEPTED).build();


		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Path("/")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response create(GrupoReceitas grupoReceitas) throws PersistenciaException, ValidationException {

		try {

			grupoReceitasBO.createGroup(grupoReceitas);

		} catch (Exception e) {
			return Response.ok().entity(new StandardResponseDTO(true, e.getMessage())).status(Response.Status.BAD_REQUEST).build();
		}

		return Response.ok().entity(new StandardResponseDTO(true, "Grupo de Receita "+grupoReceitas.getNome()+ " criado com sucesso!")).status(Response.Status.ACCEPTED).build();
	}


	@DELETE
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException {

		try {
			grupoReceitasBO.deactivateGroup(id);

		} catch (ValidationException e) {

			return Response.ok().entity(new StandardResponseDTO(true, e.getMessage())).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Grupo de Receita excluído com sucesso!")).status(Response.Status.ACCEPTED).build();

	}
	@GET
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

		try {
			
			return Response.ok().entity(grupoReceitasBO.getGroupByCod(id)).status(Response.Status.ACCEPTED).build();

		} catch (Exception e) {

			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@PUT
	@Path("/update")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response update(GrupoReceitas grupo) throws PersistenciaException, ValidationException {

		try {
			grupoReceitasBO.updateGroup(grupo);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Grupo de Receita "+ grupo.getNome()+ " editado com sucesso!")).status(Response.Status.ACCEPTED).build();

	}
}
