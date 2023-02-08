package com.gesoft.food.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
public class Usuario {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email",nullable = false, unique = true)
	private String email;
	
	@Column(name = "senha",nullable = false)
	private String senha;
	
	@CreationTimestamp
	@Column(name = "data_cadastro",nullable = false, columnDefinition =  "datetime")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="usuarios_grupos",joinColumns = @JoinColumn(name = "usuario_id"),
	                                  inverseJoinColumns = @JoinColumn(name="grupo_id"))
	private List<Grupo> grupos;
	
}
