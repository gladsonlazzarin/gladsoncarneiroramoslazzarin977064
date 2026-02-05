package com.gladson.seletivo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gladson.seletivo.dto.ArtistaCriarDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_artista")
public class Artista implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_artista", nullable = false)
    private String nomeArtista;

    @JsonIgnore
    @ManyToMany(mappedBy = "artistas", fetch = FetchType.LAZY)
    private List<Album> albums;

    public Artista() {
    }

    public Artista(Long id, String nomeArtista, List<Album> albums) {
        this.id = id;
        this.nomeArtista = nomeArtista;
        this.albums = albums;
    }

    public Artista(ArtistaCriarDTO artistaCriarDTO) {
        this.nomeArtista = artistaCriarDTO.nomeArtista();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artista artista = (Artista) o;
        return Objects.equals(id, artista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
