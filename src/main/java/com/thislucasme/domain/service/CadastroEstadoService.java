package com.thislucasme.domain.service;

import java.util.Optional;

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
		return estadoRepository.save(estado);
	}
	public Estado atualizar(Estado estado) {
		Optional<Estado> estadoAtual = estadoRepository.findById(estado.getId());
		
		if(!estadoAtual.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Estado com id %d nao existe", estado.getId()));
		}
		BeanUtils.copyProperties(estado, estadoAtual.get());
		
		return estadoRepository.save(estadoAtual.get());
	}
	
	public void delete(Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		try {
			if(!estado.isPresent()) {
				throw new EntidadeNaoEncontradaException(String.format("Estado com id %d nao existe", id));
			}
			estadoRepository.delete(estado.get());
			
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado com id %d nao pode ser deletado pois está em uso", id));
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Estado com id %d nao existe", id));
		}
	}

}
