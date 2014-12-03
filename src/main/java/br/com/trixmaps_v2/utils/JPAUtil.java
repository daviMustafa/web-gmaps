package br.com.trixmaps_v2.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("trixmaps_v2");

	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
/*	public static void main(String args[]){
		EntityManagerFactory factory = Persistence.
		          createEntityManagerFactory("trixmaps");

		    factory.close();
	}*/
}
