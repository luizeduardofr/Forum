package br.edu.fema.Forum.controller.form;

import br.edu.fema.Forum.Repository.TopicosRepository;
import br.edu.fema.Forum.model.Topico;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

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
