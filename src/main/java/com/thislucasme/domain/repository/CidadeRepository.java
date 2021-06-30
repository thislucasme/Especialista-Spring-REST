package com.thislucasme.domain.repository;

import java.util.List;

import com.thislucasme.domain.model.Cidade;
public interface CidadeRepository {

	List<Cidade> todos();
	Cidade porId(Long id);
	Cidade adcionar(Cidade cozinha);
	void remover(Cidade cozinha);
	
}
