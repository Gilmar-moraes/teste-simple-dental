package br.com.simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.simple.model.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}