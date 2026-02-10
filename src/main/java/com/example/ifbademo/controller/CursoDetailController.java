package com.example.ifbademo.controller;

import com.example.ifbademo.model.Curso;
import com.example.ifbademo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CursoDetailController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping(value = "/get_curso.php", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getCurso(@RequestParam("id_curso") Integer idCurso) {
        Map<String, String> response = new HashMap<>();

        Optional<Curso> opt = cursoRepository.findById(idCurso);

        if (opt.isPresent()) {
            Curso curso = opt.get();
            if ("1".equals(curso.getStatus())) {
                response.put("titulo", curso.getTitulo());
                response.put("conteudo", curso.getConteudo());
                response.put("imagem", curso.getImagem());
                return response;
            }
        }
        response.put("conteudo", "Curso não encontrado ou não disponível.");
        return response;
    }
}