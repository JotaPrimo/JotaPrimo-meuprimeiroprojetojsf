package br.com.cursojsf;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import br.com.dao.DaoGeneric;
import br.com.entidades.Cidades;
import br.com.entidades.Estados;
import br.com.entidades.Pessoa;
import br.com.repository.IDaoPessoa;
import br.com.repository.IDaoPessoaImpl;
import br.com.services.CidadeService;
import br.com.services.MessageService;
import br.com.services.PessoaService;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean implements Serializable {

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	private static final String URL_VIA_CEP = "https://viacep.com.br/ws/";

	private IDaoPessoa iDaoPessoa = new IDaoPessoaImpl();
	
	private List<SelectItem> estados;
	
	private List<SelectItem> cidades;
	
	private PessoaService service = new PessoaService();
	
	private Part arquivofoto;

	public String salvar() {
		System.out.println(arquivofoto);
		pessoa = daoGeneric.merge(pessoa);
		carregarPessoas();
		mostrarMsg("Cadastrado com sucesso!");
		return "";
	}

	public void pesquisaCep(AjaxBehaviorEvent event) {
		try {
			System.out.println("Chamada do metodo de busca de cep : " + pessoa.getCep());
			URL url = new URL(URL_VIA_CEP + pessoa.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			StringBuilder jsonCep = setarRetornoBufferEmString(bufferedReader, new StringBuilder());

			Pessoa gsonAux = new Gson().fromJson(jsonCep.toString(), Pessoa.class);
			
			System.out.println("===========================");
			System.out.println("WEB SERVICE BORA APRENDER");
			System.out.println(gsonAux.toString());
			System.out.println(jsonCep.toString());
			System.out.println("WEB SERVICE BORA APRENDER");

			pessoa.setCep(gsonAux.getCep());
			pessoa.setLogradouro(gsonAux.getLogradouro());
			pessoa.setComplemento(gsonAux.getComplemento());
			pessoa.setBairro(gsonAux.getBairro());
			pessoa.setLocalidade(gsonAux.getLocalidade());
			pessoa.setUf(gsonAux.getUf());
			pessoa.setIbge(gsonAux.getIbge());	
			
		} catch (Exception e) {
			mostrarMsg("Ocorreu um erro");
		}

	}
	
	public StringBuilder setarRetornoBufferEmString(BufferedReader bufferedReader, StringBuilder jsonCep)
			throws IOException {
		String cep = "";
		while ((cep = bufferedReader.readLine()) != null) {
			jsonCep.append(cep);
		}
		System.out.println("Retorno : " + cep);

		return jsonCep;
	}

	public void registraLog() {
		System.out.println("método registraLog");
		/* Criar a rotina de gravação de log */
	}

	private void mostrarMsg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);

	}

	public String novo() {
		pessoa = new Pessoa();
		return "";
	}

	public String remove() {
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
		mostrarMsg("Removido com sucesso!");
		return "";
	}

	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.getListEntity(Pessoa.class);		
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public List<SelectItem> getEstados() {
		return service.getEstados();
	}
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		
		if (pessoaUser != null) {
			externalContext.getSessionMap().remove("usuarioLogado");
			
			HttpServletRequest httpServletRequest = (HttpServletRequest) 
					context.getCurrentInstance()
					.getExternalContext()
					.getRequest();
			
			httpServletRequest.getSession().invalidate();
			return "index.jsf";
		}
		
		MessageService.mostrarMessage("Ocorreu um erro");
		return "index.jsf";
		
	}

	public String logar() {

		Pessoa pessoaUser = iDaoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());

		if (pessoaUser != null) {// achou o usuário

			// adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser);

			return "primeirapagina.jsf";
		}

		return "index.jsf";
	}

	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");

		return pessoaUser.getPerfilUser().equals(acesso);
	}

	public void mudancaDeValor(ValueChangeEvent evento) {
		System.out.println("Valor antigo: " + evento.getOldValue());
		System.out.println("Valor Novo: " + evento.getNewValue());
	}

	public void mudancaDeValorSobrenome(ValueChangeEvent evento) {
		System.out.println("Valor antigo: " + evento.getOldValue());
		System.out.println("Valor Novo: " + evento.getNewValue());
	}
	
	public List<SelectItem> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}
	
	public Part getArquivofoto() {
		return arquivofoto;
	}
	
	public void setArquivofoto(Part arquivofoto) {
		this.arquivofoto = arquivofoto;
	}

	public void carregaCidades(AjaxBehaviorEvent event) {
		Estados estado = (Estados) ((HtmlSelectOneMenu) event.getSource()).getValue();
		
		if (estado != null) {
			pessoa.setEstado(estado);
			
			@SuppressWarnings("unchecked")
			List<Cidades> cidades =  CidadeService.getListaDeCidadesPeloEstadoId(estado.getId());
			List<SelectItem> selectItemsCidades = CidadeService.retornaSelectItemDeCidades(cidades);
			setCidades(selectItemsCidades);
		}			
	}	
	
	private byte[] getByte(InputStream is) throws IOException {
		int len;
		int size = 1024;
		byte[] buf = null;
		
		if(is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			
			while((len = is.read(buf, 0, size)) != -1) {
				bos.write(buf, 0, size);
			}
			
			buf = bos.toByteArray();
		}		
		
		return buf;
	}
}
