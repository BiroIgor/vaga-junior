package com.biroigor.controller.dto;

/**
 * DTO para resposta de estatísticas por bomba
 */
public class EstatisticasBombaResponse {
    
    private double totalLitros;
    private double valorTotal;
    private Long bombaId;
    
    public EstatisticasBombaResponse(double totalLitros, double valorTotal, Long bombaId) {
        this.totalLitros = totalLitros;
        this.valorTotal = valorTotal;
        this.bombaId = bombaId;
    }
    
    // Getters e Setters
    public double getTotalLitros() { return totalLitros; }
    public void setTotalLitros(double totalLitros) { this.totalLitros = totalLitros; }
    
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    
    public Long getBombaId() { return bombaId; }
    public void setBombaId(Long bombaId) { this.bombaId = bombaId; }
}
