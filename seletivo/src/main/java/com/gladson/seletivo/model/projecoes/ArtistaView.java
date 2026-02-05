package com.gladson.seletivo.model.projecoes;

import com.gladson.seletivo.model.Album;

import java.util.List;

public interface ArtistaView {
    Long getId();
    String getNomeArtista();
    List<Album> getAlbums();

}
