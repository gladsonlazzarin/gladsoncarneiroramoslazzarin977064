package com.gladson.seletivo.model.projecoes;

import com.gladson.seletivo.model.Album;
import com.gladson.seletivo.model.enuns.TipoArtistaEnum;

import java.util.List;

public interface ArtistaView {
    Long getId();
    String getNomeArtista();
    List<Album> getAlbums();
    TipoArtistaEnum getTipo();

}
