package com.biroigor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Entity
@Table(name = "abastecimentos")
@Schema(description = "Registro de abastecimento realizado")
public class Abastecimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do abastecimento", example = "1")
    private Long id;
    
    @NotNull(message = "Bomba é obrigatória")
    @ManyToOne
    @JoinColumn(name = "bomba_id", nullable = false)
    @Schema(description = "Bomba utilizada no abastecimento")
    private BombaCombustivel bomba;
    
    @NotNull(message = "Data é obrigatória")
    @Column(nullable = false)
    @Schema(description = "Data e hora do abastecimento")
    private LocalDateTime dataHora;
    
    @Positive(message = "Valor total deve ser positivo")
    @Column(nullable = false)
    @Schema(description = "Valor total pago pelo abastecimento", example = "55.00")
    private double valorTotal;
    
    @Positive(message = "Litragem deve ser positiva")
    @Column(nullable = false)
    @Schema(description = "Quantidade de litros abastecidos", example = "10.5")
    private double litragem;

    // Construtor vazio necessário para o JPA
    public Abastecimento() {}

    public Abastecimento(BombaCombustivel bomba, LocalDateTime dataHora, double valorTotal, double litragem) {
        this.bomba = bomba;
        this.dataHora = dataHora;
        this.valorTotal = valorTotal;
        this.litragem = litragem;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BombaCombustivel getBomba() { return bomba; }
    public void setBomba(BombaCombustivel bomba) { this.bomba = bomba; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public double getLitragem() { return litragem; }
    public void setLitragem(double litragem) { this.litragem = litragem; }

    @Override
    public String toString() {
        return "Abastecimento em " + dataHora + " | Bomba: " + (bomba != null ? bomba.getNome() : "N/A") + 
               " | Litros: " + String.format("%.2f", litragem) + " | Valor: R$ " + String.format("%.2f", valorTotal);
    }
}
