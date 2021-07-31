package com.thislucasme.domain.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomJpaRepositoryIml<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID>{

	private EntityManager manager;
	
	public CustomJpaRepositoryIml(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.manager = em;
	}
	


	@Override
	public Optional<T> buscarPrimeiro() {
		var jpql = "from "+getDomainClass().getName();
		T entity = manager.createQuery(jpql, getDomainClass())
		.setMaxResults(1)
		.getSingleResult();
		return Optional.ofNullable(entity);
	}
}
