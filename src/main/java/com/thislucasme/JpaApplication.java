package com.thislucasme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.thislucasme.domain.repository.CustomJpaRepositoryIml;

@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryIml.class)
@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

}
