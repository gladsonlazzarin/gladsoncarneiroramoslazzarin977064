package com.gladson.seletivo.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_arquivo")
public class Arquivo implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "extensao")
    private String extensao;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "bucket")
    private String bucket;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "tamanho_bytes")
    private Long tamanhoBytes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;


}
