package br.com.gastronomia.db;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeneratedDB {
	public static Session factory;


	public GeneratedDB() {

	}

	public static void createConnection() {
		factory = HibernateUtil.getFactory();

	}

	public static void generatedDB() {
		createConnection();

		factory.getTransaction().begin();
		factory.getTransaction().commit();

		factory.close();

	}
}
