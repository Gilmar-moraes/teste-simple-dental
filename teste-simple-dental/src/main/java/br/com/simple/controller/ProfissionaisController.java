package br.com.simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.simple.model.Profissional;
import br.com.simple.service.ProfissionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/profissionais")
@Tag(name = "Operações relacionadas ao tratamentos dos dados dos profissionais")
public class ProfissionaisController {

	@Autowired
    private ProfissionalService profissionalService;

	@Operation(summary = "Obter todos os profissionais")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profissionais encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contatos não profissionais"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @GetMapping
    public List<Profissional> getAllProfissionais() {
        return profissionalService.getAllProfissionais();
    }

	@Operation(summary = "Obter um profissinal por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profissional encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "profissional não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @GetMapping("/{id}")
    public Profissional getProfissionalById(@PathVariable Long id) {
        return profissionalService.getProfissionalById(id);
    }
    
	@Operation(summary = "Obter um profissinal por cargo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profissional encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "profissional não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
	@GetMapping("/{nomeCargo}")
    public Profissional getProfissionalByNomeCargo(@PathVariable String nomeCargo) {
        return profissionalService.getProfissionalByCargo(nomeCargo);
    }
	
	@Operation(summary = "Inserir um novo profissional")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profissional salvo"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "profissional não salvo"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveProfissional(@RequestBody Profissional profissional) {
        profissionalService.saveProfissional(profissional);
        return "Sucesso - profissional com id " + profissional.getId() + " cadastrado.";
    }

	@Operation(summary = "Atualizar um profissional por ID")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profissional atualizado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "profissional não atualizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateProfissional(@PathVariable Long id, @RequestBody Profissional profissional) {
        profissional.setId(id);
        profissionalService.saveProfissional(profissional);
        return "Sucesso - cadastro alterado.";
    }
	@Operation(summary = "Excluir um profissional por ID")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profissional deletado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "profissional não deletado"),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @DeleteMapping("/{id}")
    public String deleteProfissional(@PathVariable Long id) {
        profissionalService.deleteProfissional(id);
        return "Sucesso - profissional excluído.";
    }
}
