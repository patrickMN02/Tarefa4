package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Professor;

public interface ProfessorService {
    Professor salvar(Professor professor);
    List<Professor> obterTodos();
    Professor obterPorId(Long id);
}
