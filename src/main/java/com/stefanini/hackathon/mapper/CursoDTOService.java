package com.stefanini.hackathon.mapper;

import com.stefanini.hackathon.dto.CursoDTO;
import com.stefanini.hackathon.exception.CursoNotFoundException;
import com.stefanini.hackathon.model.Curso;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CursoDTOService {

     public Curso mapCurso(CursoDTO curso) throws CursoNotFoundException {

        Curso newCurso = new Curso(null, curso.getName(), null, curso.getTotalGrade());
        return newCurso;
    }
       
    
    public List<CursoDTO> mapTodosCurso(List<Curso> listaCurso) {

        List<CursoDTO> listaCursoDTO = new ArrayList();
        for (Curso curso : listaCurso) {
            CursoDTO cursoDTO = new CursoDTO(curso);
            listaCursoDTO.add(cursoDTO);
        }
        
        return listaCursoDTO;
    } 
}
