package br.com.gastronomia.db;

import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.imp.GenericDAO;
import br.com.gastronomia.model.Receita;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

@SuppressWarnings("unchecked")

public class GenericHibernateDAO<T> implements GenericDAO<T> {

	@Override
	public long save(T obj) throws ValidationException, ConstraintViolationException{
		try {
			Session session = HibernateUtil.getFactory();
			Transaction tx = null;
			long id = 0;
			try {
				tx = session.beginTransaction();
				id = (long) session.save(obj);
				tx.commit();
			} catch (org.hibernate.exception.ConstraintViolationException e) {
				throw e;
			}
			catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
			System.out.println("ID: " + id);
			return id;
		} catch (ConstraintViolationException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro de Exception no salvar do GenericHibernateDAO: " + e.getMessage());
			throw new ValidationException(e.getMessage());
		}
    }

	@Override
	public long remove(T obj) throws ValidationException {
		Session session = HibernateUtil.getFactory();
		Transaction tx = null;
		long sucess = 0;
		try {
			tx = session.beginTransaction();
			session.remove(obj);
			tx.commit();
			return 1;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			System.out.println("Erro de HibernateException ao excluir no GenericHibernateDAO: " + e.getMessage());
            throw new ValidationException("invalido");
        } finally {
			session.close();
		}
	}

	@Override
	public List<T> listAll(Class<?> T) {
		Session session = HibernateUtil.getFactory();
		String queryAll = "Select t from " + T.getSimpleName() + " t ";
		List<T> objects = session.createQuery(queryAll).list();
		session.close();
		return objects;
	}


    public List<T> listAllOrder(Class<?> T, String field) {
        Session session = HibernateUtil.getFactory();
        String queryAll = "Select t from " + T.getSimpleName() + " t ORDER BY "+field+" ASC ";
        List<T> objects = session.createQuery(queryAll).list();
        session.close();
        return objects;
    }

	@Override
	public T findId(long id,Class<?> c) throws ValidationException {

		Session session = HibernateUtil.getFactory();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			T t = (T) session.find(c,id);
			return t;
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			System.out.println("Erro de HibernateException ao excluir no GenericHibernateDAO: " + e.getMessage());
            throw new ValidationException("invalido");
		} finally {
			session.close();
		}

    }
	@SuppressWarnings("deprecation")
	@Override
	public String findSingleResultString(String parameter, Object T, String valueParameter, String field) {
		Session session = HibernateUtil.getFactory();
		// Deus me perdoe por tudo que tive que fazer para isto vir a funcionar
		// <2
		String hql = "Select T." + field + " FROM " + T.getClass().getSimpleName() + " T  where T." + parameter
				+ " = ?";
		String results =  session.createQuery(hql).setString(0, valueParameter).getSingleResult().toString().replace("[", "")
				.replace("]", "");
		session.close();
		return results;
		// return session.createQuery(hql).getSingleResult().toString();
	}

	@Override
	public String findMultipleResultString(String parameter, Object T, String valueParameter, String field) {
		Session session = HibernateUtil.getFactory();
		// Select T.password from FROM Usuario T WHERE T.cpf = 10
		String sql = "Select T." + field + " FROM " + T.getClass().getSimpleName() + " T  where T." + parameter + " ="
				+ valueParameter;
		String results = session.createQuery(sql).list().toString();
        session.close();
		return results;
	}

	@Override
	public Object findSingleObject(String parameter, Class<?> T, Object valueParameter) {
		Session session = HibernateUtil.getFactory();

		String hql = "Select T FROM " + T.getSimpleName() + " T  where T." + parameter + " = :" +parameter ;
		Object results =  session.createQuery(hql).setParameter(parameter, valueParameter).getSingleResult();
		session.close();

		return  results;
	}

	public List<T> findMultipleObjects(String parameter, Class<?> T, Object valueParameter) {
		Session session = HibernateUtil.getFactory();
		List <T> results;
		String hql = "Select T FROM " + T.getSimpleName() + " T where T." + parameter + " = ?" ;
		if(valueParameter instanceof Boolean)
			results =  session.createQuery(hql).setBoolean(0, (Boolean) valueParameter).list(); //Deprecated
		else
			results =  session.createQuery(hql).setString(0, valueParameter.toString()).list(); //Deprecated
		session.close();
		return  results;
	}


	@Override
	public long merge(Object T) throws ValidationException {
		Session session = HibernateUtil.getFactory();
		Transaction tx = null;
		long id = 0;
		try {
			tx = session.beginTransaction();
			session.merge(T);
			tx.commit();
			id = 1;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
            throw new ValidationException("invalido");
		} finally {
			session.close();
		}
		return id;

	}

}
