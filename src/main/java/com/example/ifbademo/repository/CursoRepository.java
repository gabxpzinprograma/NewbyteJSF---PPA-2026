package com.example.ifbademo.repository;

import com.example.ifbademo.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
    /**
     * Busca cursos por status
     * Equivalente ao: SELECT id_curso, titulo FROM Curso WHERE status = 1
     */
    List<Curso> findByStatus(String status);
    
    /**
     * Busca curso por id e status (para o get_curso.php)
     * Equivalente ao: SELECT titulo, conteudo, imagem FROM Curso WHERE id_curso = ? AND status = 1
     */
    @Query("SELECT c FROM Curso c WHERE c.idCurso = :idCurso AND c.status = :status")
    Optional<Curso> findByIdCursoAndStatus(@Param("idCurso") Integer idCurso, @Param("status") String status);
}
