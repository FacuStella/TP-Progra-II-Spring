package com.sherlockhomes.config;

import com.sherlockhomes.TipoUsuario;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Zona;
import com.sherlockhomes.repositorys.GarageRepository;
import com.sherlockhomes.repositorys.UsuarioRepository;
import com.sherlockhomes.repositorys.ZonaRepository;
import com.sherlockhomes.services.UsuarioService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Datos iniciales. Se ejecuta en cada arranque, pero solo crea lo que falta:
 * el primer administrador (si no hay ninguno), las zonas por defecto (si no hay ninguna)
 * y los garages por defecto (si no hay ninguno).
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ZonaRepository zonaRepository;
    private final GarageRepository garageRepository;
    private final UsuarioService usuarioService;

    @Value("${app.admin.username:admin}")
    private String adminUsername;

    @Value("${app.admin.password:}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        crearAdministradorInicial();
        crearZonasPorDefecto();
        crearGaragesPorDefecto();
    }

    private void crearAdministradorInicial() {
        if (usuarioRepository.existsByTipoUsuario(TipoUsuario.ADMINISTRADOR)) {
            return;
        }
        usuarioRepository.save(
                usuarioService.nuevoUsuario(adminUsername, adminPassword, TipoUsuario.ADMINISTRADOR));
        log.warn("Se creó el administrador inicial '{}'. Cambiá su contraseña (app.admin.password).", adminUsername);
    }

    private void crearZonasPorDefecto() {
        if (zonaRepository.count() > 0) {
            return;
        }
        zonaRepository.saveAll(List.of(
                new Zona("A", "Autos", 5, 3),
                new Zona("B", "Motos", 3, 2),
                new Zona("C", "Camionetas", 6, 4),
                new Zona("D", "Camiones", 10, 6),
                new Zona("E", "SUV", 7, 4)));
        log.info("No había zonas: se crearon las 5 zonas por defecto (A a E).");
    }

    /**
     * 20 garages numerados del 1 al 20, repartidos en rotación entre las zonas
     * (1 -> A, 2 -> B, 3 -> C, 4 -> D, 5 -> E, 6 -> A, ...): 4 por zona.
     */
    private void crearGaragesPorDefecto() {
        if (garageRepository.count() > 0) {
            return;
        }
        List<Zona> zonas = new ArrayList<>(zonaRepository.findAll());
        if (zonas.isEmpty()) {
            return;
        }
        zonas.sort(Comparator.comparing(Zona::getLetra));

        List<Garage> garages = new ArrayList<>();
        int numero = 1;
        for (int ronda = 0; ronda < 4; ronda++) {
            for (Zona zona : zonas) {
                garages.add(new Garage(numero++, zona));
            }
        }
        garageRepository.saveAll(garages);
        log.info("No había garages: se crearon {} garages por defecto.", garages.size());
    }
}