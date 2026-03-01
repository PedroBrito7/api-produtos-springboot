package com.example.meu_primeiro_springboot.controller;

import com.example.meu_primeiro_springboot.Model.Produto;
import com.example.meu_primeiro_springboot.exceptions.RecursoNaoEncontradoException;
import com.example.meu_primeiro_springboot.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// ResponseEntity é uma classe do Spring Framework que representa uma resposta HTTP completa, permitindo o controle total sobre o status da resposta, os cabeçalhos (headers) e o corpo (body)
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
        private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    @GetMapping // se eu nao colocar nada ela puxa o padrão que seria api/produtos do requestMaping
    public List<Produto> listarProdutos(){ // ele vai puxar do banco de dados. Vai puxar a classe service que criamos toda a funcao
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> buscarProduto(@PathVariable Long id){ // <?> ele funciona como se fosse um if ou else. Isso é generics
            Produto produto = produtoService.buscarPorId(id);
            return ResponseEntity.ok(produto);

    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto){ // @RequestBody: Pegue o JSON que veio no corpo da requisição e transforme em um objeto Java.
        return produtoService.salvarProduto(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
