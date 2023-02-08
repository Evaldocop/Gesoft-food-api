package com.gesoft.food.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gesoft.food.Constants.ConstantesGesoft;
import com.gesoft.food.domain.exception.EntidadeEmUsoException;
import com.gesoft.food.domain.exception.FormaPagamentoNaoEncontradaException;
import com.gesoft.food.domain.model.FormaPagamento;
import com.gesoft.food.domain.reposiory.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	public List<FormaPagamento> listar() {
		return formaPagamentoRepository.findAll();
	}

	public FormaPagamento buscarPorId(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId).orElseThrow(()->new FormaPagamentoNaoEncontradaException(formaPagamentoId));
	}

	public FormaPagamento save(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}

	public FormaPagamento salvarAtualizar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}

	public void excluir(Long formaPagamentoId) {
		try {
			// FormaPagamento formaPagamentoBD =
			// formaPagamentoRepository.buscarPorId(formaPagamentoId);

			formaPagamentoRepository.deleteById(formaPagamentoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(ConstantesGesoft.ENTIDADE_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO, "Forma Pagamento", formaPagamentoId));
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(formaPagamentoId);
		}
	}

}
