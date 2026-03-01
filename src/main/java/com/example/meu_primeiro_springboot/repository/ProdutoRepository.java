package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // dentro do jparepositorio, ja tem o save, delete ja tudo pre definido
public interface ProdutoRepository extends JpaRepository<Produto,Long> { // Id é long e o primeiro é sempre oq vamos trabalhar


}
