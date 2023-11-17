package br.com.simple.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.simple.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	List<Contato> findByContato(String contato);
	
	Optional<Contato> findByNomeContaining(String nome);
}