package com.sherlockhomes.controllers;

import com.sherlockhomes.EmpleadoRepositoryFile;
import com.sherlockhomes.EntradaCons;
import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.VehiculoRepositoryFile;
import com.sherlockhomes.ZonaRepositoryFile;
import com.sherlockhomes.views.admin.VistaAdminZonas;

public class ControllerAdminZona implements ControllerAdminMenu<Usuario>{
    
    ZonaRepositoryFile zoneRepository;
    VehiculoRepositoryFile vehicleRepository;
    EmpleadoRepositoryFile employeeRepository;
    private VistaAdminZonas vistaAdminZonas;
    private int accion;
    
    public ControllerAdminZona() {
        zoneRepository = new ZonaRepositoryFile();
        vehicleRepository = new VehiculoRepositoryFile();
        employeeRepository = new EmpleadoRepositoryFile();
        vistaAdminZonas = new VistaAdminZonas();
    }
    
    @Override
    public void iniciar(Usuario u){
        do{
            vistaAdminZonas.menu();
            accion = EntradaCons.ingresaInt();
            ejecutar(u);
        } while(accion == -1);
    }

    @Override
    public void ejecutar(Usuario usuario) {
        switch (accion) {
            case 1 -> registrarZona(); 
            case 2 -> asignarEmpleado();
            case 3 -> quitarEmpleado();
            case 4 -> zoneRepository.listarAll();
            case 0 -> {}
            default -> vistaAdminZonas.noReconocida();
        }
    }

    private void registrarZona() {
        System.out.println("=== Registro de Zona ===");
        
        System.out.print("Ingrese zona: ");
        String letra = EntradaCons.ingresaString();

        if(zoneRepository.existePorValorS(letra,0)){
            System.out.println("La zona ya existe.");
            return;
        }
    }


    private void asignarEmpleado() {
        System.out.println("=== Asignar zona a empleado ===");
                
        System.out.print("Ingrese letra de la zona que quiere asignar: ");
        String letra = EntradaCons.ingresaString();

        if(!zoneRepository.existePorValorS(letra,0)){
            System.out.println("No existe la zona con letra " + letra + ".");
            return;
        }
        
        System.out.println("Ingrese codigo de empleado a asignar: ");
        int codigo = EntradaCons.ingresaInt();

        if(!employeeRepository.existePorValor(codigo,2)){
            System.out.println("El empleado no existe.");
            return;
        }
        
        employeeRepository.asignarEmpleadoZona(codigo, zoneRepository.buscarPorValorS(letra,0));
        zoneRepository.asignarZonaEmpleado(letra, codigo);

        System.out.println("Empleado asignado exitosamente.");
    }

    private void quitarEmpleado() {
        System.out.println("=== Quitar de zona a empleado ===");
        
        System.out.println("Ingrese codigo de empleado: ");
        int codigo = EntradaCons.ingresaInt();

        if(!employeeRepository.existePorValor(codigo,2)){
            System.out.println("El empleado no existe.");
            return;
        }
        
        employeeRepository.listarEmpleadoZonas(employeeRepository.buscarPorValor(codigo,2));
                
        System.out.print("Ingrese letra de la zona que quiere quitar: ");
        String letra = EntradaCons.ingresaString();

        if(!zoneRepository.existePorValorS(letra,0)){
            System.out.println("No existe la zona con letra " + letra + ".");
            return;
        }
        
        zoneRepository.quitarZonaEmpleado(letra, employeeRepository.buscarPorValor(codigo,2));
        employeeRepository.quitarEmpleadoZona(codigo, zoneRepository.buscarPorValorS(letra,0));
        
        System.out.println("Empleado asignado exitosamente.");
    }
}
