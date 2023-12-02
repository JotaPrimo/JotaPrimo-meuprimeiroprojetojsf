package br.com.jpautil;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {

	private EntityManagerFactory factory = null;

	public JPAUtil() {
		if (factory == null) {
			factory = Persistence
					.createEntityManagerFactory("meuprimeiroprojetojsf");
		}
	}


	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	

	public Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

}
