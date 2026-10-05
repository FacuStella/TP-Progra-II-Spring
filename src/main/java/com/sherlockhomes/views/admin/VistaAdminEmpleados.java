package com.sherlockhomes.views.admin;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import com.sherlockhomes.view.Vista;

public class VistaAdminEmpleados implements Vista {

    public VistaAdminEmpleados() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    }
    
    @Override
    public void menu() {
        System.out.println("=== Acciones sobre Empleados ===");
        System.out.println("1. Agregar");
        System.out.println("2. Modificar");
        System.out.println("3. Eliminar");
        System.out.println("4. Asignar zona");
        System.out.println("5. Quitar zona");
        System.out.println("6. Mostrar zonas asignadas");
        System.out.println("7. Asignar vehiculo");
        System.out.println("8. Quitar vehiculo");
        System.out.println("9. Mostrar vehiculos asignados");
        System.out.println("10. Mostrar todos");
        System.out.println("0. Volver al menu principal");
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