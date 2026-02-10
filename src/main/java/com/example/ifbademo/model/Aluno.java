package com.example.ifbademo.model;

import javax.persistence.*;

@Entity
@Table(name = "Aluno")
public class Aluno {

    @Id
    @GeneratedValue
    @Column(name = "id_aluno")
    private Integer idAluno;

    @Column(name = "nome_aluno")
    private String nome;

    @Column(name = "email_aluno")
    private String email;

    @Column(name = "senha")
    private String senha;

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
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