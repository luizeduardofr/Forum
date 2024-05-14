package br.edu.fema.Forum.Repository;

import br.edu.fema.Forum.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNome(String nomeUsuario);
    Optional<Usuario> findByEmail(String email);
}
