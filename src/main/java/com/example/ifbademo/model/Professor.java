package com.example.ifbademo.model;

import javax.persistence.*;

@Entity
@Table(name = "Professor")
public class Professor {

    @Id
    @GeneratedValue
    @Column(name = "id_professor")
    private Integer idProfessor;

    @Column(name = "nome_professor")
    private String nome;

    @Column(name = "email_professor")
    private String email;

    @Column(name = "senha")
    private String senha;

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}