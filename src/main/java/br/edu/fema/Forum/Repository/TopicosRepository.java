package br.edu.fema.Forum.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fema.Forum.model.Topico;
import br.edu.fema.Forum.model.StatusTopico;

public interface TopicosRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCursoNome(String nomeCurso);
    List<Topico> findByStatus(StatusTopico status);
}