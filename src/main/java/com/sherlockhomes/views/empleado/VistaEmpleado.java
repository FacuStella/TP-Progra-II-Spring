package com.sherlockhomes.views.empleado;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import com.sherlockhomes.view.Vista;

public class VistaEmpleado implements Vista {
    
    public VistaEmpleado(){
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    }
    
    @Override
    public void menu() {
        System.out.println("=== Menú Empleado ===");
        System.out.println("1. Consultar zonas asignadas");
        System.out.println("2. Consultar vehículos asignados");
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