package br.edu.fema.Forum.controller.form;

import br.edu.fema.Forum.Repository.TopicosRepository;
import br.edu.fema.Forum.model.Topico;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoTopicosForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicosRepository topicosRepository) {

        Topico topico = topicosRepository.getReferenceById(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        return topico;
    }
}
