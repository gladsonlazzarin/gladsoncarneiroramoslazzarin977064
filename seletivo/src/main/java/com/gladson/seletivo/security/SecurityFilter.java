package com.gladson.seletivo.security;

import com.gladson.seletivo.respository.UsuarioRepository;
import com.gladson.seletivo.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    @Lazy // <--- Add this annotation here
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        var token = recuperarToken(request);

        if (token != null) {
            var nomeUsuario = tokenService.validarToken(token);
            if (nomeUsuario != null) {
                var usuario = usuarioRepository.findByNome(nomeUsuario);

                // Cria a autenticação com as roles do usuário
                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities());

                // Define o usuário como autenticado para esta requisição
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }
}