package com.thislucasme.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.model.Restaurante;
import com.thislucasme.domain.repository.CozinhaRepository;
import com.thislucasme.domain.repository.RestauranteRepository;


@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> todos(){
		TypedQuery<Restaurante> query = manager.createQuery("from Restaurante", Restaurante.class);
		return query.getResultList();
	}
	
	@Override
	public Restaurante porId(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Transactional
	@Override
	public Restaurante adcionar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
		restaurante  = porId(restaurante.getId());
		manager.remove(restaurante);
	}
}
