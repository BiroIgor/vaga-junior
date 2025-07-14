package com.biroigor.controller;

import com.biroigor.model.Abastecimento;
import com.biroigor.model.BombaCombustivel;
import com.biroigor.service.AbastecimentoService;
import com.biroigor.service.BombaCombustivelService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/abastecimentos")
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService = new AbastecimentoService();
    private final BombaCombustivelService bombaService = new BombaCombustivelService();

    @GetMapping
    public List<Abastecimento> listar() {
        return abastecimentoService.listar();
    }

    @PostMapping
    public Abastecimento criar(
            @RequestParam Long bombaId,
            @RequestParam String data, // formato: "2025-07-14"
            @RequestParam double valorTotal,
            @RequestParam double litragem
    ) {
        BombaCombustivel bomba = bombaService.buscarPorId(bombaId).orElse(null);
        if (bomba == null) return null;
        LocalDate dataAbastecimento = LocalDate.parse(data);
        return abastecimentoService.criar(bomba, dataAbastecimento, valorTotal, litragem);
    }

    @PutMapping("/{id}")
    public Abastecimento atualizar(
            @PathVariable Long id,
            @RequestParam Long bombaId,
            @RequestParam String data,
            @RequestParam double valorTotal,
            @RequestParam double litragem
    ) {
        BombaCombustivel bomba = bombaService.buscarPorId(bombaId).orElse(null);
        if (bomba == null) return null;
        LocalDate dataAbastecimento = LocalDate.parse(data);
        abastecimentoService.atualizar(id, bomba, dataAbastecimento, valorTotal, litragem);
        return abastecimentoService.buscarPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        abastecimentoService.deletar(id);
    }
}
