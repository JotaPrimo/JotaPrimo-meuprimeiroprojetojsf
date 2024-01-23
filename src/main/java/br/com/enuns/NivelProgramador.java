package br.com.enuns;

import java.util.ArrayList;
import java.util.List;

public enum NivelProgramador {
	
	JUNIOR("Junior","JR"), PLENO("Pleno", "PL"), SENIOR("Senior", "Sr");
	
	private String descricao;
	private String valor;
	
	private NivelProgramador(String descricao, String valor) {
		this.descricao = descricao;
		this.valor = valor;
	}
	
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getValor() {
		return valor;
	}	
	
	
}
