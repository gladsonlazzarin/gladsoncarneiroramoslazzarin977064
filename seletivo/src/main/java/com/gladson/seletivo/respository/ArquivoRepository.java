package com.gladson.seletivo.respository;

import com.gladson.seletivo.model.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    Optional<Arquivo> findByUuid(UUID uuid);
}
