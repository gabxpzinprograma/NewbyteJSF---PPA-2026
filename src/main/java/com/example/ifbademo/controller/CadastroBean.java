package com.example.ifbademo.controller;

import com.example.ifbademo.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Component("cadastroBean")
@Scope("view")
public class CadastroBean {

    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
    private boolean aceitoTermos;

    @Autowired
    private CadastroService cadastroService;

    public String cadastrar() {
        String resultado = cadastroService.cadastrar(nome, email, senha, confirmarSenha);

        FacesContext ctx = FacesContext.getCurrentInstance();

        if (resultado.contains("sucesso")) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cadastro realizado com sucesso",
                    "Você já pode fazer login"));

            nome = email = senha = confirmarSenha = null;
            aceitoTermos = false;

            ctx.getExternalContext().getFlash().setKeepMessages(true);

            return "/login.xhtml?faces-redirect=true";
        } else {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro no cadastro", resultado));
            return null;
        }
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

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public boolean getAceitoTermos() {
        return aceitoTermos;
    }

    public void setAceitoTermos(boolean aceitoTermos) {
        this.aceitoTermos = aceitoTermos;
    }

}
