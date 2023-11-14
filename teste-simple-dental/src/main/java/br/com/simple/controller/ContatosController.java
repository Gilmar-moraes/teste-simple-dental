package br.com.simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simple.model.Contato;
import br.com.simple.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatosController {
	@Autowired
    private ContatoService contatoService;

    @GetMapping
    public List<Contato> getAllContatos() {
        return contatoService.getAllContatos();
    }

    @GetMapping("/{id}")
    public Contato getContatoById(@PathVariable Long id) {
        return contatoService.getContatoById(id);
    }
    
    @PostMapping
    public String saveContato(@RequestBody Contato contato) {
        contatoService.saveContato(contato);
        return "Sucesso - contato com id " + contato.getId() + " cadastrado.";
    }

    @PutMapping("/{id}")
    public String updateContato(@PathVariable Long id, @RequestBody Contato contato) {
        contato.setId(id);
        contatoService.saveContato(contato);
        return "Sucesso - cadastro alterado.";
    }
    
    @DeleteMapping("/{id}")
    public String deleteContato(@PathVariable Long id) {
        contatoService.deleteContato(id);
        return "Sucesso - contato excluído.";
    }
}
