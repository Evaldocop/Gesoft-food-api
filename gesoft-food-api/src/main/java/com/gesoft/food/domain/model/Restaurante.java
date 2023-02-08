package com.gesoft.food.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "restaurante")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
	

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name = "nome",nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	/*
	 * No serializer found for class
	 * org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties
	 * discovered to create BeanSerializer (to avoid exception, disable
	 * SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain:
	 * com.gesoft.food.domain.model.Restaurante["cozinha"]->com.gesoft.food.domain.
	 * model.Cozinha$HibernateProxy$kZ7TNW9V["hibernateLazyInitializer"])
	 * 
	 * a estratégia lazy gera essa exception, exemplification aula 6.12 time ~13m
	 * 
	 */
	
	
	//@JsonIgnore
	@JsonIgnoreProperties("hibernateLazyInitializer")///
	@ManyToOne( fetch = FetchType.LAZY) //todo mapeamento terminado em *..toOne* por padrao usa o Eager(ancioso)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition =  "datetime")
	private LocalDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition =  "datetime")
	private LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	@ManyToMany //todo mapeamento terminado em *..toManee* por padrao usa o Lazy
	@JoinTable( name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"),
	                                                  inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamentos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos;
	
}
