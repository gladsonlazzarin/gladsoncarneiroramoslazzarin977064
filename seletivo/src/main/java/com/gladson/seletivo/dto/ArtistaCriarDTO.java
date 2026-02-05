package com.gladson.seletivo.dto;

import com.gladson.seletivo.model.enuns.TipoArtistaEnum;
import jakarta.validation.constraints.NotBlank;

public record ArtistaCriarDTO(@NotBlank String nomeArtista, TipoArtistaEnum tipo) {
}
