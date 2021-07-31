package com.thislucasme.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.thislucasme.domain.exception.EntidadeEmUsoException;
import com.thislucasme.domain.exception.EntidadeNaoEncontradaException;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public void excluir(Long id) {
		
		try {
			cozinhaRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException exception) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe uma cozinha com o código %d", id));
		}
		catch (DataIntegrityViolationException exception) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código: %d nao pode ser removida, pois está em uso", id));
		}
	}

}
