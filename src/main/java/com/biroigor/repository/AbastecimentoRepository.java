package com.biroigor.repository;

import com.biroigor.model.Abastecimento;
import com.biroigor.model.BombaCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
    
    /**
     * Busca abastecimentos por bomba
     * @param bomba Bomba de combustível
     * @return Lista de abastecimentos
     */
    List<Abastecimento> findByBomba(BombaCombustivel bomba);
    
    /**
     * Busca abastecimentos em um período específico
     * @param inicio Data/hora de início
     * @param fim Data/hora de fim
     * @return Lista de abastecimentos no período
     */
    List<Abastecimento> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    
    /**
     * Busca abastecimentos ordenados por data (mais recentes primeiro)
     * @return Lista de abastecimentos ordenada
     */
    List<Abastecimento> findAllByOrderByDataHoraDesc();
    
    /**
     * Calcula o total de litros abastecidos por bomba
     * @param bombaId ID da bomba
     * @return Total de litros
     */
    @Query("SELECT SUM(a.litragem) FROM Abastecimento a WHERE a.bomba.id = :bombaId")
    Double calcularTotalLitrosPorBomba(@Param("bombaId") Long bombaId);
    
    /**
     * Calcula o valor total arrecadado por bomba
     * @param bombaId ID da bomba
     * @return Valor total arrecadado
     */
    @Query("SELECT SUM(a.valorTotal) FROM Abastecimento a WHERE a.bomba.id = :bombaId")
    Double calcularValorTotalPorBomba(@Param("bombaId") Long bombaId);
}
