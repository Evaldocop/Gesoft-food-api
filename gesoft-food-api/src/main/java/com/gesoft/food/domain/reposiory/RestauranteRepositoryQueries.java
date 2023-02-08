package com.gesoft.food.domain.reposiory;

import java.math.BigDecimal;
import java.util.List;

import com.gesoft.food.domain.model.Restaurante;


public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
