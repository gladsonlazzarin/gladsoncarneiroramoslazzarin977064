package com.gladson.seletivo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_regional")
public class Regional {

    @Id
    @Column(name = "id")
    private Integer id; // id da regional da API externa (não autogerado)

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public Regional() {
    }

    public Regional(Integer id, String nome, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    // equals e hashCode (baseado no id)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Regional)) return false;
        Regional regional = (Regional) o;
        return Objects.equals(getId(), regional.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
