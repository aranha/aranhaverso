package br.com.gastronomia.controllers.dietoterapia;

import br.com.gastronomia.bo.dietoterapia.PacienteBO;
import br.com.gastronomia.dao.dietoterapia.PacienteDAO;
import br.com.gastronomia.dto.StandardResponseDTO;
import br.com.gastronomia.exception.PersistenciaException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Usuario;
import br.com.gastronomia.model.dietoterapia.Paciente;
import br.com.gastronomia.util.EncryptUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Date;

@Path("pacientes")
public class PacienteController {
    private PacienteBO pacienteBO = new PacienteBO();
    private PacienteDAO usuarioDAO = new PacienteDAO();
    private EncryptUtil encryptUtil = new EncryptUtil();

    @Context
    private HttpServletRequest request;
    private HttpSession session;

    @POST
    @Path("/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response create(Paciente paciente) throws PersistenciaException, ValidationException {
        try {
            Paciente resultado = pacienteBO.criarPaciente(paciente);
            return Response.ok().entity(resultado).status(Response.Status.ACCEPTED).build();
        } catch (Exception e) {
            return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

        try {
            return Response.ok().entity(pacienteBO.getUserById(id)).status(Response.Status.ACCEPTED).build();

        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }

    }

    @GET
    @Path("/")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response list() throws PersistenciaException, SQLException {
        try {
            return Response.ok().entity(pacienteBO.list()).status(Response.Status.ACCEPTED).build();
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }

    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

        try {
            pacienteBO.deactivatePaciente(id);
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(new StandardResponseDTO(true, "Paciente desativado com sucesso!")).status(Response.Status.ACCEPTED).build();

    }

    @PUT
    @Path("/update")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response update(Paciente paciente) {

        try {
            pacienteBO.updatePaciente(paciente);
        } catch (ValidationException e) {
            return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(new StandardResponseDTO(true, "Paciente "+paciente.getNome()+ " editado com sucesso!")).status(Response.Status.ACCEPTED).build();
    }

}
