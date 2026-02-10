package com.example.ifbademo.controller;

import com.example.ifbademo.model.Professor;
import com.example.ifbademo.repository.ProfessorRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Component("professorCadastroBean")
@Scope("view")
public class ProfessorCadastroBean {

    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
    private boolean aceitoTermos;

    @Autowired
    private ProfessorRepository professorRepository;

    public String cadastrar() {
        if (!senha.equals(confirmarSenha)) {
            addError("As senhas não coincidem.");
            return null;
        }

        if (professorRepository.findByEmail(email).isPresent()) {
            addError("Este e-mail já é de um professor.");
            return null;
        }

        Professor novo = new Professor();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));

        professorRepository.save(novo);

        addInfo("Cadastro concluido",
                "A Newbyte irá ativar sua conta em breve. Faça login");

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "/professor-login.xhtml?faces-redirect=true";
    }

    private void addError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg));
    }

    private void addInfo(String titulo, String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, msg));
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