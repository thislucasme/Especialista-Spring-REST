package com.thislucasme.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.thislucasme.domain.exception.EntidadeEmUsoException;
import com.thislucasme.domain.exception.EntidadeNaoEncontradaException;
import com.thislucasme.domain.model.Cidade;
import com.thislucasme.domain.model.Estado;
import com.thislucasme.domain.repository.CidadeRepository;
import com.thislucasme.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade buscar(Long id) {
		Cidade cidade = cidadeRepository.porId(id);
		
		if(cidade == null) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d nao pode ser encontrado", id));
		}
		return cidade;
	}
	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.adcionar(cidade);
	}
	public Cidade atualizar(Cidade cidade) {
		Estado estado = estadoRepository.porId(cidade.getEstado().getId());
		Cidade cidadeAtual = cidadeRepository.porId(cidade.getId());
		if(cidadeAtual == null) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d nao existe", cidade.getId()));
		}else if(estado == null) {
			throw new EntidadeNaoEncontradaException(String.format("Estado com id %d nao existe", cidade.getEstado().getId()));
		}
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		return cidadeRepository.adcionar(cidadeAtual);
	}
	
	public void delete(Long id) {
		Cidade cidade =  cidadeRepository.porId(id);
		if(cidade == null) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d nao existe", id));
		}
		try {
			cidadeRepository.remover(cidade);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cozinha com id %d nao pode ser deletada, pois está em uso", id));
		}

	}
}
