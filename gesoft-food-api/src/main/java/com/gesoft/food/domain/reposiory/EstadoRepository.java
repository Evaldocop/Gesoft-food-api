package com.gesoft.food.domain.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gesoft.food.domain.model.Estado;


@Repository
public interface EstadoRepository  extends JpaRepository<Estado, Long> {

}
