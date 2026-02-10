package com.example.ifbademo.repository;

import com.example.ifbademo.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Integer> {

    List<Aula> findByIdCurso(Integer idCurso);

    @Query("SELECT COALESCE(MAX(a.idAluno), 0) FROM Aula a WHERE a.idProfessor = :idProfessor AND a.idCurso = :idCurso")
    Integer findMaxIdAlunoByProfessorAndCurso(@Param("idProfessor") Integer idProfessor, @Param("idCurso") Integer idCurso);

    @Query("SELECT COALESCE(MAX(a.aulasNumero), 0) FROM Aula a WHERE a.idCurso = :idCurso")
    Integer findMaxAulasNumeroByCurso(@Param("idCurso") Integer idCurso);
}