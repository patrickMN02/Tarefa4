package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.models.Agenda;
import com.example.demo.models.Professor;

public interface AgendaService {
    Agenda salvar(Agenda agenda);
    List<Agenda> obterTodos();
    Agenda obterPorId(Long id);
    List<Agenda> obterAgendasDisponiveis(Professor professor, LocalDate dataInicio, LocalDate dataFim);
}
