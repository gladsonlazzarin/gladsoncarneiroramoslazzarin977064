package com.gladson.seletivo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

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

}
