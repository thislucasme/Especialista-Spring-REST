package com.thislucasme.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.model.Permissao;
import com.thislucasme.infrastructure.repository.CozinhaRepositoryImpl;
import com.thislucasme.infrastructure.repository.PermissaoRepositoryImpl;

public class ConsultaPermissaoMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepositoryImpl permissoes = applicationContext.getBean(PermissaoRepositoryImpl.class);
		
		List<Permissao> todasPermissoes = permissoes.todos();
		
		for(Permissao permissao: todasPermissoes) {
			System.out.println(permissao.getNome());
		}

	}

}
