package com.biroigor.service;

import com.biroigor.model.BombaCombustivel;
import com.biroigor.model.TipoCombustivel;
import com.biroigor.repository.BombaCombustivelRepository;
import com.biroigor.exception.ResourceNotFoundException;
import com.biroigor.exception.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas operações de negócio relacionadas às bombas de combustível
 */
@Service
@Transactional
public class BombaCombustivelService {
    
    @Autowired
    private BombaCombustivelRepository repository;

    /**
     * Cria uma nova bomba de combustível
     * @param nome Nome da bomba
     * @param tipoCombustivel Tipo de combustível que a bomba fornece
     * @return Bomba criada
     * @throws DuplicateResourceException se já existe uma bomba com o mesmo nome
     */
    public BombaCombustivel criar(String nome, TipoCombustivel tipoCombustivel) {
        if (repository.existsByNome(nome)) {
            throw new DuplicateResourceException("Já existe uma bomba com o nome: " + nome);
        }
        
        BombaCombustivel bomba = new BombaCombustivel(nome, tipoCombustivel);
        return repository.save(bomba);
    }

    /**
     * Lista todas as bombas de combustível
     * @return Lista de bombas
     */
    @Transactional(readOnly = true)
    public List<BombaCombustivel> listar() {
        return repository.findAll();
    }

    /**
     * Busca uma bomba por ID
     * @param id ID da bomba
     * @return Optional contendo a bomba se encontrada
     */
    @Transactional(readOnly = true)
    public Optional<BombaCombustivel> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /**
     * Busca uma bomba por nome
     * @param nome Nome da bomba
     * @return Optional contendo a bomba se encontrada
     */
    @Transactional(readOnly = true)
    public Optional<BombaCombustivel> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    /**
     * Lista bombas por tipo de combustível
     * @param tipoCombustivel Tipo de combustível
     * @return Lista de bombas que fornecem o tipo especificado
     */
    @Transactional(readOnly = true)
    public List<BombaCombustivel> listarPorTipoCombustivel(TipoCombustivel tipoCombustivel) {
        return repository.findByTipoCombustivel(tipoCombustivel);
    }

    /**
     * Atualiza uma bomba de combustível
     * @param id ID da bomba
     * @param nome Novo nome
     * @param tipoCombustivel Novo tipo de combustível
     * @return Bomba atualizada
     * @throws ResourceNotFoundException se a bomba não for encontrada
     */
    public BombaCombustivel atualizar(Long id, String nome, TipoCombustivel tipoCombustivel) {
        BombaCombustivel bomba = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bomba não encontrada com ID: " + id));
        
        // Verifica se o novo nome já existe (apenas se for diferente do atual)
        if (!bomba.getNome().equals(nome) && repository.existsByNome(nome)) {
            throw new DuplicateResourceException("Já existe uma bomba com o nome: " + nome);
        }
        
        bomba.setNome(nome);
        bomba.setTipoCombustivel(tipoCombustivel);
        return repository.save(bomba);
    }

    /**
     * Remove uma bomba de combustível
     * @param id ID da bomba
     * @throws ResourceNotFoundException se a bomba não for encontrada
     */
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Bomba não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}