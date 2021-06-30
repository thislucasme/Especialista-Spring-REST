package com.thislucasme.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.repository.CozinhaRepository;
import com.thislucasme.infrastructure.repository.CozinhaRepositoryImpl;

public class BuscaCozinhaMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		Cozinha cozinha = cozinhas.porId(3L);
		
		System.out.println(cozinha.getNome());

	}

}
