package com.thislucasme.domain.repository;

import java.util.List;
import com.thislucasme.domain.model.FormaPagamaento;
import com.thislucasme.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> todos();
	Permissao porId(Long id);
	Permissao adcionar(Permissao permissao);
	void remover(Permissao permissao);
	
}
