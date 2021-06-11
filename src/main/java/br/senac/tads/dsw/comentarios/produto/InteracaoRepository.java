/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.comentarios.produto;

import java.util.List;

/**
 *
 * @author Jardel
 */
public interface InteracaoRepository {
    
    List<Interacao> findAll();
    
    Interacao findById(Long id);
    
    Interacao save(Interacao cat);
    
}
