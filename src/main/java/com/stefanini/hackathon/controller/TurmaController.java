package com.stefanini.hackathon.controller;

import com.stefanini.hackathon.dto.TurmaDTO;
import com.stefanini.hackathon.exception.CursoNotFoundException;
import com.stefanini.hackathon.exception.TurmaNotFoundException;
import com.stefanini.hackathon.mapper.TurmaDTOService;
import com.stefanini.hackathon.model.Turma;
import com.stefanini.hackathon.service.TurmaService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TurmaController {

    private final TurmaService turmaService;
    private final TurmaDTOService turmaDTOService;

    @Autowired
    public TurmaController(TurmaService turmaService, TurmaDTOService turmaDTOService) {
        this.turmaService = turmaService;
        this.turmaDTOService = turmaDTOService;
    }

    @GetMapping("/turma")
    public String home(Model model) {   
        
    	List<TurmaDTO> listaTurmaDTO = turmaDTOService.mapTodosTurma(turmaService.findAllTurmas());    	
        model.addAttribute("listaTurmas", listaTurmaDTO);
        return "turma";
    }

    
    
    @GetMapping("/formTurma")
    public String turmasForm(TurmaDTO turmaDTO) {    	   
        return "addTurmaForm";
    }

    // Adiciona novo turma
    @PostMapping("/addTurma")
    public String novo(@Valid TurmaDTO turmaDTO, BindingResult result) {

        if (result.hasFieldErrors()) {
            return "redirect:/formTurma";
        }

        Turma newTurma;
        newTurma = turmaDTOService.mapTurma(turmaDTO);
        turmaService.save(newTurma);
        

        return "redirect:/turma";
    }

    // Acessa o formulario de edição
    @GetMapping("formTurma/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {
        System.out.println("entrou update");
        TurmaDTO turmaDTO;
        Turma turma;
        try {
            turma = turmaService.findById(id);
            turmaDTO=new TurmaDTO(turma);
            model.addAttribute("turmaDTO", turmaDTO);
        } catch (TurmaNotFoundException ex) {
            Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return "atualizaTurma";
    }

    // Atualiza turma
    @PostMapping("updateTurma/{id}")
    public String alterarProduto(@Valid TurmaDTO turmaDTO, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return "redirect:/formTurma";
        }
        
        Turma newTurma;
        newTurma = turmaDTOService.mapTurma(turmaDTO);
        newTurma.setId(id);
        turmaService.save(newTurma);

        
        return "redirect:/turma";
    }

    @GetMapping("deleteTurma/{id}")
    @CacheEvict(value = "turmas", allEntries = true)
    public String delete(@PathVariable(name = "id") Long id, Model model) {

        Turma turma;
        
        try {
            turma = turmaService.findById(id);
            turmaService.delete(turma);
        } catch (TurmaNotFoundException ex) {
            Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/turma";
    }

}
