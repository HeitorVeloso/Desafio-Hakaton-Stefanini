package com.stefanini.hackathon.dto;

import com.stefanini.hackathon.model.Aluno;

public class AlunoDTO {

    private Long id;
    
    private String nome;

    private String matricula;

    private String cpf;

    private String email;

    private Long idTurma;
    
    private String turma;

    public AlunoDTO() {
    }

    public AlunoDTO(String nome, String matricula, String cpf, String email, Long idTurma) {
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
        this.idTurma = idTurma;
    }
    public AlunoDTO(Aluno aluno) {
        this.id=aluno.getId();
        this.nome = aluno.getNome();
        this.matricula = aluno.getMatricula();
        this.cpf = aluno.getDadosPessoais().getCpf();
        this.email = aluno.getDadosPessoais().getEmail();
        this.idTurma = aluno.getTurma().getId();
        this.turma = aluno.getTurma().getNome();
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }    
}
