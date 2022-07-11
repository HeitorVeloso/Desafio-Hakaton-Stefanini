package com.stefanini.hackathon.controller;

import com.stefanini.hackathon.dto.DisciplinaDTO;
import com.stefanini.hackathon.dto.DisciplinaDTO;
import com.stefanini.hackathon.exception.CursoNotFoundException;
import com.stefanini.hackathon.exception.DisciplinaNotFoundException;
import com.stefanini.hackathon.exception.TurmaNotFoundException;
import com.stefanini.hackathon.mapper.DisciplinaDTOService;
import com.stefanini.hackathon.model.Disciplina;
import com.stefanini.hackathon.model.Disciplina;
import com.stefanini.hackathon.service.DisciplinaService;
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
public class DisciplinaController {

    private final DisciplinaService disciplinaService;
    private final DisciplinaDTOService disciplinaDTOService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService, DisciplinaDTOService disciplinaDTOService) {
        this.disciplinaService = disciplinaService;
        this.disciplinaDTOService = disciplinaDTOService;
    }

    @GetMapping("/disciplina")
    public String home(Model model) {   
        
    	List<DisciplinaDTO> listaDisciplinaDTO = disciplinaDTOService.mapTodosDisciplina(disciplinaService.findAllDisciplinas());    	
        model.addAttribute("listaDisciplinas", listaDisciplinaDTO);
        return "disciplina";
    }

    
    
    @GetMapping("/formDisciplina")
    public String disciplinasForm(DisciplinaDTO disciplinaDTO) {    	   
        return "addDisciplinaForm";
    }

    // Adiciona novo disciplina
    @PostMapping("/addDisciplina")
    public String novo(@Valid DisciplinaDTO disciplinaDTO, BindingResult result) {

        if (result.hasFieldErrors()) {
            return "redirect:/formDisciplina";
        }

        Disciplina newDisciplina;
        try {
            newDisciplina = disciplinaDTOService.mapDisciplina(disciplinaDTO);
            disciplinaService.save(newDisciplina);
        } catch (TurmaNotFoundException ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CursoNotFoundException ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/disciplina";
    }

    // Acessa o formulario de edição
    @GetMapping("formDisciplina/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {
        System.out.println("entrou update");
        DisciplinaDTO disciplinaDTO;
        Disciplina disciplina;
        try {
            disciplina = disciplinaService.findById(id);
            disciplinaDTO=new DisciplinaDTO(disciplina);
            model.addAttribute("disciplinaDTO", disciplinaDTO);
        } catch (DisciplinaNotFoundException ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return "atualizaDisciplina";
    }

    // Atualiza disciplina
    @PostMapping("updateDisciplina/{id}")
    public String alterarProduto(@Valid DisciplinaDTO disciplinaDTO, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return "redirect:/formDisciplina";
        }
        
        Disciplina newDisciplina;
        try {
            newDisciplina = disciplinaDTOService.mapDisciplina(disciplinaDTO);
            newDisciplina.setId(id);
            disciplinaService.save(newDisciplina);
        } catch (TurmaNotFoundException ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CursoNotFoundException ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/disciplina";
    }

    @GetMapping("deleteDisciplina/{id}")
    @CacheEvict(value = "disciplinas", allEntries = true)
    public String delete(@PathVariable(name = "id") Long id, Model model) {

        Disciplina disciplina;
        
        try {
            disciplina = disciplinaService.findById(id);
            disciplinaService.delete(disciplina);
        } catch (DisciplinaNotFoundException ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/disciplina";
    }

}
