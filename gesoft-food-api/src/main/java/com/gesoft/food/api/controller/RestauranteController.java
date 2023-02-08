package com.gesoft.food.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gesoft.food.domain.exception.EntidadeNaoEncontradaException;
import com.gesoft.food.domain.exception.NegocioException;
import com.gesoft.food.domain.model.Restaurante;
import com.gesoft.food.domain.service.RestauranteService;

//GET /Restaurantes HTTP 1.1

//@Controller
//@ResponseBody ///responde a requisicao http
@RestController /// posssui o controller e response body
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private List<Restaurante> listar() {
		return restauranteService.listar();
	}
	
	
	@GetMapping("/restaurantesPorTaxa")
	public List<Restaurante> findByTaxaBetween(@RequestParam ( value ="taxaInicial" ) BigDecimal taxaInicial,@RequestParam (value = "taxaFinal") BigDecimal taxaFinal){
		return restauranteService.findByTaxaBetween(taxaInicial, taxaFinal);
	}
	
	
	@GetMapping("/restaurantesPorNomeTaxa")
	public List<Restaurante> restaurantesPorNomeFrete(String nome ,BigDecimal taxaInicial,BigDecimal taxaFinal){
		return restauranteService.find(nome,taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantesNomeCozinhaId")
	public List<Restaurante> findByTaxaBetween(@RequestParam ( value ="nome" ) String nome,@RequestParam (value = "cozinhaId") Long cozinhaId){
		return restauranteService.consultarLikeAndCozinhaId(nome, cozinhaId);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{restauranteId}")
	private Restaurante buscar(@PathVariable("restauranteId") Long restauranteId) {
		return restauranteService.buscarPorId(restauranteId);
		
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private Restaurante adicionar(@RequestBody Restaurante restaurante) {

		try {
			restaurante = restauranteService.salvarAtualizar(restaurante);
			return restaurante;

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	private Restaurante alterar(@PathVariable("restauranteId") Long restauranteId,
			@RequestBody Restaurante restaurante) {
		Restaurante restauranteAtual = restauranteService.buscarPorId(restauranteId);
		
		// param >3 inabilita a mudança
		 BeanUtils.copyProperties(restaurante, restauranteAtual, 
		            "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
		
		try {
			return restauranteService.salvarAtualizar(restauranteAtual);

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}
	/*
	 * @ResponseStatus(value = HttpStatus.OK)
	 * 
	 * @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value =
	 * "/{RestauranteId}") private ResponseEntity<Restaurante>
	 * atualizar(@PathVariable("RestauranteId") Long RestauranteId,
	 * 
	 * @RequestBody Restaurante Restaurante) { Restaurante RestauranteAtual =
	 * restauranteRepozitory.buscarPorId(RestauranteId);
	 * 
	 * if (RestauranteAtual != null) { // param >3 inabilita a mudança
	 * BeanUtils.copyProperties(Restaurante, RestauranteAtual, "id");
	 * restauranteService.salvarAtualizar(RestauranteAtual); return
	 * ResponseEntity.ok(RestauranteAtual); } else return
	 * ResponseEntity.notFound().build();
	 * 
	 * }
	 */

	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void remover(@PathVariable("restauranteId") Long RestauranteId) {
				restauranteService.excluir(RestauranteId);
	}
	
	@GetMapping("/primeiroRestaurantePorNome")
	public ResponseEntity<Restaurante> findFirstRestauranteByNomeContains(@RequestParam ( value ="nome" ) String nome){
		return ResponseEntity.ok(restauranteService.findFirstRestauranteByNomeContains(nome).get()) ;
	}
	
	@GetMapping("/doisPrimeiroRestaurantePorNome")
	public List<Restaurante> findTop2ByByNomeContains(@RequestParam ( value ="nome" ) String nome){
		return restauranteService.findTop2ByByNomeContains(nome);
	}
	
	@GetMapping("/countRestauranteCozinhaId")
	public ResponseEntity<?> countByCozinhaId(@RequestParam ( value ="cozinhaId" ) Long cozinhaId){
		return ResponseEntity.status(HttpStatus.OK).body(restauranteService.countByCozinhaId(cozinhaId)) ;
	}
	@GetMapping("/existRestaurantePorNome")
	public ResponseEntity<?> existsByNome(@RequestParam ( value ="nome" ) String nome){
		return ResponseEntity.status(HttpStatus.OK).body(restauranteService.existsByNome(nome)) ;
	}



}
