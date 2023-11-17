package br.com.simple.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.simple.model.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
	
	//List<Profissional> findByDataNascimento(String dataNascimento);
	
	Optional<Profissional> findByNomeContaining(String nomeCargo);
}