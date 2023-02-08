package com.gesoft.food.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nome;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "grupos_permissoes", joinColumns = @JoinColumn(name="grupo_id"),
	                                       inverseJoinColumns = @JoinColumn(name="restaurante_id"))
	private List<Permissao> permissoes;

}
