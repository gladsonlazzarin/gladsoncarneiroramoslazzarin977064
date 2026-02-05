package com.gladson.seletivo.respository;

import com.gladson.seletivo.model.Artista;
import com.gladson.seletivo.model.projecoes.ArtistaView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Page<ArtistaView> findAllByOrderByNomeArtistaAsc(Pageable pageable);

    Page<ArtistaView> findAllByOrderByNomeArtistaDesc(Pageable pageable);

    Page<ArtistaView> findByNomeArtistaContainingIgnoreCase(String nome, Pageable pageable);
}
