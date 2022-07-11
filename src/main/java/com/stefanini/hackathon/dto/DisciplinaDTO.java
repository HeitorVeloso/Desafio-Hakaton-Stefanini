package com.stefanini.hackathon.dto;

import com.stefanini.hackathon.model.Curso;
import com.stefanini.hackathon.model.Disciplina;
import com.stefanini.hackathon.model.Turma;

public class DisciplinaDTO {

    private Long id;

    private String nome;

    private String codigo;

    private String conteudoProgramatico;

    private Integer numeroCreditos = 0;

    private Integer totalHoras;

    private Long idTurma;

    private Long idCurso;

    public DisciplinaDTO() {
    }

    public DisciplinaDTO(String nome, String codigo, String conteudoProgramatico, Integer numeroCreditos, Integer totalHoras, Long turma, Long curso) {
        this.nome = nome;
        this.codigo = codigo;
        this.conteudoProgramatico = conteudoProgramatico;
        this.numeroCreditos = numeroCreditos;
        this.totalHoras = totalHoras;
        this.idTurma = turma;
        this.idCurso = curso;
    }
    
    public DisciplinaDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.codigo = disciplina.getCodigo();        
        this.conteudoProgramatico = disciplina.getConteudoProgramatico();
        this.numeroCreditos = disciplina.getNumeroCreditos();
        this.totalHoras = disciplina.getTotalHoras();
        this.idTurma = disciplina.getTurma().getId();
        this.idCurso = disciplina.getCurso().getId();
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getConteudoProgramatico() {
        return conteudoProgramatico;
    }

    public void setConteudoProgramatico(String conteudoProgramatico) {
        this.conteudoProgramatico = conteudoProgramatico;
    }

    public Integer getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(Integer numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public Integer getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(Integer totalHoras) {
        this.totalHoras = totalHoras;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }
}
