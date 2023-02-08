package com.gesoft.food.domain.exception;

public class NegocioException extends RuntimeException /*ResponseStatusException */{
	private static final long serialVersionUID = 1L;
	
	/*
	 * public EntidadeNaoEncontradaException(HttpStatus status,String message) {
	 * super(status,message); }
	 * 
	 * public EntidadeNaoEncontradaException(String message) {
	 * super(HttpStatus.CONFLICT,message); }
	 */
	public NegocioException(String message) {
		super(message);
	}
	
	public NegocioException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
