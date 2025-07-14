package com.biroigor.model;

public class TipoCombustivel {
    private Long id;
    private String nome;
    private double precoPorLitro;

    public TipoCombustivel(Long id, String nome, double precoPorLitro) {
        this.id = id;
        this.nome = nome;
        this.precoPorLitro = precoPorLitro;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPrecoPorLitro() { return precoPorLitro; }
    public void setPrecoPorLitro(double precoPorLitro) { this.precoPorLitro = precoPorLitro; }

    @Override
    public String toString() {
        return nome + " (R$ " + precoPorLitro + "/L)";
    }
}
