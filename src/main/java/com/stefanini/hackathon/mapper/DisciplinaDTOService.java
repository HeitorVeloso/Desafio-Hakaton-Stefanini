package com.stefanini.hackathon.mapper;

import com.stefanini.hackathon.dto.CursoDTO;
import com.stefanini.hackathon.dto.DisciplinaDTO;
import com.stefanini.hackathon.exception.CursoNotFoundException;
import com.stefanini.hackathon.exception.TurmaNotFoundException;
import com.stefanini.hackathon.model.Curso;
import com.stefanini.hackathon.model.Disciplina;
import com.stefanini.hackathon.model.Turma;
import com.stefanini.hackathon.service.CursoService;
import com.stefanini.hackathon.service.TurmaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaDTOService {

    private final TurmaService turmaService;
    private final CursoService cursoService;

    @Autowired
    public DisciplinaDTOService(TurmaService turmaService, CursoService cursoService) {
        this.turmaService = turmaService;
        this.cursoService = cursoService;
    }


    public Disciplina mapDisciplina(DisciplinaDTO disciplina) throws TurmaNotFoundException, CursoNotFoundException {

        Turma turma = turmaService.findById(disciplina.getIdTurma());
        Curso curso = cursoService.findById(disciplina.getIdCurso());

        Disciplina newDisciplina = new Disciplina(null,
                disciplina.getNome(),
                disciplina.getCodigo(),
                disciplina.getConteudoProgramatico(),
                disciplina.getNumeroCreditos(),
                disciplina.getTotalHoras(),
                turma,
                curso);
        return newDisciplina;
    }
    
    public List<DisciplinaDTO> mapTodosDisciplina(List<Disciplina> listaDisciplina) {

        List<DisciplinaDTO> listaDisciplinaDTO = new ArrayList();
        for (Disciplina disciplina : listaDisciplina) {
            DisciplinaDTO disciplinaDTO = new DisciplinaDTO(disciplina);
            listaDisciplinaDTO.add(disciplinaDTO);
        }
        
        return listaDisciplinaDTO;
    } 
}
