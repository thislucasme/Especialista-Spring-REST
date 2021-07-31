package com.thislucasme.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thislucasme.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
}
