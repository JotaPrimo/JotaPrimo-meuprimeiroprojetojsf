package converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.entidades.Cidades;
import br.com.jpautil.JPAUtil;

@FacesConverter(forClass = Cidades.class)
public class CidadeConverter implements Converter, Serializable {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		return entityManager.find(Cidades.class, Long.parseLong(codigoCidade));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {
		// TODO Auto-generated method stub
		return ((Cidades) cidade).getId().toString();
	}

}
