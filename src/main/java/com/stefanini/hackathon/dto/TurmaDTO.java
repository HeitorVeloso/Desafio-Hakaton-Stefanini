package com.stefanini.hackathon.dto;

import com.stefanini.hackathon.model.Turma;

public class TurmaDTO {

    private Long id;
    private String nome;

    public TurmaDTO() {
    }

    public TurmaDTO(String nome) {
        this.nome = nome;
    }
    
    public TurmaDTO(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
