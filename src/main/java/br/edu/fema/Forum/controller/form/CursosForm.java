package br.edu.fema.Forum.controller.form;


import br.edu.fema.Forum.model.Curso;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
