package com.gesoft.food.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gesoft.food.core.security.GesoftSecurity;
import com.gesoft.food.domain.model.Cozinha;
import com.gesoft.food.domain.service.CozinhaService;

import lombok.extern.slf4j.Slf4j;

//GET /cozinhas HTTP 1.1
@Slf4j
//@Controller
//@ResponseBody ///responde a requisicao http
@RestController /// posssui o controller e response body
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaService cozinhaService;
	
	/*
	 * @Autowired private GesoftSecurity gesoftSecurity;
	 */
	
	// por PERMISSAO
	//@PreAuthorize("hasAuthority('CONSULTAR_COZINHAS')")
	@PreAuthorize("isAuthenticated() and hasAuthority('SCOPE_WRITE')")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	List<Cozinha> listar() {
	 ///pegando as permissoes e os scopes	
	 ///System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
	//	log.info("O id do usuário logado é: " +gesoftSecurity.getUsuarioId().toString());
		return cozinhaService.listar();
	}
	

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/listaPorLikeNome/{nome}")
	List<Cozinha> listarPorLikeNome(@PathVariable("nome") String nome) {
		return cozinhaService.findBynomeContains(nome);
	}


	/*
	 * @ResponseStatus(value = HttpStatus.OK)
	 * 
	 * @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value =
	 * "/{cozinhaId}") private ResponseEntity<Cozinha>
	 * buscar(@PathVariable("cozinhaId") Long cozinhaId) { Optional<Cozinha> cozinha
	 * = cozinhaService.buscarPorId(cozinhaId); if (cozinha.isPresent()) return
	 * ResponseEntity.ok(cozinha.get()); else return
	 * ResponseEntity.notFound().build(); }
	 */
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{cozinhaId}")
	private Cozinha buscar(@PathVariable("cozinhaId") Long cozinhaId) {
		return  cozinhaService.buscarPorId(cozinhaId);
	
			
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/buscarPorNome/{nome}")
	private ResponseEntity<Cozinha> buscarPorNome(@PathVariable("nome") String nome) {
		Optional<Cozinha> cozinha = cozinhaService.buscarPorNome(nome);
		if (cozinha.isPresent())
			return ResponseEntity.ok(cozinha.get());
		else
			return ResponseEntity.notFound().build();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	private Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cozinhaService.save(cozinha);

	}

	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{cozinhaId}")
	private Cozinha atualizar(@PathVariable("cozinhaId") Long cozinhaId, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cozinhaService.buscarPorId(cozinhaId);	
			// param >3 inabilita a mudança
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			Cozinha cozinhaSalva=cozinhaService.save(cozinhaAtual);
			return cozinhaSalva;
		

	}

	/*
	 * @DeleteMapping("/{cozinhaId}") private ResponseEntity<?>
	 * remover(@PathVariable("cozinhaId") Long cozinhaId) { try {
	 * cozinhaService.excluir(cozinhaId); return ResponseEntity.noContent().build();
	 * }catch(EntidadeEmUsoException e) { return
	 * ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); }catch
	 * (EntidadeNaoEncontradaException e) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); }
	 * 
	 * }
	 */
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void  remover(@PathVariable("cozinhaId") Long cozinhaId) {
		   cozinhaService.excluir(cozinhaId);	  		
	}

}
