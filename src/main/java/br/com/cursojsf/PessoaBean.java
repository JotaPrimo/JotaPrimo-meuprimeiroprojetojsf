package br.com.cursojsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.entidades.Pessoa;
import br.com.enuns.Sexo;
import br.com.services.PessoaService;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
	private PessoaService pessoaService = new PessoaService();

	public String salvar() {
		System.out.println("Passei aqui PessoaBean -> salvar ");
		pessoaService.salvar(pessoa);
		mostrarMsg("Cadastrado com sucesso");
		return "";
	}

	private void mostrarMsg(String string) {
				
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
	
	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get(
				"usuarioLogado");	
		

		return pessoaUser.getPerfilUser().equals(acesso);
	}

	public String logar() {

		Pessoa pessoaUser = pessoaService.consultarUsuario(this.pessoa.getLogin(), this.pessoa.getSenha());

		if (pessoaUser != null) {// achou o usuário

			// adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();

			HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
			HttpSession session = req.getSession();

			session.setAttribute("usuarioLogado", pessoaUser);

			return "primeirapagina.jsf";
		} else {
			FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Usuário não encontrado"));

		}

		return "index.jsf";
	}

	@PostConstruct
	public void carregarPessoas() {
		pessoas = pessoaService.getListEntityLimit10(Pessoa.class);

		for (Pessoa p : pessoas) {
			System.out.println(p);
		}
	}
	
	public List<SelectItem> retornaOpcoesSexo() {
		return Sexo.retornaLista();
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
