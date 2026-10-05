package com.sherlockhomes.views.admin;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import com.sherlockhomes.view.Vista;

public class VistaAdminZonas implements Vista {

    public VistaAdminZonas() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    }

    @Override
    public void menu() {
        System.out.println("=== Acciones sobre Zonas ===");
        System.out.println("1. Agregar");
        System.out.println("2. Asignar empleado");
        System.out.println("3. Quitar empleado");
        System.out.println("4. Mostrar todas");
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