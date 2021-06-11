/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.comentarios.produto;

import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Jardel
 */
public class InteracaoController {
    
    private final InteracaoRepository repository;

    public InteracaoController(InteracaoRepository repository) {
        this.repository = (InteracaoRepository) (ProdutoRepository) repository;
    }
    
    @GetMapping
    public ModelAndView listar() {
        List<Interacao> resultados = repository.findAll();
        return new ModelAndView("produtos/1").addObject("comentario", resultados);
    }
    
    @GetMapping("/proutos/1")
    public ModelAndView adicionarNovo() {
        return new ModelAndView("/produtos/detalhes").addObject("interacao", new Interacao());
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute @Valid Interacao cat, BindingResult bindingResult,
            RedirectAttributes redirAttr) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/detalhes");
        }
        repository.save(cat);
        redirAttr.addFlashAttribute("msgSucesso",
                "Interaçao " + cat.getId()+ " salva com sucesso");
        return new ModelAndView("redirect:/interacao");
    }
    
}
