package br.edu.fema.Forum.Repository;

import br.edu.fema.Forum.model.Curso;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome (String nomeCurso);
}
