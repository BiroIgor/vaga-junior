package com.biroigor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostoCombustivelController {
    @GetMapping("/")
    public String hello() {
        return "API do Posto de Combustível está rodando!";
    }
}
