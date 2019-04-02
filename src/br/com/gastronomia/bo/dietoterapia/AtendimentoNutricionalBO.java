package br.com.gastronomia.bo.dietoterapia;

import br.com.gastronomia.dao.UsuarioDAO;
import br.com.gastronomia.dao.dietoterapia.AtendimentoNutricionalDAO;
import br.com.gastronomia.dao.dietoterapia.PacienteDAO;
import br.com.gastronomia.dto.AtendimentoNutricionalDTO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Usuario;
import br.com.gastronomia.model.dietoterapia.AtendimentoNutricional;
import br.com.gastronomia.model.dietoterapia.Paciente;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AtendimentoNutricionalBO {
    private AtendimentoNutricionalDAO atendimentoDAO;
    private PacienteDAO pacienteDAO;
    private UsuarioDAO usuarioDAO;

    public AtendimentoNutricionalBO() {
        atendimentoDAO = new AtendimentoNutricionalDAO();
        pacienteDAO = new PacienteDAO();
        usuarioDAO = new UsuarioDAO();
    }

    public Map<String, List<AtendimentoNutricional>> list() {

        try {
            return Collections.singletonMap("Atendimentos", atendimentoDAO.listAll(AtendimentoNutricional.class));
        } catch (final Exception e) {
            return Collections.singletonMap("Atendimentos", Collections.emptyList());
        }


    }

    public AtendimentoNutricional criarAtendimento(AtendimentoNutricionalDTO dto) throws ValidationException {
        if (dto == null)
            throw new IllegalArgumentException();

        try {
            Usuario aluno = usuarioDAO.findUserByID(dto.getIdAluno());
            Usuario professor = usuarioDAO.findUserByID(dto.getIdProfessor());
            Paciente paciente = pacienteDAO.findPacienteByID(dto.getIdPaciente());

            AtendimentoNutricional atendimento = new AtendimentoNutricional(aluno, professor, paciente, dto.getData(), dto.getStatus());
            long id = atendimentoDAO.save(atendimento);
            atendimento.setId(id);

            return atendimento;
        }
        catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    public long deactivateAtendimentoNutricional(long id) throws ValidationException {
        return atendimentoDAO.alterStatus(id, false);
    }

    public long atualizarAtendimento(AtendimentoNutricionalDTO dto) throws NoSuchAlgorithmException, ValidationException {
        if (dto == null)
            throw new IllegalArgumentException();

        Usuario aluno = usuarioDAO.findUserByID(dto.getIdAluno());
        Usuario professor = usuarioDAO.findUserByID(dto.getIdProfessor());
        Paciente paciente = pacienteDAO.findPacienteByID(dto.getIdPaciente());

        AtendimentoNutricional atendimento = new AtendimentoNutricional(aluno, professor, paciente, dto.getData(), dto.getStatus());
        atendimento.setId(dto.getId());

        if (atendimentoDAO.findAtendimentoByID(dto.getId()) == null)
            return atendimentoDAO.save(atendimento);
        else
            return atendimentoDAO.updateAtendimento(atendimento);
    }

  public AtendimentoNutricional getById(long id) throws ValidationException {
        if (id > 0) {
            AtendimentoNutricional atendimento = atendimentoDAO.findAtendimentoByID(id);
            return atendimento;
        }
        throw new ValidationException("inválido");

    }

}
