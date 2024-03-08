package converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Estados;
import br.com.jpautil.JPAUtil;

@FacesConverter(forClass = Estados.class, value = "estadoConverter")
public class EstadoConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Override /* retorna objeto inteiro*/
	public Object getAsObject(FacesContext context, UIComponent component, String codigoEstado) {
		// agora consulta no banco e retorna o objeto
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();		
		
		Estados estado = entityManager.find(Estados.class, Long.parseLong(codigoEstado));
				
		return estado;
	}

	@Override /* retorna apenas o codigo em string */
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		if (estado == null) {
			return null;
		} else if (estado instanceof Estados) {
			return ((Estados) estado).getId().toString();
		} 
		
		return estado.toString();
	}

}
