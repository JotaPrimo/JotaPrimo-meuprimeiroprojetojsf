package br.com.enuns;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum Sexo {
	MASCULINO("Masculino", "M"), FEMININO("Feminino", "F");

	private final String descricao;
	private final String valor;

	private Sexo(String descricao, String valor) {
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getValor() {
		return valor;
	}

	public static List<SelectItem> retornaLista() {
		List<SelectItem> opcoes = new ArrayList<SelectItem>();
		opcoes.add(new SelectItem(Sexo.FEMININO.getValor(), Sexo.FEMININO.getDescricao()));
		opcoes.add(new SelectItem(Sexo.MASCULINO.getValor(), Sexo.MASCULINO.getDescricao()));
		
		return opcoes;
	}

}
