package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Professor;
import com.example.demo.services.ProfessorService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    @Autowired
    private ProfessorService clienteService;

    @PostMapping
    public Professor criarCliente(@RequestBody Professor cliente) {
        return clienteService.salvar(cliente);
    }

    @GetMapping
    public List<Professor> listarClientes() {
        return clienteService.obterTodos();
    }
}
