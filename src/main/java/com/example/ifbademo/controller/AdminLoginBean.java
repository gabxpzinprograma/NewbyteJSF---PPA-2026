package com.example.ifbademo.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("adminLoginBean")
@Scope("session")
public class AdminLoginBean {

    private String username;
    private String password;

    public String logar() {
        if ("newbyte".equals(username) && "newbyteokk".equals(password)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminLogado", true);
            return "/adm.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha ou Usuário incorretos!",
                            "E-mail ou senha incorretos"));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("adminLogado");
        return "/auth.xhtml?faces-redirect=true";
    }

    public boolean isLogado() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adminLogado") != null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}