package com.stefanini.hackathon.dto;

import com.stefanini.hackathon.model.Curso;

public class CursoDTO {
    
    private Long id;
    private String name;

    private Integer totalGrade;

    public CursoDTO() {
    }

    public CursoDTO(String name, Integer totalGrade) {
        this.name = name;
        this.totalGrade = totalGrade;
    }
    
    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.name = curso.getName();
        this.totalGrade = curso.getTotalGrade();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Integer totalGrade) {
        this.totalGrade = totalGrade;
    }
}
