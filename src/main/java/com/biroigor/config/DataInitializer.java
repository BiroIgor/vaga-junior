package com.biroigor.config;

import com.biroigor.model.TipoCombustivel;
import com.biroigor.model.BombaCombustivel;
import com.biroigor.repository.TipoCombustivelRepository;
import com.biroigor.repository.BombaCombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por inserir dados iniciais no banco de dados
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private TipoCombustivelRepository tipoCombustivelRepository;
    
    @Autowired
    private BombaCombustivelRepository bombaCombustivelRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Inserir dados apenas se não existirem
        if (tipoCombustivelRepository.count() == 0) {
            initializeTiposCombustivel();
        }
        
        if (bombaCombustivelRepository.count() == 0) {
            initializeBombas();
        }
    }
    
    private void initializeTiposCombustivel() {
        // Criar tipos de combustível padrão
        TipoCombustivel gasolinaComum = new TipoCombustivel("Gasolina Comum", 5.49);
        TipoCombustivel gasolinaAditivada = new TipoCombustivel("Gasolina Aditivada", 5.79);
        TipoCombustivel etanol = new TipoCombustivel("Etanol", 3.89);
        TipoCombustivel dieselComum = new TipoCombustivel("Diesel Comum", 4.99);
        TipoCombustivel dieselS10 = new TipoCombustivel("Diesel S-10", 5.19);
        
        tipoCombustivelRepository.save(gasolinaComum);
        tipoCombustivelRepository.save(gasolinaAditivada);
        tipoCombustivelRepository.save(etanol);
        tipoCombustivelRepository.save(dieselComum);
        tipoCombustivelRepository.save(dieselS10);
        
        System.out.println("✅ Tipos de combustível inicializados com sucesso!");
    }
    
    private void initializeBombas() {
        // Buscar tipos de combustível
        TipoCombustivel gasolinaComum = tipoCombustivelRepository.findByNome("Gasolina Comum").orElse(null);
        TipoCombustivel gasolinaAditivada = tipoCombustivelRepository.findByNome("Gasolina Aditivada").orElse(null);
        TipoCombustivel etanol = tipoCombustivelRepository.findByNome("Etanol").orElse(null);
        TipoCombustivel dieselComum = tipoCombustivelRepository.findByNome("Diesel Comum").orElse(null);
        TipoCombustivel dieselS10 = tipoCombustivelRepository.findByNome("Diesel S-10").orElse(null);
        
        // Criar bombas padrão
        if (gasolinaComum != null) {
            bombaCombustivelRepository.save(new BombaCombustivel("Bomba 1 - Gasolina Comum", gasolinaComum));
            bombaCombustivelRepository.save(new BombaCombustivel("Bomba 4 - Gasolina Comum", gasolinaComum));
        }
        
        if (gasolinaAditivada != null) {
            bombaCombustivelRepository.save(new BombaCombustivel("Bomba 2 - Gasolina Aditivada", gasolinaAditivada));
        }
        
        if (etanol != null) {
            bombaCombustivelRepository.save(new BombaCombustivel("Bomba 3 - Etanol", etanol));
            bombaCombustivelRepository.save(new BombaCombustivel("Bomba 5 - Etanol", etanol));
        }
        
        if (dieselComum != null) {
            bombaCombustivelRepository.save(new BombaCombustivel("Bomba 6 - Diesel Comum", dieselComum));
        }
        
        if (dieselS10 != null) {
            bombaCombustivelRepository.save(new BombaCombustivel("Bomba 7 - Diesel S-10", dieselS10));
        }
        
        System.out.println("✅ Bombas de combustível inicializadas com sucesso!");
    }
}
