package com.example.ifbademo.repository;

import com.example.ifbademo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Optional<Aluno> findByEmail(String email);
}