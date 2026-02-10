package com.example.ifbademo.repository;

import com.example.ifbademo.model.AlunoCurso;
import com.example.ifbademo.model.AlunoCursoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoCursoRepository extends JpaRepository<AlunoCurso, AlunoCursoId> {
    @Query("SELECT ac FROM AlunoCurso ac JOIN FETCH ac.curso WHERE ac.alunoId = :alunoId")
    List<AlunoCurso> findByAlunoId(@Param("alunoId") Integer alunoId);
}