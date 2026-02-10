package com.example.ifbademo.service;

import com.example.ifbademo.model.Professor;
import com.example.ifbademo.repository.ProfessorRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repo;

    public Optional<Professor> login(String email, String senhaPlana) {
        Optional<Professor> profOpt = repo.findByEmail(email);

        if (profOpt.isPresent()) {
            String hash = profOpt.get().getSenha();

            if (hash != null && hash.startsWith("$2y$")) {
                hash = "$2a$" + hash.substring(4);
            }

            if (hash != null && BCrypt.checkpw(senhaPlana, hash)) {
                return profOpt;
            }
        }
        return Optional.empty();
    }
}