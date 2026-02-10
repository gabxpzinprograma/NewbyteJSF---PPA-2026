package com.example.ifbademo.controller;

import com.example.ifbademo.model.Curso;
import com.example.ifbademo.model.AlunoCurso;
import com.example.ifbademo.model.AlunoCursoId;
import com.example.ifbademo.repository.CursoRepository;
import com.example.ifbademo.repository.AlunoCursoRepository;
import com.example.ifbademo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Optional;

@Component("cursoBean")
@Scope("view")
public class CursoBean {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlunoCursoRepository alunoCursoRepository;

    @Autowired
    private AuthenticationService authService;

    private List<Curso> todosCursos;
    private List<AlunoCurso> cursosEmProgresso;

    @PostConstruct
    public void init() {
        todosCursos = cursoRepository.findAll();

        if (authService.isAlunoLogado()) {
            Integer alunoId = authService.getAlunoLogado().getIdAluno();
            cursosEmProgresso = alunoCursoRepository.findByAlunoId(alunoId);
        } else {
            cursosEmProgresso = null; 
        }
    }


    public String inscrever(Integer idCurso) {
        FacesContext context = FacesContext.getCurrentInstance();

        if (!authService.isAlunoLogado()) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção",
                            "Você precisa estar logado como aluno para se inscrever em um curso."));
            return null;
        }

        Integer alunoId = authService.getAlunoLogado().getIdAluno();

        AlunoCursoId compositeId = new AlunoCursoId(alunoId, idCurso);
        Optional<AlunoCurso> inscricaoExistente = alunoCursoRepository.findById(compositeId);

        if (inscricaoExistente.isPresent()) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação",
                            "Você já está inscrito neste curso."));
            return "/cursos.xhtml?faces-redirect=true";
        }

        AlunoCurso novaInscricao = new AlunoCurso();
        novaInscricao.setAlunoId(alunoId);
        novaInscricao.setCursoId(idCurso);
        novaInscricao.setProgresso(0);

        alunoCursoRepository.save(novaInscricao);

        cursosEmProgresso = alunoCursoRepository.findByAlunoId(alunoId);

        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
                        "Inscrição realizada com sucesso. Vamos começar a aprender?"));

        return "/cursos.xhtml?faces-redirect=true";
    }

    public List<Curso> getTodosCursos() {
        return todosCursos;
    }

    public List<AlunoCurso> getCursosEmProgresso() {
        return cursosEmProgresso;
    }
}