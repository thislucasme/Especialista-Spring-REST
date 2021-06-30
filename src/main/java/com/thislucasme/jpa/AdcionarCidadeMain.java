package com.thislucasme.jpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cidade;
import com.thislucasme.domain.model.Estado;
import com.thislucasme.infrastructure.repository.CidadeRepositoryImpl;


public class AdcionarCidadeMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepositoryImpl cidades = applicationContext.getBean(CidadeRepositoryImpl.class);
		
		Cidade cidade1 = new Cidade();
		cidade1.setNome("Santos");
		Estado estado = new Estado();
		estado.setId(2L);
		cidade1.setEstado(estado);
		
		cidades.adcionar(cidade1);

	}

}
