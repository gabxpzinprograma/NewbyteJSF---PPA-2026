package com.example.ifbademo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Aluno_Curso")
@IdClass(AlunoCursoId.class)
public class AlunoCurso {

    @Id
    @Column(name = "aluno_id")
    private Integer alunoId;

    @Id
    @Column(name = "curso_id")
    private Integer cursoId;

    @Column(name = "progresso")
    private Integer progresso = 0;
    @Column(name = "matricula")
    private LocalDateTime matricula;

    public LocalDateTime getMatricula() {
        return matricula;
    }

    public void setMatricula(LocalDateTime matricula) {
        this.matricula = matricula;
    }

    @ManyToOne
    @JoinColumn(name = "curso_id", insertable = false, updatable = false)
    private Curso curso;

    public Integer getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Integer alunoId) {
        this.alunoId = alunoId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getProgresso() {
        return progresso != null ? progresso : 0;
    }

    public void setProgresso(Integer progresso) {
        this.progresso = progresso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
