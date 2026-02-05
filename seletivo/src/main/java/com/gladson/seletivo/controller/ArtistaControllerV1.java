package com.gladson.seletivo.controller;

import com.gladson.seletivo.dto.ArtistaCriarDTO;
import com.gladson.seletivo.model.Artista;
import com.gladson.seletivo.model.projecoes.ArtistaView;
import com.gladson.seletivo.service.ArtistaService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/artistas")
public class ArtistaControllerV1 {

    @Autowired
    ArtistaService artistaService;

    @PostMapping
    public ResponseEntity<Artista> criar(@Valid @RequestBody ArtistaCriarDTO artistaCriarDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaService.criar(artistaCriarDTO));
    }
    @GetMapping("/todosartistasasc")
    public ResponseEntity<Page<ArtistaView>> listarArtistarsAsc(@Parameter(
            example = """
                            {
                              "page": 0,
                              "size": 1
                            }
                            """
    )Pageable pageable) {
        return ResponseEntity.ok(artistaService.todosAsc(pageable));
    }
    @GetMapping("/todosartistasdesc")
    public ResponseEntity<Page<ArtistaView>> listarArtistarsDesc(@Parameter(
            example = """
                            {
                              "page": 0,
                              "size": 1
                            }
                            """
    )Pageable pageable) {
        return ResponseEntity.ok(artistaService.todosDesc(pageable));
    }
    @GetMapping("/buscapornome")
    public ResponseEntity<Page<ArtistaView>> buscaPorNome(
            @RequestParam @NotBlank String nome,
            @Parameter(
                    description = "Ordenação no formato: campo,(asc|desc)",
                    example = """
                            {
                              "page": 0,
                              "size": 1,
                              "sort": "nomeArtista,desc"
                            }
                            """
            )Pageable pageable) {
        return ResponseEntity.ok(artistaService.buscaPorNome(nome, pageable));
    }

    @PutMapping("/atualizarnome/{idArtista}")
    public ResponseEntity<Artista> atualizarnome(@PathVariable Long idArtista, @Valid @RequestBody ArtistaCriarDTO artistaCriarDTO){
        return ResponseEntity.ok(artistaService.atualizanome(idArtista, artistaCriarDTO));
    }


}
