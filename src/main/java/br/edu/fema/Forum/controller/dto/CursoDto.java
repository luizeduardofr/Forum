package br.edu.fema.Forum.controller.dto;

import br.edu.fema.Forum.model.Curso;
import org.springframework.data.domain.Page;


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

    public static Page<CursoDto> converter(Page<Curso> cursos) {
        return cursos.map(CursoDto::new);
    }
}
