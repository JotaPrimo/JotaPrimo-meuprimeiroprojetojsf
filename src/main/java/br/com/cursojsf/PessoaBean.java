package br.com.cursojsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;
import br.com.services.PessoaService;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private PessoaService pessoaService = new PessoaService();	
	
	
	public String salvar() {
		pessoaService.salvar(pessoa);				
		return "";
	}
	
	public String novo() {
		pessoa = new Pessoa();	
		return "";
	}
	
	public String remover() {	
		pessoaService.delete(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
		return "";
	}
	
	public String editar() {
		return "";
	}
	
	@PostConstruct
	public void carregarPessoas() {
		pessoas = pessoaService.getListEntityLimit10(Pessoa.class);
		
		for (Pessoa p : pessoas) {
			System.out.println(p);			
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}	

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	

}
