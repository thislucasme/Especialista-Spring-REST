package com.thislucasme.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.infrastructure.repository.CozinhaRepositoryImpl;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepositoryImpl cozinhas = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		List<Cozinha> todasCozinhas = cozinhas.todos();
		
		for(Cozinha cozinha: todasCozinhas) {
			System.out.println(cozinha.getNome());
		}

	}

}
