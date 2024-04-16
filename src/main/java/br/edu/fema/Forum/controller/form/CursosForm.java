package br.edu.fema.Forum.controller.form;

import br.edu.fema.Forum.Repository.CursoRepository;
import br.edu.fema.Forum.model.Curso;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CursosForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String nome;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String categoria;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Curso converter(CursosForm cursosForm){
        return new Curso(this.nome, this.categoria);
    }
}
