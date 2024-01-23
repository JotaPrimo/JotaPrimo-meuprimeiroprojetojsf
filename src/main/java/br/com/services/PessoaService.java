package br.com.services;

import java.util.List;
import java.util.Optional;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;
import br.com.exceptions.UnprocessableEntityException;
import br.com.repository.IDaoPessoa;
import br.com.repository.IDaoPessoaImpl;

public class PessoaService {	
	
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();	
	
	private IDaoPessoa daoPessoa = new IDaoPessoaImpl();
	
	
	public void salvar(Pessoa pessoa) {
		System.out.println("=============================");
		System.out.println(pessoa);
		System.out.println("=============================");
		
		Optional<Pessoa> retorno = Optional.of(daoGeneric.merge(pessoa));
		
		System.out.println(retorno);
		
		if(retorno.isPresent()) {
			System.out.println("salvou");
		}else {
			throw new UnprocessableEntityException("Erro ao tentar salvar pessoa no banco");
		}
	}
	
	public void delete(Pessoa pessoa) {
		daoGeneric.deletePorId(pessoa);
	}

	public List<Pessoa> getListEntityLimit10(Class<Pessoa> class1) {		
		return daoGeneric.getListEntityLimit10(class1);
	}

	public Pessoa consultarUsuario(String login, String senha) {		
		return daoPessoa.consultarUsuario(login, senha);
	}
	
}
