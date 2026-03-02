package com.example.meu_primeiro_springboot.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
// JwtAuthFilter intercepta O filtro de toda requisicao antes dela chegar nos Controllers -> transforma o token JWT em um user autenticado dentro do spring security(que é quem decide que pode acessar)
//pega o header
//extrai o token
//valida o token
//pega o username
//autentica no Spring
@Component
public class JwtAuthFilter extends OncePerRequestFilter { //

    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        } // esse if verifica se existe um cabecalho com authorization e verifica se comeca com Bearer que é o pdrao do jwt se nao tiver ele passa a req pro proximo filtro
        String token = authHeader.substring(7); // extraindo os sete primeiros digitos
        String username = JwtUtil.extractUsername(token); // extraindo name de dentro

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){ // Verifica se encontrou um usuário no token e se ainda não há ninguém autenticado nesta conexão.
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(JwtUtil.validateToken(token)){ // pega o metodo que valida o token e retorna so true or false (boolean)
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());// aqui é um objeto que spring entende como um user que ta logado, é meio que um crachá
                SecurityContextHolder.getContext().setAuthentication(authToken); // só define a autenticação
            }
            filterChain.doFilter(request,response); // seria meio que o "else" Após autenticar (ou não), a requisição precisa continuar seu caminho para chegar ao Controller.
        }
    }
}
