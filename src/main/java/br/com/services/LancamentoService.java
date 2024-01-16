package br.com.services;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.dao.DaoGeneric;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;
import br.com.repository.IDaoLancamentoImpl;

public class LancamentoService {
	private DaoGeneric<Lancamento> daoGeneric = new DaoGeneric<Lancamento>();
	private IDaoLancamentoImpl daoLancamentoImpl = new IDaoLancamentoImpl();
	
	public void salvar(Lancamento lancamento) {
		daoGeneric.merge(lancamento);
	}
	
	public void deletar(Lancamento lancamento) {
		daoGeneric.deletePorId(lancamento);
	}
	
	public List<Lancamento> carregarLancamento() {		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		return daoLancamentoImpl.consultar(pessoaUser.getId());
	}
	
	public List<Lancamento> getListEntityLimit10(Class<Lancamento> class1) {		
		return daoGeneric.getListEntityLimit10(class1);
	}
	
}
