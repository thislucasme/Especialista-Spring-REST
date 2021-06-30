package com.thislucasme.jpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.model.Restaurante;
import com.thislucasme.domain.repository.RestauranteRepository;
import com.thislucasme.infrastructure.repository.CozinhaRepositoryImpl;
import com.thislucasme.infrastructure.repository.RestauranteRepositoryImpl;

public class RemoverRestauranteMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setId(1L);
		restaurantes.remover(restaurante);

	}

}
