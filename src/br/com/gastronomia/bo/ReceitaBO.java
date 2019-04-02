package br.com.gastronomia.bo;

import br.com.gastronomia.dao.ReceitaDAO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Receita;
import org.hibernate.exception.ConstraintViolationException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceitaBO {

    private ReceitaDAO receitaDAO;

    public ReceitaBO() {
        receitaDAO = new ReceitaDAO();
    }

    public long inactiveReceita(long id) throws ValidationException  {
        return receitaDAO.alterStatus(id, false);
    }

    public long activateReceita(long id) throws ValidationException  {
        return receitaDAO.alterStatus(id, true);
    }

    public long updateReceita(Receita receita) throws ValidationException {
        if (receita != null) {
            try {
                return receitaDAO.updateReceita(receita);
            } catch (ConstraintViolationException e) {
                throw new ValidationException(getExceptionMessage(e));
            }
            catch (Exception e) {
                throw new ValidationException(e.getMessage());
            }
        }
        throw new ValidationException("invalido");

    }

    public boolean createReceita(Receita receita) throws ValidationException, NoSuchAlgorithmException {
        if (receita != null) {
            System.out.println(receita);
            try {
                receitaDAO.save(receita);
            } catch (ConstraintViolationException e) {
               throw new ValidationException(getExceptionMessage(e));
            }
            catch (Exception e) {
                throw new ValidationException(e.getMessage());
            }
            return true;
        }
        throw new ValidationException("Houve um problema com o cadastro de receita. Verifique todos os campos.");

    }

    public String getExceptionMessage(ConstraintViolationException e) {
        switch (e.getConstraintName()) {
            case "FK_RECEITA_GRUPORECEITA":
                return ("Ficha Técnica de Preparo deve ter um grupo de receita.");
            case "FK_RECEITA_IMAGEM":
                return ("Ficha Técnica de Preparo deve ter uma imagem.");
            case "FK_NOTA_AVALIADOR":
                return ("Ficha Técnica de Preparo, houve um erro ao atribuir um avaliador à nota. Este avaliador existe?");
            case "FK_RECEITA_PROFESSOR":
                return ("Ficha Técnica de Preparo deve ter um professor.");
            default:
                return ("Preencha todos os campos obrigatórios antes de prosseguir.");
        }
    }

    public HashMap<String, List<Receita>> listReceita() {
        ArrayList<Receita> receitas = null;
        HashMap<String, List<Receita>> listReceitas = new HashMap<String, List<Receita>>();
        receitas = (ArrayList<Receita>) receitaDAO.listAllReceitas();
        listReceitas.put("Receitas", receitas);
        return listReceitas;
    }

    public HashMap<String, List<Receita>> listReceitasAtivas() {
        ArrayList<Receita> receitas = null;
        HashMap<String, List<Receita>> listReceitas = new HashMap<String, List<Receita>>();
        receitas = (ArrayList<Receita>) receitaDAO.listReceitasAtivas();
        listReceitas.put("Receitas", receitas);
        return listReceitas;
    }

    public Receita getReceitaById(Long id) throws ValidationException {
        if (id > 0) {
            return receitaDAO.findReceitaById(id);
        }
        throw new ValidationException("invalido");

    }

    public List<Receita> getReceitaByIdUsuario(Long id) throws ValidationException {
        if (id > 0) {
            return receitaDAO.findReceitaByIdUsuario(id);
        }
        throw new ValidationException("invalido");

    }

}
