package com.thislucasme.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.model.Restaurante;
import com.thislucasme.domain.repository.RestauranteRepository;
import com.thislucasme.infrastructure.repository.CozinhaRepositoryImpl;
import com.thislucasme.infrastructure.repository.RestauranteRepositoryImpl;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		List<Restaurante> todosRestaurantes = restaurantes.todos();
		
		for(Restaurante restaurante: todosRestaurantes) {
			System.out.printf("%s - %f - %s\n", restaurante.getNome(), restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
		}

	}

}
