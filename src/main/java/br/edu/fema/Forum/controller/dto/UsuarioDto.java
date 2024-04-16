package br.edu.fema.Forum.controller.dto;

import br.edu.fema.Forum.model.Usuario;

import java.util.stream.Collectors;
import java.util.List;

public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public UsuarioDto(Usuario usuario){
        super();
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}
