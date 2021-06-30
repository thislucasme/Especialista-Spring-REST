package com.thislucasme.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thislucasme.domain.model.Cidade;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.repository.CidadeRepository;
import com.thislucasme.domain.repository.CozinhaRepository;


@Component
public class CidadeRepositoryImpl implements CidadeRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> todos(){
		TypedQuery<Cidade> query = manager.createQuery("from Cidade", Cidade.class);
		return query.getResultList();
	}
	
	@Override
	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	@Transactional
	@Override
	public Cidade adcionar(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	@Transactional
	@Override
	public void remover(Cidade cidade) {
		cidade  = porId(cidade.getId());
		manager.remove(cidade);
	}
}
