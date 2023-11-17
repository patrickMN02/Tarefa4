package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Agenda;
import com.example.demo.models.Professor;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByDataInicioBetweenAndProfessor(LocalDate dataInicio, LocalDate dataFim, Professor professor);
}