package com.gladson.seletivo.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_artista_album",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artistas;

}
