package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.Model.Usuario;
import com.example.meu_primeiro_springboot.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
// classe q
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository= usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // o bcrypt ele armazena as senhas cripto
    }
    public Usuario registrarUsuario(String username,String password){
        String senhaCriptografada = passwordEncoder.encode(password); // vai jogar a senha criptografada
        Usuario usuario = new Usuario(username,password);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }
}
