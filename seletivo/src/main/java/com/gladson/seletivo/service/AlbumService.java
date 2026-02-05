package com.gladson.seletivo.service;

import com.gladson.seletivo.dto.AlbumCriarDTO;
import com.gladson.seletivo.model.Album;
import com.gladson.seletivo.model.Artista;
import com.gladson.seletivo.model.projecoes.AlbumView;
import com.gladson.seletivo.respository.AlbumRepository;
import com.gladson.seletivo.respository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;


    public Album criar(AlbumCriarDTO albumCriarDTO){
        return albumRepository.save(new Album(albumCriarDTO));
    }

    public Page<AlbumView> todosAsc(Pageable pageable) {
        return albumRepository.findAllByOrderByNomeAlbumAsc(pageable);
    }

    public Page<AlbumView> todosDesc(Pageable pageable) {
        return albumRepository.findAllByOrderByNomeAlbumDesc(pageable);
    }

    public Page<AlbumView> buscaPorNome(String nome, Pageable pageable) {
        return albumRepository.findByNomeAlbumContainingIgnoreCase(nome, pageable);
    }

    public Album atualizanome(Long idAlbum, AlbumCriarDTO albumCriarDTO) {
        Optional<Album> album = albumRepository.findById(idAlbum);

        album.get().setNomeAlbum(albumCriarDTO.nomeAlbum());

        return albumRepository.save(album.get());
    }

    public AlbumView adicionarArtista(Long idAlbum, Long idArtista) {
        Album album = albumRepository.findById(idAlbum).get();
        Artista artista = artistaRepository.findById(idArtista).get();

        album.getArtistas().add(artista);

        albumRepository.save(album);
        return albumRepository.findById(idAlbum, AlbumView.class);
    }

}
