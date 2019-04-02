package br.com.gastronomia.imp;

import br.com.gastronomia.exception.ValidationException;

import java.util.List;

public interface GenericDAO<T> {
	public long save(T obj) throws ValidationException;
	public long remove(T obj) throws ValidationException;
	public List<T> listAll(Class<?> T);
	public T findId(long id,Class<?> c) throws ValidationException;
	public String findSingleResultString(String parameter, Object T, String valueParameter, String field);
	public long merge(Object T) throws ValidationException;
	String findMultipleResultString(String parameter, Object T, String valueParameter, String field);
	Object findSingleObject(String parameter, Class<?> T, Object valueParameter);
}
