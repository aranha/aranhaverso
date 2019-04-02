package br.com.gastronomia.dao;

import java.util.List;

import br.com.gastronomia.exception.ValidationException;
import org.hibernate.Session;

import br.com.gastronomia.db.GenericHibernateDAO;
import br.com.gastronomia.db.HibernateUtil;
import br.com.gastronomia.model.GrupoReceitas;

public class GrupoReceitasDAO extends GenericHibernateDAO<GrupoReceitas> {
	
	public List<GrupoReceitas> listForName(Object GrupoReceitas, String q) {
		Session session = HibernateUtil.getFactory();
		List<GrupoReceitas> grupos = session.getNamedQuery("findGroupByName").setParameter("groupName", q).list();
		return grupos;
	}


	public GrupoReceitas findGroupByCod(long codGrupos) {
		return (GrupoReceitas) findSingleObject("IdGrupo", GrupoReceitas.class, String.valueOf(codGrupos));
	}

	public GrupoReceitas findGroupByName(String nome) {
		return (GrupoReceitas) findSingleObject("Nome", GrupoReceitas.class, nome);
	}

	public long removeGroup(GrupoReceitas grupo) throws ValidationException {
		return remove(grupo);
	}

	public GrupoReceitas findGroupByID(long id) throws ValidationException {
		return (GrupoReceitas) findId(id, GrupoReceitas.class);
	}

	public long updateGroup(GrupoReceitas grupoReceitas) throws ValidationException {
		return merge(grupoReceitas);
	}

	public long alterStatus(long id, boolean status) throws ValidationException {
		GrupoReceitas grupoReceitas = findGroupByID(id);
		grupoReceitas.setStatus(status);
		return merge(grupoReceitas);
	}
}
