package com.example.ifbademo.controller;

import com.example.ifbademo.model.Aluno;
import com.example.ifbademo.model.AlunoCurso;
import com.example.ifbademo.model.AlunoCursoId;
import com.example.ifbademo.model.Curso;
import com.example.ifbademo.repository.AlunoCursoRepository;
import com.example.ifbademo.repository.CursoRepository;
import com.example.ifbademo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
// EU DESISTO DESSA DESGRAMA, EU VOU DORMIR, NÃO SEI COMO FAZ, MALDITO SEJA O CRIADOR DO JAVA AAAAAAAAAAAAAAAAAAAAAAAAAAAA
//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("homeBean")
@Scope("view")
public class HomeBean {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlunoCursoRepository alunoCursoRepository;

    @Autowired
    private AuthenticationService authenticationService;

    private List<Curso> todosCursos;
    private List<AlunoCurso> cursosEmProgresso;
    private Aluno alunoLogado;

    @PostConstruct
    public void init() {
        todosCursos = cursoRepository.findByStatus("1");

        alunoLogado = authenticationService.getAlunoLogado();

        if (authenticationService.isAlunoLogado()) {
            Integer alunoId = alunoLogado.getIdAluno();

            List<AlunoCurso> todosDoAluno = alunoCursoRepository.findByAlunoId(alunoId);

            cursosEmProgresso = todosDoAluno.stream()
                    .filter(ac -> ac.getProgresso() != null && ac.getProgresso() > 0)
                    .collect(Collectors.toList());
        } else {
            cursosEmProgresso = new ArrayList<>();
        }
    }

    public String inscrever() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        String idStr = params.get("idCurso");
        if (idStr == null) return null;

        Integer idCurso = Integer.parseInt(idStr);

        if (!authenticationService.isAlunoLogado()) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Você precisa estar logado para se inscrever.");
            return null;
        }

        Integer alunoId = authenticationService.getAlunoLogado().getIdAluno();
        AlunoCursoId compositeId = new AlunoCursoId(alunoId, idCurso);

        if (alunoCursoRepository.findById(compositeId).isPresent()) {
            addMessage(FacesMessage.SEVERITY_INFO, "Informação", "Você já está inscrito neste curso.");
            return null;
        }

        AlunoCurso nova = new AlunoCurso();
        nova.setAlunoId(alunoId);
        nova.setCursoId(idCurso);
        nova.setProgresso(0);
        nova.setMatricula(LocalDateTime.now());
        alunoCursoRepository.save(nova);

        addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Inscrição realizada com sucesso!");
        init();
        return null;
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severity, summary, detail));
    }

    public List<Curso> getTodosCursos() {
        return todosCursos;
    }

    public List<AlunoCurso> getCursosEmProgresso() {
        return cursosEmProgresso;
    }

    public Aluno getAlunoLogado() {
        return alunoLogado;
    }

    public boolean isLogado() {
        return authenticationService.isAlunoLogado();
    }
}