package br.com.simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.simple.model.Contato;
import br.com.simple.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/contatos")
@Tag(name = "Contatos Operações relacionadas ao tratamentos dos dados contatos")
public class ContatosController {
	@Autowired
    private ContatoService contatoService;

	@Operation(summary = "Obter todos os contatos")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contatos encontrados"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contatos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @GetMapping
    public List<Contato> getAllContatos() {
        return contatoService.getAllContatos();
    }

	@Operation(summary = "Obter um contato por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @GetMapping("/{id}")
    public Contato getContatoById(@Param(value = "ID do contato") @PathVariable Long id) {
        return contatoService.getContatoById(id);
    }
    
	@Operation(summary = "Obter um contato por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @GetMapping("/{nome}")
    public Contato getContatoByNome(@Param(value = "nome do contato") @PathVariable String nome) {
        return contatoService.getContatoByNome(nome);
    }
	
	@Operation(summary = "Inserir um novo contato")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato salvo"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contato não salvo"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveContato(@Param(value = "Dados do contato a serem inseridos") @RequestBody Contato contato) {
        contatoService.saveContato(contato);
        return "Sucesso - contato com id " + contato.getId() + " cadastrado.";
    }

	@Operation(summary = "Atualizar um contato por ID")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato atualizado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contato não atualizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
    public String updateContato(
    		@Param(value = "ID do contato a ser atualizado") @PathVariable Long id, 
    		@Param(value = "Novos dados do contato") @RequestBody Contato contato) {
		contato.setId(id);
        contatoService.saveContato(contato);
        return "Sucesso - cadastro alterado.";
    }
    
	@Operation(summary = "Excluir um contato por ID")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato deletado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contato não deletado"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @DeleteMapping("/{id}")
    public String deleteContato(@Param(value = "ID do contato a ser excluído") @PathVariable Long id) {
        contatoService.deleteContato(id);
        return "Sucesso - contato excluído.";
    }
}
