package com.gladson.seletivo.dto;

import jakarta.validation.constraints.NotBlank;

public record AlbumCriarDTO(@NotBlank String nomeAlbum) {
}
