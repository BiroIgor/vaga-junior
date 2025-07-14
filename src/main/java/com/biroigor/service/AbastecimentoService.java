package com.biroigor.service;

import com.biroigor.model.Abastecimento;
import com.biroigor.model.BombaCombustivel;
import com.biroigor.repository.AbastecimentoRepository;
import com.biroigor.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas operações de negócio relacionadas aos abastecimentos
 */
@Service
@Transactional
public class AbastecimentoService {
    
    @Autowired
    private AbastecimentoRepository repository;

    /**
     * Cria um novo abastecimento
     * @param bomba Bomba utilizada
     * @param dataHora Data e hora do abastecimento
     * @param valorTotal Valor total pago
     * @param litragem Quantidade de litros
     * @return Abastecimento criado
     */
    public Abastecimento criar(BombaCombustivel bomba, LocalDateTime dataHora, double valorTotal, double litragem) {
        Abastecimento abastecimento = new Abastecimento(bomba, dataHora, valorTotal, litragem);
        return repository.save(abastecimento);
    }

    /**
     * Lista todos os abastecimentos
     * @return Lista de abastecimentos
     */
    @Transactional(readOnly = true)
    public List<Abastecimento> listar() {
        return repository.findAllByOrderByDataHoraDesc();
    }

    /**
     * Busca um abastecimento por ID
     * @param id ID do abastecimento
     * @return Optional contendo o abastecimento se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Abastecimento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /**
     * Lista abastecimentos por bomba
     * @param bomba Bomba de combustível
     * @return Lista de abastecimentos da bomba
     */
    @Transactional(readOnly = true)
    public List<Abastecimento> listarPorBomba(BombaCombustivel bomba) {
        return repository.findByBomba(bomba);
    }

    /**
     * Atualiza um abastecimento
     * @param id ID do abastecimento
     * @param bomba Nova bomba
     * @param dataHora Nova data/hora
     * @param valorTotal Novo valor total
     * @param litragem Nova litragem
     * @return Abastecimento atualizado
     */
    public Abastecimento atualizar(Long id, BombaCombustivel bomba, LocalDateTime dataHora, double valorTotal, double litragem) {
        Abastecimento abastecimento = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Abastecimento não encontrado com ID: " + id));
        
        abastecimento.setBomba(bomba);
        abastecimento.setDataHora(dataHora);
        abastecimento.setValorTotal(valorTotal);
        abastecimento.setLitragem(litragem);
        return repository.save(abastecimento);
    }

    /**
     * Remove um abastecimento
     * @param id ID do abastecimento
     */
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Abastecimento não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Calcula estatísticas por bomba
     * @param bombaId ID da bomba
     * @return Array com [totalLitros, valorTotal]
     */
    @Transactional(readOnly = true)
    public double[] calcularEstatisticasPorBomba(Long bombaId) {
        Double totalLitros = repository.calcularTotalLitrosPorBomba(bombaId);
        Double valorTotal = repository.calcularValorTotalPorBomba(bombaId);
        
        return new double[]{
            totalLitros != null ? totalLitros : 0.0,
            valorTotal != null ? valorTotal : 0.0
        };
    }
}