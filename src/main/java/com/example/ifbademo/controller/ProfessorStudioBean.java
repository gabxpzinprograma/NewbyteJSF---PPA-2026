package com.example.ifbademo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ifbademo.model.Aula;
import com.example.ifbademo.model.Curso;
import com.example.ifbademo.repository.AulaRepository;
import com.example.ifbademo.repository.CursoRepository;
import com.example.ifbademo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component("professorStudioBean")
@Scope("view")
public class ProfessorStudioBean {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    private List<Curso> cursos;
    private List<Aula> aulas;
    private String titulo;
    private Part videoPart;
    private Integer idCursoSelecionado;
    private Integer idAulaRemover;

    @PostConstruct
    public void init() {
        if (authenticationService.isProfessorLogado()) {
            cursos = cursoRepository.findAll(); 
            if (idCursoSelecionado != null) {
                aulas = aulaRepository.findByIdCurso(idCursoSelecionado);
            }
        }
    }

    public void checkProfessorLogin() {
        if (!authenticationService.isProfessorLogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/professor-login.xhtml");
            } catch (IOException e) {
            }
        }
    }

    private byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;
        while ((len = is.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        return baos.toByteArray();
    }

    public void adicionarAula() {
        try {
            if (idCursoSelecionado == null || titulo == null || videoPart == null) {
                addError("Preencha todos os campos.");
                return;
            }

            Integer idProfessor = authenticationService.getProfessorLogado().getIdProfessor();

            Integer maxIdAluno = aulaRepository.findMaxIdAlunoByProfessorAndCurso(idProfessor, idCursoSelecionado);
            Integer idAlunoNovo = maxIdAluno + 1;

            Integer maxAulasNumero = aulaRepository.findMaxAulasNumeroByCurso(idCursoSelecionado);
            Integer aulasNumeroNovo = maxAulasNumero + 1;

            String videoUrl = null;
            if (videoPart != null && videoPart.getSize() > 0) {
                Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "djucslux1",
                        "api_key", "124533292338774",
                        "api_secret", "UtncyJytlOOEyGLod4nfuqA_lGw"));

                try (InputStream is = videoPart.getInputStream()) {
                    byte[] bytes = toByteArray(is);
                    Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "video"));
                    videoUrl = (String) uploadResult.get("secure_url");
                }
            }

            Aula novaAula = new Aula();
            novaAula.setTitulo(titulo);
            novaAula.setVideo(videoUrl);
            novaAula.setIdProfessor(idProfessor);
            novaAula.setIdCurso(idCursoSelecionado);
            novaAula.setIdAluno(idAlunoNovo);
            novaAula.setAulasNumero(aulasNumeroNovo);

            aulaRepository.save(novaAula);

            addInfo("Aula adicionada com sucesso.");
            titulo = null;
            videoPart = null;
            init(); 
        } catch (Exception e) {
            addError("Erro ao adicionar aula: " + e.getMessage());
        }
    }

    public void removerAula() {
        if (idAulaRemover != null) {
            aulaRepository.deleteById(idAulaRemover);
            addInfo("Aula removida com sucesso.");
            init(); 
        } else {
            addError("Selecione uma aula para remover.");
        }
    }

    private void addInfo(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
    }

    private void addError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg));
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Part getVideoPart() {
        return videoPart;
    }

    public void setVideoPart(Part videoPart) {
        this.videoPart = videoPart;
    }

    public Integer getIdCursoSelecionado() {
        return idCursoSelecionado;
    }

    public void setIdCursoSelecionado(Integer idCursoSelecionado) {
        this.idCursoSelecionado = idCursoSelecionado;
        if (idCursoSelecionado != null) {
            aulas = aulaRepository.findByIdCurso(idCursoSelecionado);
        }
    }

    public Integer getIdAulaRemover() {
        return idAulaRemover;
    }

    public void setIdAulaRemover(Integer idAulaRemover) {
        this.idAulaRemover = idAulaRemover;
    }

    public String getCursoTitulo(Integer idCurso) {
        return cursoRepository.findById(idCurso)
                .map(Curso::getTitulo)
                .orElse("Curso não encontrado");
    }
}