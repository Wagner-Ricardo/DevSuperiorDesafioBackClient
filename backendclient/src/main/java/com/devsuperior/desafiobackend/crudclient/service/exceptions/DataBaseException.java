package com.devsuperior.desafiobackend.crudclient.service.exceptions;

public class DataBaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//a mensagem ser utilizada pela super classe
	public DataBaseException(String msg) {
		super(msg);
	}
	
}
