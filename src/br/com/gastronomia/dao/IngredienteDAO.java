package br.com.gastronomia.dao;

import br.com.gastronomia.db.GenericHibernateDAO;
import br.com.gastronomia.db.HibernateUtil;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Ingrediente;
import br.com.gastronomia.model.ReceitaIngrediente;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class IngredienteDAO extends GenericHibernateDAO<Ingrediente> {
	public List<Ingrediente> listForName(Object Ingrediente, String q) {
		Session session = HibernateUtil.getFactory();
		List<Ingrediente> ingredientes = session.getNamedQuery("findUserForName").setParameter("userName", q).list();
		return ingredientes;
	}

	public List<Ingrediente> listAllIngredientes(){
		List<Ingrediente> ingredientes = listAll(Ingrediente.class)
				.stream()
				.filter(ingrediente -> { return ingrediente.getStatus(); })
				.collect(Collectors.toList());
		return ingredientes;
	}

	public Ingrediente findIngredienteByName(String nome) {
		return (Ingrediente) findSingleObject("Nome", Ingrediente.class, nome);
	}

	public Ingrediente findIngredienteById(long id) {
		return (Ingrediente) findSingleObject("id", Ingrediente.class, id);
	}

	public List<Ingrediente> findReceitaIngredienteByIdIngrediente(Long id) {
		return findMultipleObjects("ingrediente", ReceitaIngrediente.class, id);
	}

	public long updateIngrediente(Ingrediente ingrediente) throws ValidationException {
		return merge(ingrediente);
	}

	public long alterStatus(long id, boolean statusBool) throws ValidationException {
		Ingrediente ingrediente = findIngredienteById(id);
		ingrediente.setStatus(statusBool);
		return merge(ingrediente);
	}


}
