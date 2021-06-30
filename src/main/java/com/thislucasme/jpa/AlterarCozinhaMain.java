package com.thislucasme.jpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.repository.CozinhaRepository;
import com.thislucasme.infrastructure.repository.CozinhaRepositoryImpl;

public class AlterarCozinhaMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cadastroConzinha = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Mexicana");
		cozinha.setId(1L);
		
		
		cadastroConzinha.adcionar(cozinha);

	}

}
