package com.stefanini.hackathon.controller;

import com.stefanini.hackathon.dto.AlunoDTO;
import com.stefanini.hackathon.exception.AlunoNotFoundException;
import com.stefanini.hackathon.exception.TurmaNotFoundException;
import com.stefanini.hackathon.model.Aluno;
import com.stefanini.hackathon.mapper.AlunoDTOService;
import com.stefanini.hackathon.model.DadosPessoais;
import com.stefanini.hackathon.model.Turma;
import com.stefanini.hackathon.service.AlunoService;
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
public class AlunoController {

    private final AlunoService alunoService;
    private final AlunoDTOService alunoDTOService;

    @Autowired
    public AlunoController(AlunoService alunoService, AlunoDTOService alunoDTOService) {
        this.alunoService = alunoService;
        this.alunoDTOService = alunoDTOService;
    }

    /*@RequestMapping(path = "/aluno")
    public ModelAndView loadHtml() {

        ModelAndView mv = new ModelAndView("aluno");
        AlunoDTO alunoDTO = new AlunoDTO();
        mv.addObject("alunoDTO", alunoDTO);        
        
        return mv;
    }

    @PostMapping(value = "/aluno")
    public String saveAluno(AlunoDTO aluno) throws TurmaNotFoundException {
         
        Aluno newAluno = alunoDTOService.mapAluno(aluno);

        alunoService.save(newAluno);

        return "redirect:/aluno";
    }*/
    
    @GetMapping("/aluno")
    public String home(Model model) {   
        /*Aluno aluno = new Aluno();
        aluno.setId(Long.parseLong("3"));
        aluno.setNome("Joao");
        aluno.setMatricula("123");
        DadosPessoais dadosPessoais = new DadosPessoais();
        dadosPessoais.setCpf("11406578606");
        dadosPessoais.setId(Long.parseLong("3"));
        dadosPessoais.setEmail("a@gmail.com");
        aluno.setDadosPessoais(dadosPessoais);
        Turma turma = new Turma();
        turma.setId(Long.parseLong("1"));
        aluno.setTurma(turma);
        alunoService.save(aluno);*/
    	List<AlunoDTO> listaAlunoDTO = alunoDTOService.mapTodosAluno(alunoService.findAllAlunos());    	
        model.addAttribute("listaAlunos", listaAlunoDTO);
        return "aluno";
    }

    
    
    @GetMapping("/formAluno")
    public String alunosForm(AlunoDTO alunoDTO) {    	   
        return "addAlunoForm";
    }

    // Adiciona novo aluno
    @PostMapping("/addAluno")
    public String novo(@Valid AlunoDTO alunoDTO, BindingResult result) {

        if (result.hasFieldErrors()) {
            return "redirect:/formAluno";
        }

        Aluno newAluno;
        try {
            newAluno = alunoDTOService.mapAluno(alunoDTO);
            alunoService.save(newAluno);
        } catch (TurmaNotFoundException ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return "redirect:/aluno";
    }

    // Acessa o formulario de edição
    @GetMapping("formAluno/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {
        System.out.println("entrou update");
        AlunoDTO alunoDTO;
        Aluno aluno;
        try {
            aluno = alunoService.findById(id);
            alunoDTO=new AlunoDTO(aluno);
            model.addAttribute("alunoDTO", alunoDTO);
        } catch (AlunoNotFoundException ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return "atualizaAluno";
    }

    // Atualiza aluno
    @PostMapping("updateAluno/{id}")
    public String alterarProduto(@Valid AlunoDTO alunoDTO, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return "redirect:/formAluno";
        }
        
        Aluno newAluno;
        try {
            newAluno = alunoDTOService.mapAluno(alunoDTO);
            newAluno.setId(id);
            alunoService.save(newAluno);
        } catch (TurmaNotFoundException ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return "redirect:/aluno";
    }

    @GetMapping("deleteAluno/{id}")
    @CacheEvict(value = "alunos", allEntries = true)
    public String delete(@PathVariable(name = "id") Long id, Model model) {

        Aluno aluno;
        
        try {
            aluno = alunoService.findById(id);
            alunoService.delete(aluno);
        } catch (AlunoNotFoundException ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/aluno";
    }
}
