package com.thislucasme.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.thislucasme.domain.exception.EntidadeEmUsoException;
import com.thislucasme.domain.exception.EntidadeNaoEncontradaException;
import com.thislucasme.domain.model.Estado;
import com.thislucasme.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado adcionar(Estado estado) {
		return estadoRepository.adcionar(estado);
	}
	public Estado atualizar(Estado estado) {
		Estado estadoAtual = estadoRepository.porId(estado.getId());
		
		if(estadoAtual == null) {
			throw new EntidadeNaoEncontradaException(String.format("Estado com id %d nao existe", estado.getId()));
		}
		BeanUtils.copyProperties(estado, estadoAtual);
		
		return estadoRepository.adcionar(estadoAtual);
	}
	
	public void delete(Long id) {
		Estado estado = estadoRepository.porId(id);
		try {
			if(estado == null) {
				throw new EntidadeNaoEncontradaException(String.format("Estado com id %d nao existe", id));
			}
			estadoRepository.remover(estado);
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado com id %d nao pode ser deletado pois está em uso", id));
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Estado com id %d nao existe", id));
		}
	}

}
