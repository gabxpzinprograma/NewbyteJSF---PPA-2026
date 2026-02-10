package com.example.ifbademo.model;

import java.io.Serializable;

public class AlunoCursoId implements Serializable {
    private Integer alunoId;
    private Integer cursoId;

    public AlunoCursoId() {
    }

    public AlunoCursoId(Integer alunoId, Integer cursoId) {
        this.alunoId = alunoId;
        this.cursoId = cursoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AlunoCursoId that = (AlunoCursoId) o;
        return alunoId.equals(that.alunoId) && cursoId.equals(that.cursoId);
    }

    @Override
    public int hashCode() {
        return alunoId.hashCode() + cursoId.hashCode();
    }
}