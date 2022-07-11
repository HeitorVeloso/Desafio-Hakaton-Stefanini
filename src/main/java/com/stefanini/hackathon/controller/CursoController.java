package com.stefanini.hackathon.controller;

import com.stefanini.hackathon.dto.CursoDTO;
import com.stefanini.hackathon.exception.CursoNotFoundException;
import com.stefanini.hackathon.mapper.CursoDTOService;
import com.stefanini.hackathon.model.Curso;


import com.stefanini.hackathon.service.CursoService;
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
public class CursoController {

    private final CursoService cursoService;
    private final CursoDTOService cursoDTOService;

    @Autowired
    public CursoController(CursoService cursoService, CursoDTOService cursoDTOService) {
        this.cursoService = cursoService;
        this.cursoDTOService = cursoDTOService;
    }

    @GetMapping("/curso")
    public String home(Model model) {   
        
    	List<CursoDTO> listaCursoDTO = cursoDTOService.mapTodosCurso(cursoService.findAllCursos());    	
        model.addAttribute("listaCursos", listaCursoDTO);
        return "curso";
    }

    
    
    @GetMapping("/formCurso")
    public String cursosForm(CursoDTO cursoDTO) {    	   
        return "addCursoForm";
    }

    // Adiciona novo curso
    @PostMapping("/addCurso")
    public String novo(@Valid CursoDTO cursoDTO, BindingResult result) {

        if (result.hasFieldErrors()) {
            return "redirect:/formCurso";
        }

        Curso newCurso;
        try {
            newCurso = cursoDTOService.mapCurso(cursoDTO);
            cursoService.save(newCurso);
        } catch (CursoNotFoundException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/curso";
    }

    // Acessa o formulario de edição
    @GetMapping("formCurso/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {
        System.out.println("entrou update");
        CursoDTO cursoDTO;
        Curso curso;
        try {
            curso = cursoService.findById(id);
            cursoDTO=new CursoDTO(curso);
            model.addAttribute("cursoDTO", cursoDTO);
        } catch (CursoNotFoundException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return "atualizaCurso";
    }

    // Atualiza curso
    @PostMapping("updateCurso/{id}")
    public String alterarProduto(@Valid CursoDTO cursoDTO, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return "redirect:/formCurso";
        }
        
        Curso newCurso;
        try {
            newCurso = cursoDTOService.mapCurso(cursoDTO);
            newCurso.setId(id);
            cursoService.save(newCurso);
        } catch (CursoNotFoundException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/curso";
    }

    @GetMapping("deleteCurso/{id}")
    @CacheEvict(value = "cursos", allEntries = true)
    public String delete(@PathVariable(name = "id") Long id, Model model) {

        Curso curso;
        
        try {
            curso = cursoService.findById(id);
            cursoService.delete(curso);
        } catch (CursoNotFoundException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/curso";
    }

}
