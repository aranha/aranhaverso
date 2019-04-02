package br.com.gastronomia.dao.dietoterapia;

import br.com.gastronomia.db.GenericHibernateDAO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.dietoterapia.AtendimentoNutricional;
import br.com.gastronomia.model.dietoterapia.Paciente;

public class AtendimentoNutricionalDAO extends GenericHibernateDAO<AtendimentoNutricional> {


    public long criarAtendimentoNutricional(AtendimentoNutricional atendimentoNutricional) throws ValidationException {
        return  save(atendimentoNutricional);
    }

    public AtendimentoNutricional findAtendimentoByID(long id) {
          return (AtendimentoNutricional) findSingleObject("id", AtendimentoNutricional.class, id);
      }

    public long alterStatus(long id, boolean status) throws ValidationException  {
        AtendimentoNutricional atendimentoNutricional = findAtendimentoByID(id);
        atendimentoNutricional.setStatus(status);
        return merge(atendimentoNutricional);
    }

    public long updateAtendimento(AtendimentoNutricional atendimentoNutricional) throws ValidationException {
        return merge(atendimentoNutricional);
    }

}
