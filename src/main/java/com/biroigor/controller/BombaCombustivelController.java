package com.biroigor.controller;

import com.biroigor.model.BombaCombustivel;
import com.biroigor.model.TipoCombustivel;
import com.biroigor.service.BombaCombustivelService;
import com.biroigor.service.TipoCombustivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bombas-combustivel")
public class BombaCombustivelController {

    @Autowired
    private BombaCombustivelService bombaService;
    
    @Autowired
    private TipoCombustivelService tipoService;

    @GetMapping
    public List<BombaCombustivel> listar() {
        return bombaService.listar();
    }

    @PostMapping
    public BombaCombustivel criar(@RequestParam("nome") String nome, @RequestParam("tipoCombustivelId") Long tipoCombustivelId) {
        TipoCombustivel tipo = tipoService.buscarPorId(tipoCombustivelId).orElse(null);
        if (tipo == null) return null;
        return bombaService.criar(nome, tipo);
    }

    @PutMapping("/{id}")
    public BombaCombustivel atualizar(@PathVariable("id") Long id, @RequestParam("nome") String nome, @RequestParam("tipoCombustivelId") Long tipoCombustivelId) {
        TipoCombustivel tipo = tipoService.buscarPorId(tipoCombustivelId).orElse(null);
        if (tipo == null) return null;
        bombaService.atualizar(id, nome, tipo);
        return bombaService.buscarPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id) {
        bombaService.deletar(id);
    }
}
