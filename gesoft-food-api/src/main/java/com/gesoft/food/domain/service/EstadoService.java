package com.gesoft.food.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gesoft.food.Constants.ConstantesGesoft;
import com.gesoft.food.domain.exception.EntidadeEmUsoException;
import com.gesoft.food.domain.exception.EstadoNaoEncontradaException;
import com.gesoft.food.domain.model.Estado;
import com.gesoft.food.domain.reposiory.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvarAtualizar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public List<Estado> listar() {
		// TODO Auto-generated method stub
		return estadoRepository.findAll();
	}

	public Estado buscarPorId(Long estadoId) {
		return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradaException(estadoId));
	}

	public void excluir(Long estadoId) {
		try { // Cozinha cozinhaBD =estadoRepository.buscarPorId(estadoId);

			estadoRepository.deleteById(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(ConstantesGesoft.ENTIDADE_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO, "Estado", estadoId));
		} catch (EmptyResultDataAccessException e) {
			throw new  EstadoNaoEncontradaException(estadoId);
		}
	}

}
