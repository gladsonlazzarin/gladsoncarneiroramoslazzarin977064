package com.gladson.seletivo.controller;

import com.gladson.seletivo.dto.AlbumCriarDTO;
import com.gladson.seletivo.model.Album;
import com.gladson.seletivo.model.projecoes.AlbumView;
import com.gladson.seletivo.service.AlbumService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/albuns")
public class AlbumControllerV1 {

    @Autowired
    AlbumService albumService;

    @PostMapping
    public ResponseEntity<Album> criar(@Valid @RequestBody AlbumCriarDTO albumCriarDTO){
        return ResponseEntity.ok(albumService.criar(albumCriarDTO));
    }
    @GetMapping("/todosalbunsasc")
    public ResponseEntity<Page<AlbumView>> listarAlbunsAsc(Pageable pageable){
        return ResponseEntity.ok(albumService.todosAsc(pageable));
    }
    @GetMapping("/todosalbunsdesc")
    public ResponseEntity<Page<AlbumView>> listarAlbunsDesc(Pageable pageable){
        return ResponseEntity.ok(albumService.todosDesc(pageable));
    }
    @GetMapping("/buscapornome")
    public ResponseEntity<Page<AlbumView>> buscaPorNome(
            @RequestParam @NotBlank String nome,
            @Parameter(
                    description = "Ordenação no formato: campo,(asc|desc)",
                    example = """
                            {
                              "page": 0,
                              "size": 1,
                              "sort": "nomeAlbum,desc"
                            }
                            """
            )Pageable pageable) {
        return ResponseEntity.ok(albumService.buscaPorNome(nome, pageable));
    }

    @PutMapping("/atualizarnome/{idAlbum}")
    public ResponseEntity<Album> atualizarnome(@PathVariable Long idAlbum, @Valid @RequestBody AlbumCriarDTO albumCriarDTO){
        return ResponseEntity.ok(albumService.atualizanome(idAlbum, albumCriarDTO));
    }

}
