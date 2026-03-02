package com.example.meu_primeiro_springboot.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.security.Signature;
import java.util.Date;

// classe responsavel por criar o token, validar o token e extrair o username
public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //garante a integridade e autenticidade do token.Ele se baseia no metodo HMAC com SHA-256, que é um algoritmo simétrico.
    private static final long EXPIRATION_TIME = 8640000; // 1 dia vai expirar, depende do produto

    public static String generateToken(String username) { //
        return Jwts.builder() // Inicia a construção do token utilizando o padrão Builder da biblioteca JJWT
                .setSubject(username)// Define o "assunto" do token, que geralmente é o identificador do usuário (neste caso, o username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Assina qual o tempo de execucao somando hora atual e o expiration ja estabelecido que é 864000 milesimos(1day)
                .signWith(key, SignatureAlgorithm.HS256) // escreve o token com essa assinatura
                .compact(); // compacta e finaliza a construção
    }

    public static String extractUsername(String token) { // Usando um builder pegando o corpo do token e o username que é o subject, para ser validado
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public static boolean validateToken(String token) { // vai validar o token que foi dado
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

}
