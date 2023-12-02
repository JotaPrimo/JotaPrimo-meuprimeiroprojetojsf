package br.com.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;


public class DaoGeneric<E> implements Serializable {
	
	
	public void salvar(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entidade);
		
		entityTransaction.commit();
	}
	
	
	public E merge(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		E retorno = entityManager.merge(entidade);
		
		entityTransaction.commit();
		
		return retorno;
	}
	
	
	public void delete(E entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.remove(entidade);
		
		entityTransaction.commit();
	}
	
	/*
	 * public void deletePorId(E entidade){ EntityManager entityManager =
	 * JPAUtil.getEntityManager(); EntityTransaction entityTransaction =
	 * entityManager.getTransaction(); entityTransaction.begin();
	 * 
	 * Object id = jpaUtil.getPrimaryKey(entidade);
	 * entityManager.createNativeQuery("delete from " +
	 * entidade.getClass().getSimpleName().toLowerCase() + " where id = " +
	 * id).executeUpdate();
	 * 
	 * entityTransaction.commit(); }
	 */
	
	
	public List<E> getListEntity(Class<E> entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		entityTransaction.commit();
		
		return retorno;
	}
	
	
	public List<E> getListEntityLimit10(Class<E> entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<E> retorno = entityManager.createQuery("from " + entidade.getName() + " order by id desc ")
				.setMaxResults(10).getResultList();
		
		entityTransaction.commit();
		
		return retorno;
	}
	
	public E consultar(Class<E> entidade, String codigo){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		E objeto = (E) entityManager.find(entidade, Long.parseLong(codigo));
		entityTransaction.commit();
		return objeto;
		
	}
	
}
