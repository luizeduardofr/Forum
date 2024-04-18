package br.edu.fema.Forum.controller.form;

import br.edu.fema.Forum.Repository.CursoRepository;
import br.edu.fema.Forum.Repository.TopicosRepository;
import br.edu.fema.Forum.model.Curso;

import br.edu.fema.Forum.model.Topico;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AtualizacaoCursosForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso atualizar(Long id, CursoRepository cursoRepository) {

        Curso curso = cursoRepository.getReferenceById(id);
        curso.setNome(this.nome);
        return curso;
    }
}
