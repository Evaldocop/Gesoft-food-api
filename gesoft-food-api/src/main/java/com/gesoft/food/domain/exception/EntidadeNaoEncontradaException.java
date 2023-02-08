package com.gesoft.food.domain.exception;

import com.gesoft.food.Constants.ConstantesGesoft;


public abstract class EntidadeNaoEncontradaException extends NegocioException /*ResponseStatusException */{
	private static final long serialVersionUID = 1L;
	
	/*
	 * public EntidadeNaoEncontradaException(HttpStatus status,String message) {
	 * super(status,message); }
	 * 
	 * public EntidadeNaoEncontradaException(String message) {
	 * super(HttpStatus.CONFLICT,message); }
	 */
	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
	
	
	public EntidadeNaoEncontradaException(String entityName, Long classId) {
		this(String.format(ConstantesGesoft.ENTIDADE_NAO_ENCONTRADA, entityName, classId));
	}
	
}
