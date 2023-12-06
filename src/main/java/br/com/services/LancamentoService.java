package br.com.services;

import java.util.List;

import br.com.dao.DaoGeneric;
import br.com.entidades.Lancamento;
import br.com.repository.IDaoLancamentoImpl;

public class LancamentoService {
	private DaoGeneric<Lancamento> daoGeneric = new DaoGeneric<Lancamento>();
	private IDaoLancamentoImpl daoLancamentoImpl = new IDaoLancamentoImpl();
	
	public void salvar(Lancamento lancamento) {
		daoGeneric.salvar(lancamento);
	}
	
	public void deletar(Lancamento lancamento) {
		daoGeneric.deletePorId(lancamento);
	}
	
	public List<Lancamento> carregarLancamento(Long id) {		
		return daoLancamentoImpl.consultar(id);
	}
	
	public List<Lancamento> getListEntityLimit10(Class<Lancamento> class1) {		
		return daoGeneric.getListEntityLimit10(class1);
	}
	
}
