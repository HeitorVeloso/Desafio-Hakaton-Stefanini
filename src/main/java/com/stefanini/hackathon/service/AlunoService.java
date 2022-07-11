package com.stefanini.hackathon.service;

import com.stefanini.hackathon.exception.AlunoNotFoundException;
import com.stefanini.hackathon.model.Aluno;
import com.stefanini.hackathon.repository.AlunoRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> findAllAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) throws AlunoNotFoundException {
        return alunoRepository.findById(id)
                .orElseThrow(()-> new AlunoNotFoundException(id));
    }

    public Aluno save(Aluno aluno){
        return alunoRepository.save(aluno);
    }
    
    public void delete(Aluno aluno){
        alunoRepository.delete(aluno);
    }

}
