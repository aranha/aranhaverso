package br.com.gastronomia.dao;

import java.util.List;

import br.com.gastronomia.exception.ValidationException;
import org.hibernate.Session;

import br.com.gastronomia.db.GenericHibernateDAO;
import br.com.gastronomia.db.HibernateUtil;
import br.com.gastronomia.model.Atributo;


public class AtributoDAO extends GenericHibernateDAO<Atributo> {
	
	public List<Atributo> listForName(Object Atributo, String q) {
		Session session = HibernateUtil.getFactory();
		List<Atributo> atributos = session.getNamedQuery("findAtributoForName").setParameter("atributoNome", q).list();
		return atributos;
	}

	public long removeAtributo(Atributo atributo) throws ValidationException {
		return remove(atributo);
	}

	public Atributo findAtributoByID(long id) throws ValidationException {
		return (Atributo) findId(id, Atributo.class);
	}

	public long updateAtributo(Atributo atributo) throws ValidationException {
		return merge(atributo);
	}

	public long alterStatus(long id, boolean status) throws ValidationException {
		Atributo atributo = findAtributoByID(id);
		atributo.setStatus(status);
		return merge(atributo);
	}

	public List<Atributo> listAllAtributos(){
		List<Atributo> atributos = listAll(Atributo.class);
		return atributos;
	}
}
