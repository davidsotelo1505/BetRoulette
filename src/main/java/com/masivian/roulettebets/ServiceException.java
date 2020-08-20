package com.masivian.roulettebets;

import java.io.Serializable;


public class ServiceException extends Exception implements Serializable {

	/** * */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}

	/**
	 * Informacion del error
	 */
	private ApiError apiError;
	
	/**
	 * Constructor
	 * 
	 * @param text
	 * @param e
	 */
	public ServiceException(String text, Exception e) {
		super(text, e);
	}

	/**
	 * Constructor
	 * 
	 * @param text
	 */
	public ServiceException(String text) {
		super(text);
	}

	/**
	 * Constructor
	 * 
	 * @param text
	 */
	public ServiceException(ApiError apiError) {
		super(apiError.getMessageUser());
		this.apiError = apiError;
	}
	
	public ServiceException(ApiError apiError, String message) {
		super(message);
		this.apiError = apiError;
	}

	public ApiError getApiError() {
		return apiError;
	}

}