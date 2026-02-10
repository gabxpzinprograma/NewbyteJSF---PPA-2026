package com.example.ifbademo.controller;

import com.example.ifbademo.model.Aluno;
import com.example.ifbademo.service.AlunoService;
import com.example.ifbademo.service.AuthenticationService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component("loginBean")
@Scope("session")
public class LoginBean implements Serializable {

    private String email;
    private String senha;
    private Aluno alunoLogado;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AuthenticationService authenticationService;

    public String logar() {
        var opt = alunoService.login(email, senha);
        
        if (opt.isPresent()) {
            authenticationService.loginAsAluno(opt.get());
            
            alunoLogado = opt.get();
            
            return "/index.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "E-mail ou senha incorretos"));
            return null;
        }
    }

    public String logout() {
        authenticationService.logout();
        alunoLogado = null;
        return "/index.xhtml?faces-redirect=true";
    }

    public boolean isLogado() {
        return authenticationService.isAlunoLogado();
    }

    public Aluno getAlunoLogado() {
        return alunoLogado;
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
