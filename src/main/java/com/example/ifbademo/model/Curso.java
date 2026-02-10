package com.example.ifbademo.model;

import javax.persistence.*;

@Entity
@Table(name = "Curso")
public class Curso {

    @Id
    @GeneratedValue
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "imagem")
    private String imagem;

    @Column(name = "status")
    private String status;

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}