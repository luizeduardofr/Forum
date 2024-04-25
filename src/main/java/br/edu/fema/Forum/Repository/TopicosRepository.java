package br.edu.fema.Forum.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.fema.Forum.model.Topico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicosRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);

    @Query("Select t FROM Topico t where t.curso.nome = :nomeCurso")
    Page<Topico> carregarPorNomeCurso(@Param("nomeCurso") String nomeCurso, Pageable paginacao);
}