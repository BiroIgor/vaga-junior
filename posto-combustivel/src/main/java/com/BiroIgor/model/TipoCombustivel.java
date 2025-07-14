package com.biroigor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tipos_combustivel")
@Schema(description = "Tipo de combustível disponível no posto")
public class TipoCombustivel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do tipo de combustível", example = "1")
    private Long id;
    
    @NotBlank(message = "Nome do combustível é obrigatório")
    @Column(nullable = false, unique = true)
    @Schema(description = "Nome do tipo de combustível", example = "Gasolina Comum")
    private String nome;
    
    @Positive(message = "Preço por litro deve ser positivo")
    @Column(nullable = false)
    @Schema(description = "Preço por litro do combustível", example = "5.50")
    private double precoPorLitro;

    // Construtor vazio necessário para o JPA
    public TipoCombustivel() {}

    public TipoCombustivel(String nome, double precoPorLitro) {
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
        return nome + " (R$ " + String.format("%.2f", precoPorLitro) + "/L)";
    }
}