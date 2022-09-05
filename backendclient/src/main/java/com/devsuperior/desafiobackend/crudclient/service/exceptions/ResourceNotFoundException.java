package com.devsuperior.desafiobackend.crudclient.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//a mensagem ser utilizada pela super classe
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
}
