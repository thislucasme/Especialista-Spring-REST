package com.thislucasme.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thislucasme.domain.model.FormaPagamaento;
import com.thislucasme.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoRepositoryImp implements FormaPagamentoRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FormaPagamaento> todos() {
		TypedQuery<FormaPagamaento> query = manager.createQuery("from FormaPagamaento", FormaPagamaento.class);
		return query.getResultList();
	}

	@Override
	public FormaPagamaento porId(Long id) {
		return manager.find(FormaPagamaento.class, id);
	}

	@Override
	@Transactional
	public FormaPagamaento adcionar(FormaPagamaento formaPagamento) {
		return manager.merge(formaPagamento);
	}

	@Override
	public void remover(FormaPagamaento formaPagamaento) {
		manager.remove(formaPagamaento);
	}
}
