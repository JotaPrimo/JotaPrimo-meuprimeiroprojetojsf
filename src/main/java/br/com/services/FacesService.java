package br.com.services;

import javax.faces.context.FacesContext;

public class FacesService {
	public static void getFacesContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		
	}
}
