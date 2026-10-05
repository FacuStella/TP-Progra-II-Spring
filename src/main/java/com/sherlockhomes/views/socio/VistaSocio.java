package com.sherlockhomes.views.socio;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import com.sherlockhomes.view.Vista;

public class VistaSocio implements Vista {
    
    public VistaSocio(){
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    }
    
    @Override
    public void menu() {
        System.out.println("=== Menú Socio ===");
        System.out.println("1. Consultar vehículos");
        System.out.println("2. Consultar garages");
        System.out.println("0. Salir");
    }

    @Override
    public void salir() {
        System.out.println("Gracias vuelva prontos.");
    }
    
    @Override
    public void noReconocida() {
        System.out.println("Opcion no reconocida");
    }
}