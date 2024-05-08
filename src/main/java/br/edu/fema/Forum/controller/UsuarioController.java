package br.edu.fema.Forum.controller;

import br.edu.fema.Forum.Repository.UsuarioRepository;
import br.edu.fema.Forum.controller.dto.UsuarioDto;
import br.edu.fema.Forum.controller.form.UsuarioForm;
import br.edu.fema.Forum.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @ResponseBody
    public List<UsuarioDto> lista(String nomeUsuario) {
        List<Usuario> usuarios;
        if (nomeUsuario != null) {
            usuarios = this.usuarioRepository.findByNome(nomeUsuario);
        }
        else {
            usuarios = this.usuarioRepository.findAll();
        }
        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody UsuarioForm form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }
}
