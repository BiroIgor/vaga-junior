package com.biroigor.service;

import com.biroigor.model.TipoCombustivel;
import com.biroigor.repository.TipoCombustivelRepository;
import com.biroigor.exception.ResourceNotFoundException;
import com.biroigor.exception.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas operações de negócio relacionadas aos tipos de combustível
 */
@Service
@Transactional
public class TipoCombustivelService {
    
    @Autowired
    private TipoCombustivelRepository repository;

    /**
     * Cria um novo tipo de combustível
     * @param nome Nome do tipo de combustível
     * @param precoPorLitro Preço por litro
     * @return Tipo de combustível criado
     * @throws DuplicateResourceException se já existe um tipo com o mesmo nome
     */
    public TipoCombustivel criar(String nome, double precoPorLitro) {
        if (repository.existsByNome(nome)) {
            throw new DuplicateResourceException("Já existe um tipo de combustível com o nome: " + nome);
        }
        
        TipoCombustivel tipo = new TipoCombustivel(nome, precoPorLitro);
        return repository.save(tipo);
    }

    /**
     * Lista todos os tipos de combustível
     * @return Lista de tipos de combustível
     */
    @Transactional(readOnly = true)
    public List<TipoCombustivel> listar() {
        return repository.findAll();
    }

    /**
     * Busca um tipo de combustível por ID
     * @param id ID do tipo de combustível
     * @return Optional contendo o tipo se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<TipoCombustivel> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /**
     * Busca um tipo de combustível por nome
     * @param nome Nome do tipo de combustível
     * @return Optional contendo o tipo se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<TipoCombustivel> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    /**
     * Atualiza um tipo de combustível
     * @param id ID do tipo de combustível
     * @param nome Novo nome
     * @param precoPorLitro Novo preço por litro
     * @return Tipo de combustível atualizado
     * @throws ResourceNotFoundException se o tipo não for encontrado
     */
    public TipoCombustivel atualizar(Long id, String nome, double precoPorLitro) {
        TipoCombustivel tipo = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tipo de combustível não encontrado com ID: " + id));
        
        // Verifica se o novo nome já existe (apenas se for diferente do atual)
        if (!tipo.getNome().equals(nome) && repository.existsByNome(nome)) {
            throw new DuplicateResourceException("Já existe um tipo de combustível com o nome: " + nome);
        }
        
        tipo.setNome(nome);
        tipo.setPrecoPorLitro(precoPorLitro);
        return repository.save(tipo);
    }

    /**
     * Remove um tipo de combustível
     * @param id ID do tipo de combustível
     * @throws ResourceNotFoundException se o tipo não for encontrado
     */
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de combustível não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}
