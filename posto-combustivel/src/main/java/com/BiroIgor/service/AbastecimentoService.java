package com.biroigor.service;

import com.biroigor.model.Abastecimento;
import com.biroigor.model.BombaCombustivel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbastecimentoService {
    private List<Abastecimento> abastecimentos = new ArrayList<>();
    private long proximoId = 1;

    public Abastecimento criar(BombaCombustivel bomba, LocalDate data, double valorTotal, double litragem) {
        Abastecimento abastecimento = new Abastecimento(proximoId++, bomba, data, valorTotal, litragem);
        abastecimentos.add(abastecimento);
        return abastecimento;
    }

    public List<Abastecimento> listar() {
        return new ArrayList<>(abastecimentos);
    }

    public Optional<Abastecimento> buscarPorId(Long id) {
        return abastecimentos.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public boolean atualizar(Long id, BombaCombustivel bomba, LocalDate data, double valorTotal, double litragem) {
        Optional<Abastecimento> opt = buscarPorId(id);
        if (opt.isPresent()) {
            Abastecimento abastecimento = opt.get();
            abastecimento.setBomba(bomba);
            abastecimento.setData(data);
            abastecimento.setValorTotal(valorTotal);
            abastecimento.setLitragem(litragem);
            return true;
        }
        return false;
    }

    public boolean deletar(Long id) {
        return abastecimentos.removeIf(a -> a.getId().equals(id));
    }
}