package br.com.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Lancamento;
import br.com.jpautil.JPAUtil;

public class IDaoLancamentoImpl implements IDaoLancamento {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> consultar(Long idUser) {
		List<Lancamento> lancamentos = null;
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		lancamentos = entityManager.createQuery(" FROM Lancamento l WHERE usuario_id = :idUser")
				.setParameter("idUser", idUser)
				.getResultList();
		
		entityTransaction.commit();
		
		return lancamentos;
	}

	@Override
	public List<Lancamento> consultarLimit10(Long codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lancamento> relatorioLancamento(String numNome, Date dataIni, Date dataFim) {
		// TODO Auto-generated method stub
		return null;
	}

}
