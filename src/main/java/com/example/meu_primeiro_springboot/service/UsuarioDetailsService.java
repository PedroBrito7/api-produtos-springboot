package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.Model.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.meu_primeiro_springboot.repository.UsuarioRepository;
// Classe que busca o usuario no banco
@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // Spring chama o metodo
        Usuario usuario = usuarioRepository.findByUsername(username) // Ele busca o usuário no banco.
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")); // caso n ache ele retorna n encontrado

        return User.builder() //
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER") // implementa que o cargo dele ele só tem acesso a rotas próprias de user, não vai poder ter acesso de admin etc
                .build();
    }

}