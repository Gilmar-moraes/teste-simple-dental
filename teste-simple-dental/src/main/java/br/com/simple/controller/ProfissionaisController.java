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

import br.com.simple.model.Profissional;
import br.com.simple.service.ProfissionalService;

@RestController
@RequestMapping("/profissionais")
public class ProfissionaisController {

	@Autowired
    private ProfissionalService profissionalService;

    @GetMapping
    public List<Profissional> getAllProfissionais() {
        return profissionalService.getAllProfissionais();
    }

    @GetMapping("/{id}")
    public Profissional getProfissionalById(@PathVariable Long id) {
        return profissionalService.getProfissionalById(id);
    }
    
    @PostMapping
    public String saveProfissional(@RequestBody Profissional profissional) {
        profissionalService.saveProfissional(profissional);
        return "Sucesso - profissional com id " + profissional.getId() + " cadastrado.";
    }

    @PutMapping("/{id}")
    public String updateProfissional(@PathVariable Long id, @RequestBody Profissional profissional) {
        profissional.setId(id);
        profissionalService.saveProfissional(profissional);
        return "Sucesso - cadastro alterado.";
    }
    
    @DeleteMapping("/{id}")
    public String deleteProfissional(@PathVariable Long id) {
        profissionalService.deleteProfissional(id);
        return "Sucesso - profissional excluído.";
    }
}
