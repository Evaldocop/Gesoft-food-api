package com.gesoft.food.domain.reposiory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gesoft.food.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> , RestauranteRepositoryQueries{
	
/** query methods - gera o sql por meio analise morfologica e sintatica**/	
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial,BigDecimal taxaFinal);
	
	List<Restaurante> findByNomeContainsAndCozinhaId(String nome,Long cozinhaId);
	
	
  //retorna o primeiro de um LIKE
	Optional <Restaurante> findFirstRestauranteByNomeContains(String nome);
	
	  //retorna o  os dois primeiros de um LIKE com limite definido Top2
	List<Restaurante> findTop2ByNomeContains(String nome);
	//count(restaurante.cozinha.id)
	int countByCozinhaId(Long cozinhaId);
	
	boolean existsByNome(String nome);
	
	/** query methods - gera o sql, analise morfologica e sintatica. 
	 * ESSA QUERY FOI EXTERNALIZADA PARA O META-INF.orm.xml**/	
//	@Query("from Restaurante r where nome like %:nome% and r.cozinha.id = :cozinhaId")
	List<Restaurante> consultarLikeAndCozinhaId(String nome,@Param("cozinhaId") Long cozinhaId);	
}
