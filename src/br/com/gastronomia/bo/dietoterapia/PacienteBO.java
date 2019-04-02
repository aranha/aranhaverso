package br.com.gastronomia.bo.dietoterapia;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.gastronomia.dao.dietoterapia.PacienteDAO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.dietoterapia.Paciente;
import org.hibernate.exception.ConstraintViolationException;

public class PacienteBO {

    private PacienteDAO pacienteDAO;

    public PacienteBO() {
        pacienteDAO = new PacienteDAO();
    }

    public Paciente criarPaciente(Paciente paciente) throws NoSuchAlgorithmException, ValidationException {
        if (paciente != null) {
            try {
                if(!sexoValido(paciente)) {
                    throw new ValidationException("Sexo do paciente a ser criado inválido.");
                }
                pacienteDAO.save(paciente);
                paciente.setStatus(true);
                return paciente;
            }
            catch (ConstraintViolationException e) {
                throw new ValidationException(e.getMessage());
            }
            catch (Exception e) {
                throw new ValidationException(e.getMessage());
            }
        }
        return null;
    }
    public Paciente getUserById(long id) throws ValidationException {
        if (id > 0) {
            Paciente paciente = pacienteDAO.findPacienteByID(id);
            return paciente;
        }
        throw new ValidationException("inválido");

    }
    public HashMap<String, List<Paciente>> list() {
        ArrayList<Paciente> pacientes = null;
        HashMap<String, List<Paciente>> list = new HashMap<String, List<Paciente>>();
        pacientes = (ArrayList<Paciente>) pacienteDAO.listAll(Paciente.class);
        list.put("Pacientes", pacientes);
        return list;
    }


    public long deactivatePaciente(long id) throws ValidationException {
        return pacienteDAO.alterStatus(id, false);
    }

    public long activatePaciente(long id) throws ValidationException {
        return pacienteDAO.alterStatus(id, true);
    }

    public long updatePaciente(Paciente paciente) throws ValidationException {
        if (paciente != null) {
            if(getUserById(paciente.getId()) != null) {

                if(!sexoValido(paciente)) {
                    throw new ValidationException("Sexo do paciente a ser criado inválido.");
                }

                return pacienteDAO.updatePaciente(paciente);
            } else {
                throw new ValidationException("Paciente não existente");
            }
            //paciente.setStatus(true);
        } else {
            throw new ValidationException("Dados inseridos para atualização insuficientes.");
        }
    }

    // Checks

    private boolean sexoValido(Paciente paciente) {
        if(paciente.getSexo() == 'F' || paciente.getSexo() == 'M') {
            return true;
        }
        return false;
    }
}
