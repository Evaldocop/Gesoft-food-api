package com.gesoft.food.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gesoft.food.Constants.ConstantesGesoft;
import com.gesoft.food.domain.exception.CozinhaNaoEncontradaException;
import com.gesoft.food.domain.exception.EntidadeEmUsoException;
import com.gesoft.food.domain.model.Cozinha;
import com.gesoft.food.domain.reposiory.CozinhaRepository;

@Service
public class CozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}
	
	
	public Cozinha buscarPorId(Long cozinhaId){
		return cozinhaRepository.findById(cozinhaId).orElseThrow(()->new CozinhaNaoEncontradaException(cozinhaId));
	}
	public Optional<Cozinha> buscarPorNome(String nome){
		return cozinhaRepository.findBynome(nome);
	}

	public List<Cozinha> findBynomeContains(String nome){
		return cozinhaRepository.findBynomeContains(nome);
	}
	public Cozinha save(Cozinha cozinha){
		return  cozinhaRepository.save(cozinha);
	}
	
	public Cozinha salvarAtualizar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long cozinhaId) {
		try {
			//Cozinha cozinhaBD = cozinhaRepository.buscarPorId(cozinhaId);
		
				cozinhaRepository.deleteById(cozinhaId);
		} catch (DataIntegrityViolationException e) {
				throw new EntidadeEmUsoException(
						String.format(ConstantesGesoft.ENTIDADE_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO,"Cozinha", cozinhaId));
		}catch(EmptyResultDataAccessException e) {
			throw  new CozinhaNaoEncontradaException( cozinhaId);
		}
	}


}
