package br.com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.simple.exception.NegocioException;
import br.com.simple.model.Profissional;
import br.com.simple.repository.ProfissionalRepository;

@Service
public class ProfissionalService {

	@Autowired
    private ProfissionalRepository profissionalRepository;

    public List<Profissional> getAllProfissionais() {
        return profissionalRepository.findAll();
    }

    public Profissional getProfissionalById(Long id) {
        return profissionalRepository.findById(id)
        		.orElseThrow(() -> new NegocioException("Profissional não encontrado"));
    }
    
    public Profissional getProfissionalByCargo(String nomeCargo) {
        return profissionalRepository.findByNomeContaining(nomeCargo).orElseThrow(() -> new NegocioException("Cargos não encontrado"));
    }

    @Transactional
    public Profissional saveProfissional(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    @Transactional
    public void deleteProfissional(Long id) {
        profissionalRepository.deleteById(id);
    }
}
