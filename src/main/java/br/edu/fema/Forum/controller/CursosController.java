package br.edu.fema.Forum.controller;

import br.edu.fema.Forum.Repository.CursoRepository;
import br.edu.fema.Forum.controller.dto.CursoDto;
import br.edu.fema.Forum.controller.form.AtualizacaoCursosForm;
import br.edu.fema.Forum.controller.form.CursosForm;
import br.edu.fema.Forum.model.Curso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @ResponseBody
    public Page<CursoDto> lista(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10)
                                Pageable paginacao) {

        Page<Curso> cursos;

        cursos = this.cursoRepository.findAll(paginacao);
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

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {

        Optional<Curso> curso = cursoRepository.findById(id);
        if(curso.isPresent()) {
            return ResponseEntity.ok(new CursoDto(curso.get()));
        }
        return ResponseEntity.notFound().build();
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

