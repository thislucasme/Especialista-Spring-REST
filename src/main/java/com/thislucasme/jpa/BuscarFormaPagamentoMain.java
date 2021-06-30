package com.thislucasme.jpa;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.thislucasme.JpaApplication;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.model.FormaPagamaento;
import com.thislucasme.domain.repository.CozinhaRepository;
import com.thislucasme.domain.repository.FormaPagamentoRepository;
import com.thislucasme.infrastructure.repository.CozinhaRepositoryImpl;
import com.thislucasme.infrastructure.repository.FormaPagamentoRepositoryImp;

public class BuscarFormaPagamentoMain {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(JpaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository pagamentos = applicationContext.getBean(FormaPagamentoRepositoryImp.class);
		
		FormaPagamaento formaPagamaento = new FormaPagamaento();
		formaPagamaento.setDescricao("Cartao crédito");
		
		pagamentos.adcionar(formaPagamaento);

	}

}
