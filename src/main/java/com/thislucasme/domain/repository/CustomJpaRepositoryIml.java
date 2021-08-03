package com.thislucasme.domain.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomJpaRepositoryIml<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID>{

	private EntityManager manager;
	
	public CustomJpaRepositoryIml(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		String jpql = "from "+getDomainClass().getName();
		T entity = manager.createQuery(jpql, getDomainClass())
		.setMaxResults(1)
		.getSingleResult();
		return Optional.ofNullable(entity);
	}
}
