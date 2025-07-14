package com.biroigor.model;

public class BombaCombustivel {
    private Long id;
    private String nome;
    private TipoCombustivel tipoCombustivel;

    public BombaCombustivel(Long id, String nome, TipoCombustivel tipoCombustivel) {
        this.id = id;
        this.nome = nome;
        this.tipoCombustivel = tipoCombustivel;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoCombustivel getTipoCombustivel() { return tipoCombustivel; }
    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) { this.tipoCombustivel = tipoCombustivel; }

    @Override
    public String toString() {
        return nome + " - " + tipoCombustivel.getNome();
    }
}
