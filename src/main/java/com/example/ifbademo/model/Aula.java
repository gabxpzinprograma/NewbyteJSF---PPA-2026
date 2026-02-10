package com.example.ifbademo.model;

import javax.persistence.*;

@Entity
@Table(name = "Aula")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula", nullable = false)
    private Integer idAula;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "video")
    private String video;

    @Column(name = "id_professor")
    private Integer idProfessor;

    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "id_aluno")
    private Integer idAluno;

    @Column(name = "aulas_numero")
    private Integer aulasNumero;

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public Integer getAulasNumero() {
        return aulasNumero;
    }

    public void setAulasNumero(Integer aulasNumero) {
        this.aulasNumero = aulasNumero;
    }
}