package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CategoriaCursoDTO;
import com.example.demo.dtos.CursoDTO;
import com.example.demo.dtos.DadosCursoDTO;
import com.example.demo.exceptions.RegraNegocioException;
import com.example.demo.models.CategoriaCurso;
import com.example.demo.models.Curso;
import com.example.demo.repository.CategoriaCursoRepository;
import com.example.demo.repository.CursoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CategoriaCursoRepository categoriaCursoRepository;

@Override
@Transactional
public Curso salvar(CursoDTO cursoDTO) {
CategoriaCurso categ = categoriaCursoRepository
.findById(cursoDTO.getCategoriaCursoId())
.orElseThrow(() -> new RegraNegocioException("Categoria não encontrada"));
Curso curso = new Curso();
curso.setNome(cursoDTO.getNome());
curso.setCargaHoraria(cursoDTO.getCargaHoraria());
curso.setCategoriaCurso(categ);
return cursoRepository.save(curso);
}

    @Override
    public DadosCursoDTO obterCursoPorId(Long id) {
        return cursoRepository.findById(id).map((Curso c) -> {
            return DadosCursoDTO.builder()
                    .id(c.getId())
                    .nome(c.getNome())
                    .cargaHoraria(c.getCargaHoraria())
                    .categoria(CategoriaCursoDTO.builder()
                            .id(c.getCategoriaCurso().getId())
                            .nome(c.getCategoriaCurso().getNome())
                            .build())
                    .build();
        }).orElseThrow(() -> new RegraNegocioException("Curso não encontrado."));
    }

    @Override
    @Transactional
    public void remover(Long id) {
        cursoRepository.deleteById(id);
    }

@Override
@Transactional
public void editar(Long id, CursoDTO cursoDto) {
Curso curso = cursoRepository.findById(id)
.orElseThrow(() -> new RegraNegocioException("Curso não encontrado"));
CategoriaCurso categoria = categoriaCursoRepository.findById(
cursoDto.getCategoriaCursoId())
.orElseThrow(() -> new RegraNegocioException("Categoria não encontrada"));
curso.setNome(cursoDto.getNome());
curso.setCargaHoraria(cursoDto.getCargaHoraria());
curso.setCategoriaCurso(categoria);
cursoRepository.save(curso);
}

@Override
public List<DadosCursoDTO> obterTodos() {
return cursoRepository.findAll().stream().map((Curso c) -> {
return DadosCursoDTO.builder()
.id(c.getId())
.nome(c.getNome())
.cargaHoraria(c.getCargaHoraria())
.categoria(CategoriaCursoDTO.builder()
.id(c.getCategoriaCurso().getId())
.nome(c.getCategoriaCurso().getNome())
.build())
.build();
}).collect(Collectors.toList());
}
}
