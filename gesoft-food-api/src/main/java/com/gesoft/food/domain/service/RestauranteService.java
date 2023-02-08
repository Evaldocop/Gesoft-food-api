package com.gesoft.food.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gesoft.food.Constants.ConstantesGesoft;
import com.gesoft.food.domain.exception.EntidadeEmUsoException;
import com.gesoft.food.domain.exception.RestauranteNaoEncontradaException;
import com.gesoft.food.domain.model.Cozinha;
import com.gesoft.food.domain.model.Restaurante;
import com.gesoft.food.domain.reposiory.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CozinhaService cozinhaService;

	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	public List<Restaurante> findByTaxaBetween(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	public List<Restaurante> findByNomeContainsAndCozinhaId(String nome, Long cozinhaId) {
		return restauranteRepository.findByNomeContainsAndCozinhaId(nome, cozinhaId);
	}

	public List<Restaurante> consultarLikeAndCozinhaId(String nome, Long cozinhaId) {
		return restauranteRepository.findByNomeContainsAndCozinhaId(nome, cozinhaId);
	}

	public Restaurante salvarAtualizar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarPorId(cozinhaId);

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	public Restaurante buscarPorId(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradaException( restauranteId));
	}

	public Optional<Restaurante> findFirstRestauranteByNomeContains(String nome) {
		return restauranteRepository.findFirstRestauranteByNomeContains(nome);
	}

	public List<Restaurante> findTop2ByByNomeContains(String nome) {
		return restauranteRepository.findTop2ByNomeContains(nome);
	}

	public int countByCozinhaId(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}

	public boolean existsByNome(String nome) {
		return restauranteRepository.existsByNome(nome);
	}

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);

	}

	public void excluir(Long restauranteId) {
		try {
			// Restaurante RestauranteBD = RestauranteRepository.buscarPorId(RestauranteId);

			restauranteRepository.deleteById(restauranteId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String
					.format(ConstantesGesoft.ENTIDADE_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO, "Restaurante", restauranteId));
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradaException(restauranteId);
		}
	}
}
