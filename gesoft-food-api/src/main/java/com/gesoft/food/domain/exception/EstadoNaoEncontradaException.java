package com.gesoft.food.domain.exception;

import com.gesoft.food.Constants.ConstantesGesoft;


public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException /*ResponseStatusException */{
	private static final long serialVersionUID = 1L;
	
	private static String NAME_CLASS_ESTADO=" ESTADO ";
	
	/*
	 * public EntidadeNaoEncontradaException(HttpStatus status,String message) {
	 * super(status,message); }
	 * 
	 * public EntidadeNaoEncontradaException(String message) {
	 * super(HttpStatus.CONFLICT,message); }
	 */
	public EstadoNaoEncontradaException(String message) {
		super(message);
	}
	
	public EstadoNaoEncontradaException(Long classId) {
		this(String.format(ConstantesGesoft.ENTIDADE_NAO_ENCONTRADA, NAME_CLASS_ESTADO, classId));
	}
	
}
