package com.thislucasme.domain.repository;

import java.util.List;

import com.thislucasme.domain.model.Cidade;
import com.thislucasme.domain.model.Estado;
public interface EstadoRepository {

	List<Estado> todos();
	Estado porId(Long id);
	Estado adcionar(Estado estado);
	void remover(Estado estado);
	
}
