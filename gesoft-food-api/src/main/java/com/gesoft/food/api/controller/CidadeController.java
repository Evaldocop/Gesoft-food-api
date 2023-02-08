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

import com.gesoft.food.domain.exception.EntidadeNaoEncontradaException;
import com.gesoft.food.domain.exception.NegocioException;
import com.gesoft.food.domain.model.Cidade;
import com.gesoft.food.domain.service.CidadeService;

//GET /cozinhas HTTP 1.1

//@Controller
//@ResponseBody ///responde a requisicao http
@RestController /// posssui o controller e response body
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	// por padrao retorna json, mas retorna tbm xml. o op
	@GetMapping("/{cidadeId}")
	public Cidade buscarPorId(@PathVariable("cidadeId") Long cidadeId) {
		return cidadeService.buscarPorId(cidadeId);
	}

	// por padrao retorna json, mas retorna tbm xml. o op
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cidade> listar() {
		return cidadeService.listar();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Cidade adicionar(@RequestBody Cidade cidade) {

		try {
			cidade = cidadeService.salvarAtualizar(cidade);
			return cidade;

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}


	@PutMapping(value = "/{cidadeId}")
	public Cidade alterar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		
		//trata 404
		Cidade cidadeAtual = cidadeService.buscarPorId(cidadeId);
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		
		try {
			//estado nao existe 
			return cidadeService.salvarAtualizar(cidadeAtual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("cidadeId") Long cidadeId) {
		cidadeService.excluir(cidadeId);

	}
	
	
}
