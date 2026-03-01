package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.repository.MensagemRepository;
import org.springframework.stereotype.Service;

@Service // Anotacao para anotar que aqui tem a regra de negocio da aplicação
public class MensagemService {
    private final MensagemRepository mensagemRepository; // N é instanciando manualmente, ou seja o spring ja injeta no construtor automaticamente
    public MensagemService(MensagemRepository mensagemRepository){this.mensagemRepository = mensagemRepository;}

    public String obterMensagem(){
        return mensagemRepository.obterMensagem();
    }

}
