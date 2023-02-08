package com.gesoft.food.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gesoft.food.Constants.ConstantesGesoft;
import com.gesoft.food.domain.exception.EntidadeEmUsoException;
import com.gesoft.food.domain.exception.PermissaoNaoEncontradaException;
import com.gesoft.food.domain.model.Permissao;
import com.gesoft.food.domain.reposiory.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public Permissao salvarAtualizar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}

	public List<Permissao> listar() {
		// TODO Auto-generated method stub
		return permissaoRepository.findAll();
	}

	public Permissao buscarPorId(Long permissaoId) {
		return permissaoRepository.findById(permissaoId).orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}

	public void excluir(Long permissaoId) {
		try {
			permissaoRepository.deleteById(permissaoId);

		}catch( DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(
				String.format(ConstantesGesoft.ENTIDADE_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO,"Permissao", permissaoId));
		}catch(EmptyResultDataAccessException e){
			throw new PermissaoNaoEncontradaException(permissaoId);
		}
	}

}
