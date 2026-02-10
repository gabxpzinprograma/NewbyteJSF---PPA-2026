package com.example.ifbademo.service;

import com.example.ifbademo.model.Aluno;
import com.example.ifbademo.model.Professor;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Service
public class AuthenticationService {

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public void loginAsAluno(Aluno aluno) {
        logout();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        context.getExternalContext().getSessionMap().put("alunoLogado", aluno);
        context.getExternalContext().getSessionMap().put("usuarioTipo", "ALUNO");
    }

    public void loginAsProfessor(Professor professor) {
        logout();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        context.getExternalContext().getSessionMap().put("professorLogado", professor);
        context.getExternalContext().getSessionMap().put("usuarioTipo", "PROFESSOR");
    }

    public Aluno getAlunoLogado() {
        return (Aluno) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("alunoLogado");
    }

    public Professor getProfessorLogado() {
        return (Professor) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("professorLogado");
    }

    public boolean isAlunoLogado() {
        return getAlunoLogado() != null;
    }

    public boolean isProfessorLogado() {
        return getProfessorLogado() != null;
    }

    public String getTipoUsuario() {
        Object tipo = FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("usuarioTipo");
        return tipo != null ? tipo.toString() : null;
    }
}