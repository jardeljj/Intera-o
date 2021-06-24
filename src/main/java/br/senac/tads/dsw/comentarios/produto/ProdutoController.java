/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.comentarios.produto;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ModelAndView listar() {
        List<Produto> produtos = repository.findAll();
        return new ModelAndView("produtos/lista").addObject("items", produtos);
    }

    @GetMapping("/{id}")
    public ModelAndView mostrarDetalhes(@PathVariable("id") Integer id, RedirectAttributes redirAttr) {
        Optional<Produto> optProduto = repository.findById(id);
        if (optProduto.isEmpty()) {
            redirAttr.addFlashAttribute("msgErro", "Produto com ID " + id + " não encontrado.");
            return new ModelAndView("redirect:/produtos");
        }

        ProdutoInteracao pi = new ProdutoInteracao(optProduto.get(), new Interacao());

        return new ModelAndView("produtos/detalhes")
                .addObject("item", pi);
    }

    @RequestMapping(name = "/saveInteracao", method = RequestMethod.POST)
    public ResponseEntity<?> saveInteracao(@RequestBody ProdutoInteracao pi) {
        Interacao i = pi.getInteracao();
        //return new ModelAndView("redirect:/produtos/" + pi.getProduto().getId());
        return new ResponseEntity<>(new ModelAndView("redirect:/produtos/" + pi.getProduto().getId()), HttpStatus.OK);
    }

}
