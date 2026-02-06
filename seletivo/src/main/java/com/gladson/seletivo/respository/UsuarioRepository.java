package com.gladson.seletivo.respository;

import com.gladson.seletivo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNome(String nomeUsuario);
}
