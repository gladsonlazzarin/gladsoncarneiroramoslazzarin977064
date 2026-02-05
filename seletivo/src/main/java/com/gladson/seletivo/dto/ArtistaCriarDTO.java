package com.gladson.seletivo.dto;

import jakarta.validation.constraints.NotBlank;

public record ArtistaCriarDTO(@NotBlank String nomeArtista) {
}
