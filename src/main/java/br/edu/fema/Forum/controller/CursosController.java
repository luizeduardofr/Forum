package br.edu.fema.Forum.controller;

import br.edu.fema.Forum.Repository.CursoRepository;
import br.edu.fema.Forum.controller.dto.CursoDto;
import br.edu.fema.Forum.controller.form.AtualizacaoCursosForm;
import br.edu.fema.Forum.controller.form.CursosForm;
import br.edu.fema.Forum.model.Curso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @ResponseBody
    public List<CursoDto> lista() {

        List<Curso> cursos = this.cursoRepository.findAll();
        return CursoDto.converter(cursos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CursoDto> cadastrar(@RequestBody @Valid CursosForm form, UriComponentsBuilder uriBuilder) {
        Curso curso = form.converter(form);
        cursoRepository.save(curso);

        URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursoDto(curso));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CursoDto> atualizar (@PathVariable Long id, @RequestBody @Valid AtualizacaoCursosForm form) {

        Optional<Curso> optional = cursoRepository.findById(id);
        if (optional.isPresent()) {
            Curso curso = form.atualizar(id, cursoRepository);
            return ResponseEntity.ok(new CursoDto(curso));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {

        Optional<Curso> optional = cursoRepository.findById(id);
        if(optional.isPresent()) {
            cursoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

