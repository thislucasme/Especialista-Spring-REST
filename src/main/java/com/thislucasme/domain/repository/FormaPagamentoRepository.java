package com.thislucasme.domain.repository;

import java.util.List;
import com.thislucasme.domain.model.FormaPagamaento;

public interface FormaPagamentoRepository {
	
	List<FormaPagamaento> todos();
	FormaPagamaento porId(Long id);
	FormaPagamaento adcionar(FormaPagamaento cozinha);
	void remover(FormaPagamaento formaPagamaento);
	
}
