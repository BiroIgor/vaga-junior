package com.biroigor.repository;

import com.biroigor.model.BombaCombustivel;
import com.biroigor.model.TipoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BombaCombustivelRepository extends JpaRepository<BombaCombustivel, Long> {
    
    /**
     * Busca uma bomba pelo nome
     * @param nome Nome da bomba
     * @return Optional contendo a bomba se encontrada
     */
    Optional<BombaCombustivel> findByNome(String nome);
    
    /**
     * Busca todas as bombas que fornecem um tipo específico de combustível
     * @param tipoCombustivel Tipo de combustível
     * @return Lista de bombas
     */
    List<BombaCombustivel> findByTipoCombustivel(TipoCombustivel tipoCombustivel);
    
    /**
     * Verifica se existe uma bomba com o nome especificado
     * @param nome Nome da bomba
     * @return true se existe, false caso contrário
     */
    boolean existsByNome(String nome);
}
