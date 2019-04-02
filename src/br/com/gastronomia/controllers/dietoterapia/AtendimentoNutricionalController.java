package br.com.gastronomia.controllers.dietoterapia;

import br.com.gastronomia.bo.dietoterapia.AtendimentoNutricionalBO;
import br.com.gastronomia.dto.StandardResponseDTO;
import br.com.gastronomia.exception.PersistenciaException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.dto.AtendimentoNutricionalDTO;
import br.com.gastronomia.model.dietoterapia.AtendimentoNutricional;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("atendimentos")
public class AtendimentoNutricionalController {
    private AtendimentoNutricionalBO atendimentoBO = new AtendimentoNutricionalBO();

    @Context
    private HttpServletRequest request;


    @POST
    @Path("/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")

    public Response create(AtendimentoNutricionalDTO atendimentoNutriocional) throws PersistenciaException, ValidationException{
        try {
            AtendimentoNutricional resultado = atendimentoBO.criarAtendimento(atendimentoNutriocional);
            return Response.ok().entity(resultado).status(Response.Status.ACCEPTED).build();
        } catch (final Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/")
    @Produces("application/json; charset=UTF-8")
    public Response list() {
        try {
            return Response.ok().entity(atendimentoBO.list()).status(Response.Status.ACCEPTED).build();
        } catch (final Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json; charset=UTF-8")
    public Response searchById(@PathParam("id") long id) {
        try {
            return Response.ok().entity(atendimentoBO.getById(id)).status(Response.Status.ACCEPTED).build();
        } catch (final Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response remove(@PathParam("id") long id) throws PersistenciaException, ValidationException {

        try {
            atendimentoBO.deactivateAtendimentoNutricional(id);
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(new StandardResponseDTO(true, "Atendimento desativado com sucesso!")).status(Response.Status.ACCEPTED).build();

    }



    @PUT
    @Path("/update")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response update(AtendimentoNutricionalDTO atendimento) throws PersistenciaException, ValidationException {

        try {
            atendimentoBO.atualizarAtendimento(atendimento);
        } catch (ValidationException e) {
            return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(new StandardResponseDTO(true, "AtendimentoNutricionalBO " + atendimento.getId() + " atualizado com sucesso!")).status(Response.Status.ACCEPTED).build();
    }
}


