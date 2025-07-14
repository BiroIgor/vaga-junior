package com.biroigor.controller;

import com.biroigor.model.Abastecimento;
import com.biroigor.model.BombaCombustivel;
import com.biroigor.service.AbastecimentoService;
import com.biroigor.service.BombaCombustivelService;
import com.biroigor.controller.dto.EstatisticasBombaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/abastecimentos")
@Tag(name = "Abastecimentos", description = "Operações relacionadas aos abastecimentos")
public class AbastecimentoController {

    @Autowired
    private AbastecimentoService abastecimentoService;
    
    @Autowired
    private BombaCombustivelService bombaService;

    @GetMapping
    @Operation(summary = "Listar todos os abastecimentos", description = "Retorna uma lista com todos os abastecimentos ordenados por data/hora")
    @ApiResponse(responseCode = "200", description = "Lista de abastecimentos retornada com sucesso")
    public ResponseEntity<List<Abastecimento>> listar() {
        List<Abastecimento> abastecimentos = abastecimentoService.listar();
        return ResponseEntity.ok(abastecimentos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar abastecimento por ID", description = "Retorna um abastecimento específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Abastecimento encontrado"),
        @ApiResponse(responseCode = "404", description = "Abastecimento não encontrado")
    })
    public ResponseEntity<Abastecimento> buscarPorId(
            @Parameter(description = "ID do abastecimento", example = "1")
            @PathVariable("id") Long id) {
        return abastecimentoService.buscarPorId(id)
                .map(abastecimento -> ResponseEntity.ok(abastecimento))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo abastecimento", description = "Registra um novo abastecimento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Abastecimento criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Bomba não encontrada")
    })
    public ResponseEntity<Abastecimento> criar(
            @Parameter(description = "ID da bomba utilizada", example = "1")
            @RequestParam("bombaId") Long bombaId,
            @Parameter(description = "Data e hora do abastecimento (ISO format)", example = "2025-07-14T10:30:00")
            @RequestParam("dataHora") String dataHora,
            @Parameter(description = "Valor total pago", example = "55.00")
            @RequestParam("valorTotal") double valorTotal,
            @Parameter(description = "Quantidade de litros abastecidos", example = "10.5")
            @RequestParam("litragem") double litragem
    ) {
        BombaCombustivel bomba = bombaService.buscarPorId(bombaId)
                .orElse(null);
        if (bomba == null) {
            return ResponseEntity.notFound().build();
        }
        
        LocalDateTime dataHoraAbastecimento = LocalDateTime.parse(dataHora);
        Abastecimento novoAbastecimento = abastecimentoService.criar(bomba, dataHoraAbastecimento, valorTotal, litragem);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAbastecimento);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar abastecimento", description = "Atualiza os dados de um abastecimento existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Abastecimento atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Abastecimento ou bomba não encontrada")
    })
    public ResponseEntity<Abastecimento> atualizar(
            @Parameter(description = "ID do abastecimento", example = "1")
            @PathVariable("id") Long id,
            @Parameter(description = "ID da nova bomba", example = "1")
            @RequestParam("bombaId") Long bombaId,
            @Parameter(description = "Nova data e hora", example = "2025-07-14T10:30:00")
            @RequestParam("dataHora") String dataHora,
            @Parameter(description = "Novo valor total", example = "55.00")
            @RequestParam("valorTotal") double valorTotal,
            @Parameter(description = "Nova quantidade de litros", example = "10.5")
            @RequestParam("litragem") double litragem
    ) {
        BombaCombustivel bomba = bombaService.buscarPorId(bombaId)
                .orElse(null);
        if (bomba == null) {
            return ResponseEntity.notFound().build();
        }
        
        LocalDateTime dataHoraAbastecimento = LocalDateTime.parse(dataHora);
        Abastecimento abastecimentoAtualizado = abastecimentoService.atualizar(id, bomba, dataHoraAbastecimento, valorTotal, litragem);
        return ResponseEntity.ok(abastecimentoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar abastecimento", description = "Remove um abastecimento do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Abastecimento deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Abastecimento não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do abastecimento", example = "1")
            @PathVariable("id") Long id) {
        abastecimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bomba/{bombaId}")
    @Operation(summary = "Listar abastecimentos por bomba", description = "Retorna todos os abastecimentos de uma bomba específica")
    @ApiResponse(responseCode = "200", description = "Lista de abastecimentos da bomba")
    public ResponseEntity<List<Abastecimento>> listarPorBomba(
            @Parameter(description = "ID da bomba", example = "1")
            @PathVariable("bombaId") Long bombaId) {
        BombaCombustivel bomba = bombaService.buscarPorId(bombaId)
                .orElse(null);
        if (bomba == null) {
            return ResponseEntity.notFound().build();
        }
        
        List<Abastecimento> abastecimentos = abastecimentoService.listarPorBomba(bomba);
        return ResponseEntity.ok(abastecimentos);
    }

    @GetMapping("/estatisticas/bomba/{bombaId}")
    @Operation(summary = "Estatísticas por bomba", description = "Retorna estatísticas de uma bomba específica")
    @ApiResponse(responseCode = "200", description = "Estatísticas da bomba")
    public ResponseEntity<EstatisticasBombaResponse> estatisticasPorBomba(
            @Parameter(description = "ID da bomba", example = "1")
            @PathVariable("bombaId") Long bombaId) {
        double[] stats = abastecimentoService.calcularEstatisticasPorBomba(bombaId);
        
        EstatisticasBombaResponse response = new EstatisticasBombaResponse(
            stats[0], stats[1], bombaId
        );
        return ResponseEntity.ok(response);
    }
}
