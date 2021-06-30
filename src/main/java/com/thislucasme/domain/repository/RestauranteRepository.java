package com.thislucasme.domain.repository;

import java.util.List;

import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> todos();
	Restaurante porId(Long id);
	Restaurante adcionar(Restaurante restaurante);
	void remover(Restaurante cozinha);
	
}
