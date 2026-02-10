package com.example.ifbademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Component("contatoBean")
@Scope("view")
public class ContatoBean {

    private String nome;
    private String email;
    private String mensagem;

    @Autowired
    private JavaMailSender mailSender;

    public String enviar() {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo("gm351802@gmail.com");
            mail.setReplyTo(email);
            mail.setSubject("Nova mensagem do site - " + nome);
            mail.setText("Nome: " + nome + "\nE-mail: " + email + "\n\nMensagem:\n" + mensagem);

            mailSender.send(mail);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso! Logo iremos responder!",
                            "Mensagem enviada com sucesso! Em breve responderemos."));

            nome = email = mensagem = null;

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                            "Não foi possível enviar. Tente novamente mais tarde."));
        }
        return null;
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}