package com.thislucasme.api.controller;

import java.beans.Beans;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.BeanDefinitionDsl.BeanSupplierContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thislucasme.domain.exception.EntidadeNaoEncontradaException;
import com.thislucasme.domain.model.Restaurante;
import com.thislucasme.domain.repository.RestauranteRepository;
import com.thislucasme.domain.service.CadastroRestauranteService;

import lombok.val;

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();	
	
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);

		if (restaurante.isPresent()) {
			return ResponseEntity.ok().body(restaurante.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> adcionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestauranteService.salvar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		
		restaurante.setId(id);
		
		try {
			Restaurante restauranteAtual = restauranteRepository.findById(id).orElse(null);
			
			if(restauranteAtual != null) {
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formaPagamento", "endereco", "dataCadastro");
		
				restaurante = cadastroRestauranteService.atualizar(restauranteAtual);
				return ResponseEntity.status(HttpStatus.OK).body(restauranteAtual);
			}
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id,
			@RequestBody Map<String, Object> campos){
		
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
		
		if(!restauranteAtual.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		
		merge(campos, restauranteAtual.get());
 
		
		return atualizar(id, restauranteAtual.get());
	}

	private void merge(Map<String, Object> campos, Restaurante restaurante) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(campos, Restaurante.class);
		
		campos.forEach((nomePropiedade, valorPropiedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropiedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			System.out.println(nomePropiedade + " = " + novoValor);
			
			ReflectionUtils.setField(field, restaurante, novoValor);
		});
	}

}
