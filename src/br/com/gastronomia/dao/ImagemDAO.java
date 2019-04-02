package br.com.gastronomia.dao;

import java.util.List;

import br.com.gastronomia.model.Imagem;
import br.com.gastronomia.exception.ValidationException;
import org.hibernate.Session;

import br.com.gastronomia.db.GenericHibernateDAO;
import br.com.gastronomia.db.HibernateUtil;
import br.com.gastronomia.model.Atributo;


public class ImagemDAO extends GenericHibernateDAO<Imagem> {

	// list

	public long removeImagem(Imagem imagem) throws ValidationException {
		return remove(imagem);
	}

	public Imagem findImagemByID(long id) throws ValidationException {
		return (Imagem) findId(id, Imagem.class);
	}

	public long updateImagem(Imagem imagem) throws ValidationException {
		return merge(imagem);
	}
}
