package com.gladson.seletivo.controller;

import com.gladson.seletivo.dto.LoginDTO;
import com.gladson.seletivo.dto.RefreshDTO;
import com.gladson.seletivo.dto.TokenResponseDTO;
import com.gladson.seletivo.model.Usuario;
import com.gladson.seletivo.service.TokenService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<?> login(@RequestBody LoginDTO dados) {
        // O Spring Security vai usar o AutenticacaoService (acima) para validar
        var authToken = new UsernamePasswordAuthenticationToken(dados.nome(), dados.senha());
        var authentication = authManager.authenticate(authToken);

        var usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.ok(new TokenResponseDTO(
                tokenService.gerarToken(usuario),
                tokenService.gerarRefreshToken(usuario)
        ));

    }

    @PostMapping("/refresh")
    @PermitAll
    public ResponseEntity<?> refresh(@RequestBody RefreshDTO dados) {
        var nomeUsuario = tokenService.validarToken(dados.refreshToken());

        if (nomeUsuario != null) {
            // Aqui você deve buscar o usuário novamente para gerar um novo Access Token
            // Idealmente, valide se o refresh token ainda é válido no seu critério
            return ResponseEntity.ok(new TokenResponseDTO(
                    "novo_access_token_aqui",
                    "novo_refresh_token_aqui"
            ));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}