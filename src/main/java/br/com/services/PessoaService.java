package br.com.services;

import java.util.List;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;

public class PessoaService {
	
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	
	public void salvar(Pessoa pessoa) {
		daoGeneric.salvar(pessoa);
	}
	
	public void delete(Pessoa pessoa) {
		daoGeneric.deletePorId(pessoa);
	}

	public List<Pessoa> getListEntityLimit10(Class<Pessoa> class1) {		
		return daoGeneric.getListEntityLimit10(class1);
	}
	
}
