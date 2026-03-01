package com.example.meu_primeiro_springboot.Model;

import jakarta.persistence.*;
// usando anotacao do jpa, para manipular um banco relacional


@Entity // primeira anotação, indica que essa classe é uma identidade jpa
@Table(name="produtos") // definindo nome da tabela do banco sql
public class Produto {
    @Id // indica que isso vai ser um id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// estudar melhor sobre anotações e como são gerados o id, porem aq indica que vai ser uma chave primaria que vai ser gerada automaticamente
    private Long id;

    private String nome;
    private Double preco;

    public Produto(){}
    public Produto(String nome, Double preco){
        this.nome = nome;
        this.preco = preco; // rever encapsulamento no canal matheus Leandro caso tiver dúvida sobre
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
