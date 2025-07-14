package com.biroigor.controller;

import com.biroigor.model.TipoCombustivel;
import com.biroigor.service.TipoCombustivelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-combustivel")
@Tag(name = "Tipos de Combustível", description = "Operações relacionadas aos tipos de combustível")
public class TipoCombustivelController {

    @Autowired
    private TipoCombustivelService service;

    @GetMapping
    @Operation(summary = "Listar todos os tipos de combustível", description = "Retorna uma lista com todos os tipos de combustível cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de tipos de combustível retornada com sucesso")
    public ResponseEntity<List<TipoCombustivel>> listar() {
        List<TipoCombustivel> tipos = service.listar();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo de combustível por ID", description = "Retorna um tipo de combustível específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de combustível encontrado"),
        @ApiResponse(responseCode = "404", description = "Tipo de combustível não encontrado")
    })
    public ResponseEntity<TipoCombustivel> buscarPorId(
            @Parameter(description = "ID do tipo de combustível", example = "1")
            @PathVariable Long id) {
        return service.buscarPorId(id)
                .map(tipo -> ResponseEntity.ok(tipo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo tipo de combustível", description = "Cria um novo tipo de combustível")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tipo de combustível criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "409", description = "Tipo de combustível já existe")
    })
    public ResponseEntity<TipoCombustivel> criar(@Valid @RequestBody TipoCombustivel tipo) {
        TipoCombustivel novoTipo = service.criar(tipo.getNome(), tipo.getPrecoPorLitro());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTipo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tipo de combustível", description = "Atualiza os dados de um tipo de combustível existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de combustível atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Tipo de combustível não encontrado"),
        @ApiResponse(responseCode = "409", description = "Nome já existe para outro tipo")
    })
    public ResponseEntity<TipoCombustivel> atualizar(
            @Parameter(description = "ID do tipo de combustível", example = "1")
            @PathVariable Long id, 
            @Valid @RequestBody TipoCombustivel tipo) {
        TipoCombustivel tipoAtualizado = service.atualizar(id, tipo.getNome(), tipo.getPrecoPorLitro());
        return ResponseEntity.ok(tipoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tipo de combustível", description = "Remove um tipo de combustível do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tipo de combustível deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tipo de combustível não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do tipo de combustível", example = "1")
            @PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
