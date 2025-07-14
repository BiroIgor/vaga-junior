package com.biroigor.controller;

import com.biroigor.model.BombaCombustivel;
import com.biroigor.model.TipoCombustivel;
import com.biroigor.service.BombaCombustivelService;
import com.biroigor.service.TipoCombustivelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bombas")
public class BombaCombustivelController {

    private final BombaCombustivelService bombaService = new BombaCombustivelService();
    private final TipoCombustivelService tipoService = new TipoCombustivelService();

    @GetMapping
    public List<BombaCombustivel> listar() {
        return bombaService.listar();
    }

    @PostMapping
    public BombaCombustivel criar(@RequestParam String nome, @RequestParam Long tipoCombustivelId) {
        TipoCombustivel tipo = tipoService.buscarPorId(tipoCombustivelId).orElse(null);
        if (tipo == null) return null;
        return bombaService.criar(nome, tipo);
    }

    @PutMapping("/{id}")
    public BombaCombustivel atualizar(@PathVariable Long id, @RequestParam String nome, @RequestParam Long tipoCombustivelId) {
        TipoCombustivel tipo = tipoService.buscarPorId(tipoCombustivelId).orElse(null);
        if (tipo == null) return null;
        bombaService.atualizar(id, nome, tipo);
        return bombaService.buscarPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        bombaService.deletar(id);
    }
}
