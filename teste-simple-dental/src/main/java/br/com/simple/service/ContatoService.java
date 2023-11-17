package br.com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.simple.exception.NegocioException;
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
        return contatoRepository.findById(id)
        		.orElseThrow(() -> new NegocioException("Contato não encontrado"));
    }
    
    public Contato getContatoByNome(String nome) {
        return contatoRepository.findByNomeContaining(nome)
        		.orElseThrow(() -> new NegocioException("Nome não encontrado"));
    }

    @Transactional
    public Contato saveContato(Contato contato) {
    	boolean contatoEmUso = contatoRepository.findByContato(contato.getContato())
    			.stream()
    			.anyMatch(contatoExiste -> !contatoExiste.equals(contato));
    	if (contatoEmUso) {
			throw new NegocioException("Contato já em uso");
		}
        return contatoRepository.save(contato);
    }
    
    @Transactional
    public void deleteContato(Long id) {
        contatoRepository.deleteById(id);
    }
}
