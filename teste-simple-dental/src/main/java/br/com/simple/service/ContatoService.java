package br.com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.simple.model.Contato;
import br.com.simple.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
    private ContatoRepository contatoRepository;

    public List<Contato> getAllContatos() {
        return contatoRepository.findAll();
    }
    
    public Contato getContatoById(Long id) {
        return contatoRepository.findById(id).orElse(null);
    }

    public Contato saveContato(Contato contato) {
        return contatoRepository.save(contato);
    }
    
    public void deleteContato(Long id) {
        contatoRepository.deleteById(id);
    }
}
