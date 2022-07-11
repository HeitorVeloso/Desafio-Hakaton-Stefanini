package com.stefanini.hackathon.service;

import com.stefanini.hackathon.exception.CursoNotFoundException;
import com.stefanini.hackathon.model.Curso;
import com.stefanini.hackathon.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> findAllCursos(){
        return cursoRepository.findAll();
    }

    public Curso findById(Long id) throws CursoNotFoundException {
        return cursoRepository.findById(id)
                .orElseThrow(()-> new CursoNotFoundException(id));
    }

    public Curso save(Curso curso){
        return cursoRepository.save(curso);
    }
    
    public void delete(Curso curso){
        cursoRepository.delete(curso);
    }

}
