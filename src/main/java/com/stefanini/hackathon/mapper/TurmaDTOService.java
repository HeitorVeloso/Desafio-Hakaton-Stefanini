package com.stefanini.hackathon.mapper;

import com.stefanini.hackathon.dto.TurmaDTO;
import com.stefanini.hackathon.exception.TurmaNotFoundException;
import com.stefanini.hackathon.model.DadosPessoais;
import com.stefanini.hackathon.model.Turma;
import com.stefanini.hackathon.service.DadosPessoaisService;
import com.stefanini.hackathon.service.TurmaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaDTOService {

     public Turma mapTurma(TurmaDTO turma){

        Turma newTurma = new Turma(null, turma.getNome(), null, null);
        return newTurma;
    }
    private final TurmaService turmaService;
    private final DadosPessoaisService dadosPessoaisService;

    @Autowired
    public TurmaDTOService(TurmaService turmaService, DadosPessoaisService dadosPessoaisService) {
        this.turmaService = turmaService;
        this.dadosPessoaisService = dadosPessoaisService;
    }
    
    public List<TurmaDTO> mapTodosTurma(List<Turma> listaTurma) {

        List<TurmaDTO> listaTurmaDTO = new ArrayList();
        for (Turma turma : listaTurma) {
            TurmaDTO turmaDTO = new TurmaDTO(turma);
            listaTurmaDTO.add(turmaDTO);
        }
        
        return listaTurmaDTO;
    }
}
