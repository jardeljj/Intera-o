package br.senac.tads.dsw.comentarios;

import br.senac.tads.dsw.comentarios.produto.Produto;
import br.senac.tads.dsw.comentarios.produto.ProdutoRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class ComentariosApplication implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ComentariosApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // ADICIONA 2 PRODUTOS INICIAIS QUANDO O BANCO FOR CRIADO

        // CASO SEJA NECESSÁRIO REINICIAR O BANCO, APAGAR O ARQUIVO 
        // CONFIGURADO NO application.properties
        if (produtoRepository.count() == 0) {
            produtoRepository.save(new Produto("Bolo de chocolate", "Descrição do bolo de chocolate", new BigDecimal("75.99"), "/img/produto01.jpg"));
            produtoRepository.save(new Produto("Bolo de morango", "Descrição de bolo de morango", new BigDecimal("89.99"), "/img/produto02.jpg"));
        }
    }

}
