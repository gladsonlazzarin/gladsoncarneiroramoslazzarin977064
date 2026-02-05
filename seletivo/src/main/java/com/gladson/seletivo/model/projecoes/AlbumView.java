package com.gladson.seletivo.model.projecoes;

import com.gladson.seletivo.model.Arquivo;
import com.gladson.seletivo.model.Artista;

import java.util.List;

public interface AlbumView {
    Long getId();
    String getNomeAlbum();
    List<Arquivo> getImagens();
    List<Artista> getArtistas();
}
