package com.gladson.seletivo.respository;

import com.gladson.seletivo.model.Album;
import com.gladson.seletivo.model.projecoes.AlbumView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Page<AlbumView> findAllByOrderByNomeAlbumAsc(Pageable pageable);

    Page<AlbumView> findAllByOrderByNomeAlbumDesc(Pageable pageable);

    Page<AlbumView> findByNomeAlbumContainingIgnoreCase(String nome, Pageable pageable);
}
