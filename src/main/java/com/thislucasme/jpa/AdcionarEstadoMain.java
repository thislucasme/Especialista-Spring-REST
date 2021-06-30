package com.thislucasme.jpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cidade;
import com.thislucasme.domain.model.Estado;
import com.thislucasme.infrastructure.repository.CidadeRepositoryImpl;
import com.thislucasme.infrastructure.repository.EstadoRepositoryImpl;

public class AdcionarEstadoMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepositoryImpl estados = applicationContext.getBean(EstadoRepositoryImpl.class);
		
		
		Estado estado = new  Estado();
		estado.setNome("Sao Paulo");
		
		Estado estado1 = new  Estado();
		estado1.setNome("Minas Gerais");
		
		estados.adcionar(estado1);
		estados.adcionar(estado);
	

	}

}
