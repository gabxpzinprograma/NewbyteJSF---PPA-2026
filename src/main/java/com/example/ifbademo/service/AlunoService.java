package com.example.ifbademo.service;

import com.example.ifbademo.model.Aluno;
import com.example.ifbademo.repository.AlunoRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repo;

    public Optional<Aluno> login(String email, String senhaPlana) {
        Optional<Aluno> alunoOpt = repo.findByEmail(email);

        if (alunoOpt.isPresent()) {
            String hashDoBanco = alunoOpt.get().getSenha();
            if (hashDoBanco.startsWith("$2y$")) {
                hashDoBanco = "$2a$" + hashDoBanco.substring(4);
            }

            if (BCrypt.checkpw(senhaPlana, hashDoBanco)) {
                return alunoOpt;
            }
        }
        return Optional.empty();
    }
}