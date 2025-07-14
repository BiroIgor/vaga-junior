package com.biroigor.repository;

import com.biroigor.model.TipoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoCombustivelRepository extends JpaRepository<TipoCombustivel, Long> {
    
    /**
     * Busca um tipo de combustível pelo nome
     * @param nome Nome do tipo de combustível
     * @return Optional contendo o tipo se encontrado
     */
    Optional<TipoCombustivel> findByNome(String nome);
    
    /**
     * Verifica se existe um tipo de combustível com o nome especificado
     * @param nome Nome do tipo de combustível
     * @return true se existe, false caso contrário
     */
    boolean existsByNome(String nome);
}
