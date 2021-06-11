/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.senac.tads.dsw.comentarios.produto.Interacao;
import br.senac.tads.dsw.comentarios.produto.InteracaoRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jardel
 */
@Repository
public class InteracaoRepositoryJpa implements InteracaoRepository{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Interacao> findAll() {
        TypedQuery<Interacao> jpqlQuery
                = em.createQuery("SELECT p FROM Interacao p", Interacao.class); 
        return jpqlQuery.getResultList();
    }

    @Override
    public Interacao findById(Long id) {
        
        TypedQuery<Interacao> jpqlQuery = 
                em.createQuery("SELECT C FROM Interacao c WHERE c.id = :idCat", Interacao.class);
        jpqlQuery.setParameter("idCat", id);
        Interacao c = jpqlQuery.getSingleResult();
        return c;
    }

    @Override
    @Transactional
    public Interacao save(Interacao cat) {
        if(cat.getId() == null){
            em.persist(cat);
        }else{
             cat = em.merge(cat);
        }
        return cat;
    }
    
}
