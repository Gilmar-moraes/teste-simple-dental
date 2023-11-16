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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/contatos")
@Api(tags = "Contatos", description = "Operações relacionadas ao tratamentos dos dados contatos")
public class ContatosController {
	@Autowired
    private ContatoService contatoService;

	@ApiOperation(value = "Obter todos os contatos", response = List.class)
    @GetMapping
    public List<Contato> getAllContatos() {
        return contatoService.getAllContatos();
    }

	@ApiOperation(value = "Obter um contato por ID", response = Contato.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contato encontrado"),
            @ApiResponse(code = 404, message = "Contato não encontrado")
    })
    @GetMapping("/{id}")
    public Contato getContatoById( @ApiParam(value = "ID do contato", required = true) @PathVariable Long id) {
        return contatoService.getContatoById(id);
    }
    
	@ApiOperation(value = "Inserir um novo contato", response = Contato.class)
    @PostMapping
    public String saveContato( @ApiParam(value = "Dados do contato a serem inseridos", required = true) @RequestBody Contato contato) {
        contatoService.saveContato(contato);
        return "Sucesso - contato com id " + contato.getId() + " cadastrado.";
    }

	@ApiOperation(value = "Atualizar um contato por ID", response = Contato.class)
    @PutMapping("/{id}")
    public String updateContato(
    		@ApiParam(value = "ID do contato a ser atualizado", required = true) @PathVariable Long id, 
    		@ApiParam(value = "Novos dados do contato", required = true) @RequestBody Contato contato) {
        contato.setId(id);
        contatoService.saveContato(contato);
        return "Sucesso - cadastro alterado.";
    }
    
	@ApiOperation(value = "Excluir um contato por ID")
    @DeleteMapping("/{id}")
    public String deleteContato(@ApiParam(value = "ID do contato a ser excluído", required = true) @PathVariable Long id) {
        contatoService.deleteContato(id);
        return "Sucesso - contato excluído.";
    }
}
