package br.com.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.entidades.Cidades;
import br.com.jpautil.JPAUtil;

public class CidadeService {
	@SuppressWarnings("unchecked")
	public static List<Cidades> getListaDeCidadesPeloEstadoId(Long estadoId) {
		return JPAUtil
				.getEntityManager()
				.createQuery("from Cidades where estados.id = :estado_id")
				.setParameter("estado_id", estadoId)
				.getResultList();
	}
	
	public static List<SelectItem> retornaSelectItemDeCidades(List<Cidades> listaCidades) {
		
		List<SelectItem> selectItemsCidades = new ArrayList<SelectItem>();
		
		for (Cidades cidade : listaCidades) {
			selectItemsCidades.add(new SelectItem(cidade.getId(), cidade.getNome()));			
		}
		
		return selectItemsCidades;
	}
}
