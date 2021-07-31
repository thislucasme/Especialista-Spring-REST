package com.thislucasme.domain.service;

import java.util.Optional;

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
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		
		if(!cidade.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d nao pode ser encontrado", id));
		}
		return cidade.get();
	}
	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	public Cidade atualizar(Cidade cidade) {
		Optional<Estado> estado = estadoRepository.findById(cidade.getEstado().getId());
		Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidade.getId());
		
		if(!cidadeAtual.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d nao existe", cidade.getId()));
		}else if(!estado.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Estado com id %d nao existe", cidade.getEstado().getId()));
		}
		BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
		return cidadeRepository.save(cidadeAtual.get());
	}
	
	public void delete(Long id) {
		Optional<Cidade> cidade =  cidadeRepository.findById(id);
		if(!cidade.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d nao existe", id));
		}
		try {
			cidadeRepository.delete(cidade.get());
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cozinha com id %d nao pode ser deletada, pois está em uso", id));
		}

	}
}
