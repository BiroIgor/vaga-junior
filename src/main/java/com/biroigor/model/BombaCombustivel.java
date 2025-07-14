package com.biroigor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "bombas_combustivel")
@Schema(description = "Bomba de combustível do posto")
public class BombaCombustivel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da bomba", example = "1")
    private Long id;
    
    @NotBlank(message = "Nome da bomba é obrigatório")
    @Column(nullable = false)
    @Schema(description = "Nome identificador da bomba", example = "Bomba 1")
    private String nome;
    
    @NotNull(message = "Tipo de combustível é obrigatório")
    @ManyToOne
    @JoinColumn(name = "tipo_combustivel_id", nullable = false)
    @Schema(description = "Tipo de combustível que a bomba fornece")
    private TipoCombustivel tipoCombustivel;

    // Construtor vazio necessário para o JPA
    public BombaCombustivel() {}

    public BombaCombustivel(String nome, TipoCombustivel tipoCombustivel) {
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
        return nome + " - " + (tipoCombustivel != null ? tipoCombustivel.getNome() : "Sem combustível");
    }
}
