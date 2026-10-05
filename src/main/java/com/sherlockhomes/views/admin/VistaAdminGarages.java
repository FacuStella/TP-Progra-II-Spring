package com.sherlockhomes.views.admin;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import com.sherlockhomes.view.Vista;

public class VistaAdminGarages implements Vista{

    public VistaAdminGarages() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    }

    @Override
    public void menu() {
        System.out.println("=== Acciones sobre Garages ===");
        System.out.println("1. Agregar garage");
        System.out.println("2. Asignar vehiculo a garage");
        System.out.println("3. Quitar vehiculo asignado");
        System.out.println("4. Comprar garage");
        System.out.println("5. Mostrar todos");
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