package com.sherlockhomes.controllers;

import com.sherlockhomes.views.empleado.VistaEmpleado;
import com.sherlockhomes.models.Empleado;
import com.sherlockhomes.EmpleadoRepositoryFile;
import com.sherlockhomes.EntradaCons;

public class ControllerEmpleado implements Controller<Empleado> {
    
    private EmpleadoRepositoryFile employeeRepository;
    private VistaEmpleado vistaEmpleado;
    private int accion;
    
    public ControllerEmpleado(){
        employeeRepository = new EmpleadoRepositoryFile();
        vistaEmpleado = new VistaEmpleado();
    }
    
    @Override
    public void iniciar(Empleado e){
            do{
                vistaEmpleado.menu();
                accion = EntradaCons.ingresaInt();
                ejecutar(e);
            } while(accion != 0);
    }

    @Override
    public void ejecutar(Empleado empleadoAux) {
        switch (accion) {
            case 1 -> employeeRepository.listarEmpleadoZonas(empleadoAux);
            case 2 -> employeeRepository.listarEmpleadoVehiculos(empleadoAux);
            case 0 -> vistaEmpleado.salir();
            default -> vistaEmpleado.noReconocida();
        }
    }  
}
