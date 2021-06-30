package com.thislucasme.domain.repository;

import java.util.List;

import com.thislucasme.domain.model.Cozinha;
public interface CozinhaRepository {

	List<Cozinha> todos();
	Cozinha porId(Long id);
	Cozinha adcionar(Cozinha cozinha);
	void remover(Cozinha cozinha);
	
}
