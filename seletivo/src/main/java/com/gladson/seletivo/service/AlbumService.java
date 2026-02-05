package com.gladson.seletivo.service;

import com.gladson.seletivo.dto.AlbumCriarDTO;
import com.gladson.seletivo.model.Album;
import com.gladson.seletivo.model.Arquivo;
import com.gladson.seletivo.model.Artista;
import com.gladson.seletivo.model.projecoes.AlbumView;
import com.gladson.seletivo.respository.AlbumRepository;
import com.gladson.seletivo.respository.ArquivoRepository;
import com.gladson.seletivo.respository.ArtistaRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private ArquivoRepository arquivoRepository;


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

    public AlbumView adicionarImagemAoAlbum(Long idAlbum, MultipartFile file) throws Exception {
        Album album = albumRepository.findById(idAlbum)
                .orElseThrow(() -> new RuntimeException("Album não encontrado"));

        String bucket = "seletivo";
        UUID uuid = UUID.randomUUID();
        String objectName = uuid.toString() + "-" + file.getOriginalFilename();

        // Upload para o MinIO
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        // Criar entidade Arquivo
        Arquivo arquivo = new Arquivo();
        arquivo.setUuid(uuid);
        arquivo.setNomeArquivo(file.getOriginalFilename());
        arquivo.setExtensao(getExtensao(file.getOriginalFilename()));
        arquivo.setContentType(file.getContentType());
        arquivo.setTamanhoBytes(file.getSize());
        arquivo.setBucket(bucket);
        arquivo.setObjectName(objectName);
        arquivo.setAlbum(album);

        arquivo.setAlbum(album);
        arquivoRepository.save(arquivo);

        return albumRepository.findById(idAlbum, AlbumView.class);

    }


    private String getExtensao(String nomeArquivo) {
        int i = nomeArquivo.lastIndexOf('.');
        return i > 0 ? nomeArquivo.substring(i + 1) : "";
    }

    public String gerarLinkPreAssinado(UUID uuid) throws Exception {
        // Buscar arquivo no banco
        Arquivo arquivo = arquivoRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Arquivo não encontrado"));

        // Gerar link pré-assinado
        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(arquivo.getBucket())
                        .object(arquivo.getObjectName())
                        .expiry(30, TimeUnit.MINUTES) // expira em 30 minutos
                        .build()
        );

        return url;
    }
}
