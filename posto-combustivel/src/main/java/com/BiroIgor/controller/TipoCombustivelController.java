package com.biroigor.controller;

import com.biroigor.model.TipoCombustivel;
import com.biroigor.service.TipoCombustivelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-combustivel")
public class TipoCombustivelController {

    private final TipoCombustivelService service = new TipoCombustivelService();

    @GetMapping
    public List<TipoCombustivel> listar() {
        return service.listar();
    }

    @PostMapping
    public TipoCombustivel criar(@RequestBody TipoCombustivel tipo) {
        return service.criar(tipo.getNome(), tipo.getPrecoPorLitro());
    }

    @PutMapping("/{id}")
    public TipoCombustivel atualizar(@PathVariable Long id, @RequestBody TipoCombustivel tipo) {
        service.atualizar(id, tipo.getNome(), tipo.getPrecoPorLitro());
        return service.buscarPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
