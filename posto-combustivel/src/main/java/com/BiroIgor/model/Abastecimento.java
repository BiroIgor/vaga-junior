package com.biroigor.model;

import java.time.LocalDate;

public class Abastecimento {
    private Long id;
    private BombaCombustivel bomba;
    private LocalDate data;
    private double valorTotal;
    private double litragem;

    public Abastecimento(Long id, BombaCombustivel bomba, LocalDate data, double valorTotal, double litragem) {
        this.id = id;
        this.bomba = bomba;
        this.data = data;
        this.valorTotal = valorTotal;
        this.litragem = litragem;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BombaCombustivel getBomba() { return bomba; }
    public void setBomba(BombaCombustivel bomba) { this.bomba = bomba; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public double getLitragem() { return litragem; }
    public void setLitragem(double litragem) { this.litragem = litragem; }

    @Override
    public String toString() {
        return "Abastecimento em " + data + " | Bomba: " + bomba.getNome() + " | Litros: " + litragem + " | Valor: R$ " + valorTotal;
    }
}
