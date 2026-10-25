package com.sherlockhomes.repositorys;

import com.sherlockhomes.TipoUsuario;
import com.sherlockhomes.models.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByTipoUsuario(TipoUsuario tipoUsuario);
}
