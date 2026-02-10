package com.example.ifbademo.controller;

import com.example.ifbademo.model.Professor;
import com.example.ifbademo.service.ProfessorService;
import com.example.ifbademo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Component("professorLoginBean")
@Scope("session")
public class ProfessorLoginBean {

    private String email;
    private String senha;
    private Professor professorLogado;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AuthenticationService authenticationService;

    public String logar() {
        var opt = professorService.login(email, senha);
        
        if (opt.isPresent()) {
            authenticationService.loginAsProfessor(opt.get());
            
            professorLogado = opt.get();
            
            return "/index.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "E-mail ou senha incorretos"));
            return null;
        }
    }

    public String logout() {
        authenticationService.logout();
        professorLogado = null;
        return "/index.xhtml?faces-redirect=true";
    }

    public boolean isLogado() {
        return authenticationService.isProfessorLogado();
    }

    public Professor getProfessorLogado() {
        return professorLogado;
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
