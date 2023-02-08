package com.gesoft.food.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gesoft.food.domain.model.Estado;
import com.gesoft.food.domain.service.EstadoService;

//GET /cozinhas HTTP 1.1

//@Controller
//@ResponseBody ///responde a requisicao http
@RestController /// posssui o controller e response body
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	// por padrao retorna json, mas retorna tbm xml. o op
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Estado> listar() {
		return estadoService.listar();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Estado adicionar(@RequestBody Estado estado) {
		estado = estadoService.salvarAtualizar(estado);
		return estado;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{estadoId}")
	public Estado alterar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		Estado estadoAtual = estadoService.buscarPorId(estadoId);
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoService.salvarAtualizar(estadoAtual);

	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("estadoId") Long estadoId) {
		estadoService.excluir(estadoId);

	}

}
