package com.sherlockhomes.views.admin;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import com.sherlockhomes.view.Vista;

public class VistaAdministrador implements Vista {
    
    public VistaAdministrador(){
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    }
    
    @Override
    public void menu() {
        System.out.println("=== Menú Administrador ===");
        System.out.println("1. Gestionar socios");
        System.out.println("2. Gestionar vehículos");
        System.out.println("3. Gestionar garages");
        System.out.println("4. Gestionar zonas");
        System.out.println("5. Gestionar empleados");
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