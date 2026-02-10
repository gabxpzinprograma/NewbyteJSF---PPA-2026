package com.example.ifbademo.service;

import com.example.ifbademo.model.Aluno;
import com.example.ifbademo.repository.AlunoRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    @Autowired
    private AlunoRepository alunoRepository;

    public String cadastrar(String nome, String email, String senha, String confirmarSenha) {
        
        if (!senha.equals(confirmarSenha)) {
            return "As senhas não coincidem.";
        }

        if (alunoRepository.findByEmail(email).isPresent()) {
            return "Este e-mail já está cadastrado.";
        }

        String hash = BCrypt.hashpw(senha, BCrypt.gensalt());

        Aluno novo = new Aluno();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setSenha(hash);

        alunoRepository.save(novo);
        return "Cadastro realizado com sucesso!";
    }
}