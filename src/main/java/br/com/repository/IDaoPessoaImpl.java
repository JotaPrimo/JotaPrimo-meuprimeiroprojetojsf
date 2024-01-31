package br.com.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import br.com.entidades.Estados;
import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;

@Named
public class IDaoPessoaImpl implements IDaoPessoa, Serializable {
	
	private static final long serialVersionUID = 1L;		
	
	@Override
	public Pessoa consultarUsuario(String login, String senha) {
		Pessoa pessoa = null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try {
			pessoa = (Pessoa) entityManager
					.createQuery("SELECT p FROM Pessoa p WHERE p.login = :login AND senha = :senha")
					.setParameter("login", login)
					.setParameter("senha", senha)
					.getSingleResult();
		} catch (NoResultException e) {
			
		}
		
		entityTransaction.commit();
		entityManager.close();
		
		return pessoa;
	}

	@Override
	public List<SelectItem> listaEstados() {
		
		List<SelectItem> listSelectItems = new ArrayList<SelectItem>();
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<Estados> estados = entityManager.createQuery(" from Estados ").getResultList();

		entityTransaction.commit();
		entityManager.close();
		
		for (Estados estado : estados) {
			listSelectItems.add(new SelectItem(estado.getId(), estado.getNome()));
		}
		
		return listSelectItems;
	}

	

}
