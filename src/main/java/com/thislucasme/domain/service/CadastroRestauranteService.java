package com.thislucasme.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thislucasme.domain.exception.EntidadeNaoEncontradaException;
import com.thislucasme.domain.model.Cozinha;
import com.thislucasme.domain.model.Restaurante;
import com.thislucasme.domain.repository.CozinhaRepository;
import com.thislucasme.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.porId(cozinhaId);
		
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de cozinha com id  %d",cozinhaId));
		}
			restaurante.setCozinha(cozinha);
		
		return restauranteRepository.adcionar(restaurante);
	}
	
	public Restaurante atualizar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.porId(cozinhaId);
		
		Restaurante restauranteAtual = restauranteRepository.porId(restaurante.getId());
		

		if(restauranteAtual == null) {
			throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de restaurante com id  %d",restaurante.getId()));	
		}
		
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de cozinha com id  %d",cozinhaId));
		}
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
		return restauranteRepository.adcionar(restaurante);
	}
	
}
