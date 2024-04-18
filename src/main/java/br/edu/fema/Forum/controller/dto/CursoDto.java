package br.edu.fema.Forum.controller.dto;

import br.edu.fema.Forum.model.Curso;
import br.edu.fema.Forum.model.Topico;

import java.util.List;
import java.util.stream.Collectors;

public class CursoDto {

    private Long id;
    private String nome;
    private String categoria;

    public CursoDto (Curso curso) {
        super();
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.categoria = curso.getCategoria();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static List<CursoDto> converter(List<Curso> cursos) {
        return cursos.stream().map(CursoDto::new).collect(Collectors.toList());
    }
}
