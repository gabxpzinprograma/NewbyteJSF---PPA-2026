package com.example.ifbademo.controller;

import com.example.ifbademo.model.Curso;
import com.example.ifbademo.repository.CursoRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component("adminBean")
@Scope("view")
public class AdminBean {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AdminLoginBean adminLoginBean;

    private List<Curso> cursos;
    private String titulo;
    private String conteudo;
    private Part imagem;
    private Integer idCursoRemover;

    @PostConstruct
    public void init() {
        cursos = cursoRepository.findAll();
    }

    public void checkLogin() {
        if (!adminLoginBean.isLogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("auth.xhtml");
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
        baos.flush();
        return baos.toByteArray();
    }

    @SuppressWarnings("rawtypes")
    public void adicionarCurso() {
        try {
            String imageUrl = null;
            if (imagem != null && imagem.getSize() > 0) {
                Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "djucslux1",
                    "api_key", "124533292338774",
                    "api_secret", "UtncyJytlOOEyGLod4nfuqA_lGw"
                ));

                try (InputStream is = imagem.getInputStream()) {
                    byte[] bytes = toByteArray(is);
                    Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.emptyMap());
                    imageUrl = (String) uploadResult.get("secure_url");
                }
            }

            Curso novo = new Curso();
            novo.setTitulo(titulo);
            novo.setConteudo(conteudo);
            novo.setImagem(imageUrl);
            cursoRepository.save(novo);

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Deu tudo certo, curso adcionado", "Curso adicionado com sucesso."));

            titulo = null;
            conteudo = null;
            imagem = null;
            init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Algo deu errado de novo aaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Falha ao adicionar curso."));
        }
    }

    public void removerCurso() {
        if (idCursoRemover != null) {
            cursoRepository.deleteById(idCursoRemover);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso retirado", "Curso removido com sucesso."));
            init();
        }
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Part getImagem() {
        return imagem;
    }

    public void setImagem(Part imagem) {
        this.imagem = imagem;
    }

    public Integer getIdCursoRemover() {
        return idCursoRemover;
    }

    public void setIdCursoRemover(Integer idCursoRemover) {
        this.idCursoRemover = idCursoRemover;
    }
}