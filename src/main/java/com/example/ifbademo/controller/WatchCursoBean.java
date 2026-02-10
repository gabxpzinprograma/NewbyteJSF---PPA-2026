package com.example.ifbademo.controller;

import com.example.ifbademo.model.AlunoCurso;
import com.example.ifbademo.model.AlunoCursoId;
import com.example.ifbademo.model.Aula;
import com.example.ifbademo.model.Curso;
import com.example.ifbademo.repository.AlunoCursoRepository;
import com.example.ifbademo.repository.AulaRepository;
import com.example.ifbademo.repository.CursoRepository;
import com.example.ifbademo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component("watchCursoBean")
@Scope("view")
public class WatchCursoBean implements Serializable {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlunoCursoRepository alunoCursoRepository;

    @Autowired
    private AuthenticationService authService;

    private Integer cursoId;
    private Curso curso;
    private List<Aula> aulas;
    private Aula aulaAtual;
    private Integer progresso = 0;
    private int currentAulaIndex = 0; 

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        String param = params.get("curso");
        if (param == null) {
            param = params.get("idCurso");
        }

        if (param != null) {
            try {
                cursoId = Integer.parseInt(param);
                curso = cursoRepository.findById(cursoId).orElse(null);

                if (curso != null) {
                    aulas = aulaRepository.findByIdCurso(cursoId);

                    if (aulas != null && !aulas.isEmpty()) {
                        aulas.sort(Comparator.comparingInt(Aula::getAulasNumero));

                        if (authService.isAlunoLogado()) {
                            Integer alunoId = authService.getAlunoLogado().getIdAluno();
                            AlunoCursoId acId = new AlunoCursoId(alunoId, cursoId);
                            Optional<AlunoCurso> acOpt = alunoCursoRepository.findById(acId);
                            acOpt.ifPresent(ac -> progresso = ac.getProgresso());
                        }

                        int totalAulas = aulas.size();
                        int completedAulas = (int) Math.round((progresso / 100.0) * totalAulas);
                        int startIndex = completedAulas;
                        if (startIndex >= totalAulas) {
                            startIndex = totalAulas - 1;
                        }
                        aulaAtual = aulas.get(startIndex);
                        currentAulaIndex = startIndex;
                    } else {
                        addError("Este curso não possui aulas cadastradas ainda.");
                    }
                } else {
                    addError("Curso com ID " + cursoId + " não encontrado no banco.");
                }
            } catch (NumberFormatException e) {
                addError("ID do curso inválido.");
            }
        }
    }

    public void atualizarProgresso(Integer novoProgresso) {
        if (novoProgresso == null || novoProgresso <= progresso)
            return;

        progresso = novoProgresso;

        if (!authService.isAlunoLogado() || cursoId == null)
            return;

        Integer alunoId = authService.getAlunoLogado().getIdAluno();
        AlunoCursoId acId = new AlunoCursoId(alunoId, cursoId);

        Optional<AlunoCurso> opt = alunoCursoRepository.findById(acId);
        if (opt.isPresent()) {
            AlunoCurso ac = opt.get();
            ac.setProgresso(progresso);
            alunoCursoRepository.save(ac);
        } else {
            AlunoCurso nova = new AlunoCurso();
            nova.setAlunoId(alunoId);
            nova.setCursoId(cursoId);
            nova.setProgresso(progresso);
            alunoCursoRepository.save(nova);
        }
    }

    public void atualizarProgressoFromJS() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        String novoStr = params.get("novoProgresso");
        if (novoStr != null && !novoStr.isEmpty()) {
            try {
                Integer novo = Integer.parseInt(novoStr);
                atualizarProgresso(novo);
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public void selecionarAula(Aula aula) {
        aulaAtual = aula;
    }

    public String getVideoUrlAtual() {
        return aulaAtual != null ? aulaAtual.getVideo() : null;
    }

    public String getTituloAulaAtual() {
        return aulaAtual != null ? aulaAtual.getTitulo() : "Selecione uma aula";
    }

    public Curso getCurso() {
        return curso;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public Aula getAulaAtual() {
        return aulaAtual;
    }

    public Integer getProgresso() {
        return progresso;
    }

    public int getCurrentAulaIndex() {
        return currentAulaIndex;
    }

    public AuthenticationService getAuthService() {
        return authService;
    }

    private void addError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg));
    }
}