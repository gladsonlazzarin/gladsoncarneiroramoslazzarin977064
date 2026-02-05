package com.gladson.seletivo.controller;

import com.gladson.seletivo.dto.AlbumCriarDTO;
import com.gladson.seletivo.model.Album;
import com.gladson.seletivo.model.enuns.TipoArtistaEnum;
import com.gladson.seletivo.model.projecoes.AlbumView;
import com.gladson.seletivo.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

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
    public ResponseEntity<Page<AlbumView>> listarAlbunsAsc(@Parameter(
            example = """
                            {
                              "page": 0,
                              "size": 1
                            }
                            """
    )Pageable pageable) {
        return ResponseEntity.ok(albumService.todosAsc(pageable));
    }
    @GetMapping("/todosalbunsdesc")
    public ResponseEntity<Page<AlbumView>> listarAlbunsDesc(@Parameter(
            example = """
                            {
                              "page": 0,
                              "size": 1
                            }
                            """
    )Pageable pageable) {
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

    @PutMapping("/album/{idAlbum}/artista/{idArtista}")
    public ResponseEntity<AlbumView> adicionarArtista(@PathVariable Long idAlbum, @PathVariable Long idArtista){
        return ResponseEntity.ok(albumService.adicionarArtista(idAlbum, idArtista));
    }

    @PostMapping(value = "/{idAlbum}/imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Adicionar imagem a um álbum", description = "Faz upload de uma imagem e associa ao álbum")
    public ResponseEntity<AlbumView> adicionarImagem(@PathVariable Long idAlbum, @Parameter(description = "Arquivo da imagem", required = true) @RequestParam("Imagem") MultipartFile file) throws Exception{
        return ResponseEntity.ok(albumService.adicionarImagemAoAlbum(idAlbum, file));
    }

    @GetMapping("/imagem/{uuid}/link")
    @Operation(summary = "Gerar link pré-assinado", description = "Gera um link temporário para acessar a imagem")
    public ResponseEntity<String> gerarLink(@PathVariable UUID uuid) throws Exception {
        String link = albumService.gerarLinkPreAssinado(uuid);
        return ResponseEntity.ok(link);
    }

    @GetMapping("/listaralbumportipoartista")
    public ResponseEntity<Page<AlbumView>> listarAlbunsPorTipoArtista(@RequestParam TipoArtistaEnum tipoArtistaEnum, @Parameter(
            example = """
                            {
                              "page": 0,
                              "size": 1
                            }
                            """
    )Pageable pageable) {
        return ResponseEntity.ok(albumService.listarAlbunsPorTipoArtista(tipoArtistaEnum, pageable));

    }

}
