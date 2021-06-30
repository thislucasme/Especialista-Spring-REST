package com.thislucasme.domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Component
public class Permissao {
	
	private String nome;
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;

}
