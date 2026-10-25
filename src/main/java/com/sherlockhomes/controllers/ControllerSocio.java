package com.sherlockhomes.controllers;

import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Socio;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.services.SocioService;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Endpoints del socio logueado: siempre trabajan sobre sus propios datos. */
@RestController
@RequestMapping("/api/socio")
public class ControllerSocio {

    private final SocioService socioService;

    public ControllerSocio(SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping("/perfil")
    public Socio verPerfil(Authentication auth) {
        return socioService.buscarPorUsername(auth.getName());
    }

    @GetMapping("/listarVehiculos")
    public List<Vehiculo> listarSocioVehiculos(Authentication auth) {
        Socio yo = socioService.buscarPorUsername(auth.getName());
        return socioService.listarVehiculos(yo.getDni());
    }

    @GetMapping("/listarGarages")
    public List<Garage> listarSocioGarages(Authentication auth) {
        Socio yo = socioService.buscarPorUsername(auth.getName());
        return socioService.listarGarages(yo.getDni());
    }
}
