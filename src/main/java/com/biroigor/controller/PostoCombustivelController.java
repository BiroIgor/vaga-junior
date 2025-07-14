package com.biroigor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Status da API", description = "Endpoints de status e informações da API")
public class PostoCombustivelController {
    
    @GetMapping("/")
    @Operation(summary = "Status da aplicação", description = "Retorna o status da aplicação")
    @ApiResponse(responseCode = "200", description = "Aplicação funcionando corretamente")
    public Map<String, Object> status() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "online");
        response.put("message", "API do Posto de Combustível está rodando!");
        response.put("timestamp", LocalDateTime.now());
        response.put("version", "1.0.0");
        return response;
    }
    
    @GetMapping("/api")
    @Operation(summary = "Status da API", description = "Endpoint base da API REST")
    @ApiResponse(responseCode = "200", description = "API funcionando corretamente")
    public Map<String, Object> apiStatus() {
        Map<String, Object> response = new HashMap<>();
        response.put("api", "Posto de Combustível REST API");
        response.put("version", "1.0.0");
        response.put("status", "running");
        response.put("timestamp", LocalDateTime.now());
        response.put("endpoints", Map.of(
            "tipos-combustivel", "/api/tipos-combustivel",
            "bombas-combustivel", "/api/bombas-combustivel", 
            "abastecimentos", "/api/abastecimentos",
            "swagger-ui", "/swagger-ui.html",
            "h2-console", "/h2-console"
        ));
        return response;
    }
    
    @GetMapping("/api/")
    @Operation(summary = "Status da API (com barra)", description = "Endpoint base da API REST com barra final")
    @ApiResponse(responseCode = "200", description = "API funcionando corretamente")
    public Map<String, Object> apiStatusWithSlash() {
        return apiStatus();
    }
}
