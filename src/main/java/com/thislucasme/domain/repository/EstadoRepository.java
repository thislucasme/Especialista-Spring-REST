package com.thislucasme.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thislucasme.domain.model.Estado;
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
}
