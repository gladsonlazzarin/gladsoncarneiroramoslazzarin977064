package com.gladson.seletivo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gladson.seletivo.dto.AlbumCriarDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_album")
public class Album implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_album", nullable = false)
    private String nomeAlbum;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private List<Arquivo> imagens;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_artista_album",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artistas;

    public Album() {
    }

    public Album(Long id, String nomeAlbum, List<Arquivo> imagens, List<Artista> artistas) {
        this.id = id;
        this.nomeAlbum = nomeAlbum;
        this.imagens = imagens;
        this.artistas = artistas;
    }

    public Album(AlbumCriarDTO albumCriarDTO){
        this.nomeAlbum = albumCriarDTO.nomeAlbum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }

    public List<Arquivo> getImagens() {
        return imagens;
    }

    public void setImagens(List<Arquivo> imagens) {
        this.imagens = imagens;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
