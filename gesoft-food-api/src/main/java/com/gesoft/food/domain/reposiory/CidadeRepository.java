package com.gesoft.food.domain.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesoft.food.domain.model.Cidade;


@Repository
public interface CidadeRepository  extends JpaRepository<Cidade, Long>{	
}
