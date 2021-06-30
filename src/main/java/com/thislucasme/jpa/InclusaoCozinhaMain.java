package com.thislucasme.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.infrastructure.repository.CozinhaRepositoryImpl;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepositoryImpl cozinhas = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Mexicana");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Libanesa");
		
		cozinhas.adcionar(cozinha1);
		cozinhas.adcionar(cozinha2);

	}

}
