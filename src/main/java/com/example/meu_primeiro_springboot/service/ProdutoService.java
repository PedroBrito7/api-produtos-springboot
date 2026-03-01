package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.Model.Produto;
import com.example.meu_primeiro_springboot.exceptions.RecursoNaoEncontradoException;
import com.example.meu_primeiro_springboot.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
private final ProdutoRepository produtoRepository;
    public ProdutoService (ProdutoRepository produtoRepository){this.produtoRepository = produtoRepository;}

    public List<Produto> listarProdutos(){
    return produtoRepository.findAll(); // findAll ja existe no jpa repository
    }

    public Produto buscarPorId(Long id){ // tirou optional daq pq ja ta tratando com exception
        return  produtoRepository.findById(id)
                .orElseThrow(()->new RecursoNaoEncontradoException("Produto com ID "+ id + " Não encontrado"));
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id){

        if(!produtoRepository.existsById(id)){ // agora quando for deletar um produto que n existe ele lanca essa exception
            throw  new RecursoNaoEncontradoException("Produto com ID "+ id + " Não encontrado");
        }
        produtoRepository.deleteById(id);
    }

}
