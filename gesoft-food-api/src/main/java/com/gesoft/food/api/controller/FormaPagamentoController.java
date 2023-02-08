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

import com.gesoft.food.domain.model.FormaPagamento;
import com.gesoft.food.domain.service.FormaPagamentoService;

//GET /formaPagamentos HTTP 1.1

//@Controller
//@ResponseBody ///responde a requisicao http
@RestController /// posssui o controller e response body
@RequestMapping("/formapagamentos")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	List<FormaPagamento> listar() {
		return formaPagamentoService.listar();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{formaPagamentoId}")
	private FormaPagamento buscar(@PathVariable("formaPagamentoId") Long formaPagamentoId) {
		FormaPagamento formaPagamento = formaPagamentoService.buscarPorId(formaPagamentoId);
		return formaPagamento;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento) {
		return formaPagamentoService.save(formaPagamento);

	}

	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{formaPagamentoId}")
	private FormaPagamento atualizar(@PathVariable("formaPagamentoId") Long formaPagamentoId,
			@RequestBody FormaPagamento formaPagamento) {
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarPorId(formaPagamentoId);

		// param >3 inabilita a mudança
		BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
		FormaPagamento formaPagamentoSalva = formaPagamentoService.save(formaPagamentoAtual);
		return formaPagamentoSalva;

	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void remover(@PathVariable("formaPagamentoId") Long formaPagamentoId) {
		formaPagamentoService.excluir(formaPagamentoId);
	}

}
