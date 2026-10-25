package com.sherlockhomes.services;

import com.sherlockhomes.TipoUsuario;
import com.sherlockhomes.exceptions.ConflictoException;
import com.sherlockhomes.exceptions.SolicitudInvalidaException;
import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.repositorys.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private static final int LARGO_MINIMO_PASSWORD = 6;

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return org.springframework.security.core.userdetails.User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles(u.getTipoUsuario().name())
                .build();
    }

    /**
     * Arma un Usuario nuevo (sin guardarlo) con la contraseña ya hasheada.
     * Lo guarda en cascada la entidad que lo contiene (Socio / Empleado).
     */
    public Usuario nuevoUsuario(String username, String password, TipoUsuario tipo) {
        if (username == null || username.isBlank()) {
            throw new SolicitudInvalidaException("El username es obligatorio");
        }
        validarPassword(password);
        if (usuarioRepository.existsByUsername(username)) {
            throw new ConflictoException("Ya existe un usuario con username '" + username + "'");
        }
        return new Usuario(username, passwordEncoder.encode(password), tipo);
    }

    public void cambiarPassword(Usuario usuario, String passwordNueva) {
        validarPassword(passwordNueva);
        usuario.setPassword(passwordEncoder.encode(passwordNueva));
    }

    public boolean coincidePassword(Usuario usuario, String passwordIngresada) {
        return passwordEncoder.matches(passwordIngresada, usuario.getPassword());
    }

    private void validarPassword(String password) {
        if (password == null || password.length() < LARGO_MINIMO_PASSWORD) {
            throw new SolicitudInvalidaException(
                    "La contraseña es obligatoria y debe tener al menos " + LARGO_MINIMO_PASSWORD + " caracteres");
        }
    }
}
