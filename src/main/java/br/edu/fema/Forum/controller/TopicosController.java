package br.edu.fema.Forum.controller;

import java.net.URI;
import java.util.List;

import br.edu.fema.Forum.Repository.CursoRepository;
import br.edu.fema.Forum.controller.dto.DetalhesDoTopicoDto;
import br.edu.fema.Forum.controller.form.AtualizacaoTopicosForm;
import br.edu.fema.Forum.controller.form.TopicosForm;
import br.edu.fema.Forum.controller.dto.TopicoDto;
import br.edu.fema.Forum.model.Topico;
import br.edu.fema.Forum.Repository.TopicosRepository;
import br.edu.fema.Forum.model.StatusTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso, StatusTopico status) {

        List<Topico> topicos;
        if(nomeCurso != null) {
            topicos = this.topicosRepository.findByCursoNome(nomeCurso);
        } else if (status != null) {
            topicos = this.topicosRepository.findByStatus(status);
        } else {
            topicos = this.topicosRepository.findAll();
        }
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicosForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicosRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public DetalhesDoTopicoDto detalhar(@PathVariable Long id) {

        Topico topico = topicosRepository.getReferenceById(id);
        return new DetalhesDoTopicoDto(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicosForm form) {
        Topico topico = form.atualizar(id, topicosRepository);
        return ResponseEntity.ok(new TopicoDto(topico));
    }
}
