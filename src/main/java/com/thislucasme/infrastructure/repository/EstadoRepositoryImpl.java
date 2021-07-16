package com.thislucasme.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thislucasme.domain.model.Cidade;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.model.Estado;
import com.thislucasme.domain.repository.CidadeRepository;
import com.thislucasme.domain.repository.CozinhaRepository;
import com.thislucasme.domain.repository.EstadoRepository;


@Component
public class EstadoRepositoryImpl implements EstadoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> todos(){
		TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
		return query.getResultList();
	}
	
	@Override
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}
	
	@Transactional
	@Override
	public Estado adcionar(Estado estado) {
		return manager.merge(estado);
	}
	
	@Transactional
	@Override
	public void remover(Estado estado) {
		manager.remove(estado);
	}
}
