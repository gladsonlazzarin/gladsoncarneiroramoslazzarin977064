package com.gladson.seletivo.service;

import com.gladson.seletivo.dto.ArtistaCriarDTO;
import com.gladson.seletivo.model.Artista;
import com.gladson.seletivo.model.projecoes.ArtistaView;
import com.gladson.seletivo.respository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public Artista criar(ArtistaCriarDTO artistaCriarDTO){
        return artistaRepository.save(new Artista(artistaCriarDTO));
    }

    public Page<ArtistaView> todosAsc(Pageable pageable) {
        return artistaRepository.findAllByOrderByNomeArtistaAsc(pageable);
    }

    public Page<ArtistaView> todosDesc(Pageable pageable) {
        return artistaRepository.findAllByOrderByNomeArtistaDesc(pageable);
    }

    public Page<ArtistaView> buscaPorNome(String nome, Pageable pageable) {
        return artistaRepository.findByNomeArtistaContainingIgnoreCase(nome, pageable);
    }

    public Artista atualizanome(Long idArtista, ArtistaCriarDTO artistaCriarDTO) {
        Optional<Artista> artista = artistaRepository.findById(idArtista);

        artista.get().setNomeArtista(artistaCriarDTO.nomeArtista());

        return artistaRepository.save(artista.get());
    }
}
