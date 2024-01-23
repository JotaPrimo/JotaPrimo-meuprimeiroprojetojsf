package br.com.services;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageService {
	private void mostrarMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(message);
		context.addMessage(null, facesMessage);
		}
}
