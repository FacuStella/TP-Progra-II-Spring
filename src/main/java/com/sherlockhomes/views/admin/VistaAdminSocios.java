package com.sherlockhomes.views.admin;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import com.sherlockhomes.view.Vista;

public class VistaAdminSocios implements Vista {

    public VistaAdminSocios() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    }

    @Override
    public void menu() {
        System.out.println("=== Acciones sobre Socios ===");
        System.out.println("1. Agregar");
        System.out.println("2. Modificar");
        System.out.println("3. Eliminar");
        System.out.println("4. Mostrar vehiculos");
        System.out.println("5. Mostrar garages");
        System.out.println("6. Mostrar todos");
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