package br.com.services;

import java.util.List;
import java.util.Optional;

import javax.faces.model.SelectItem;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;
import br.com.exceptions.UnprocessableEntityException;
import br.com.repository.IDaoPessoa;
import br.com.repository.IDaoPessoaImpl;

public class PessoaService {

	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();

	private IDaoPessoa daoPessoa = new IDaoPessoaImpl();

	public void salvar(Pessoa pessoa) {
		try {
			daoGeneric.merge(pessoa);
		} catch (Exception e) {
			throw new UnprocessableEntityException("Erro ao tentar salvar pessoa");
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

	public List<SelectItem> getEstados() {
		return daoPessoa.listaEstados();
	}

}
