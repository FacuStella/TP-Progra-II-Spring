package com.sherlockhomes.controllers;

import com.sherlockhomes.models.Empleado;
import com.sherlockhomes.EmpleadoRepositoryFile;
import com.sherlockhomes.EntradaCons;
import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.VehiculoRepositoryFile;
import com.sherlockhomes.ZonaRepositoryFile;
import com.sherlockhomes.views.admin.VistaAdminEmpleados;

public class ControllerAdminEmpleado implements ControllerAdminMenu<Usuario> {
    
    protected EmpleadoRepositoryFile employeeRepository;
    protected VehiculoRepositoryFile vehicleRepository;
    protected ZonaRepositoryFile zoneRepository;
    protected VistaAdminEmpleados vistaAdminEmpleados;
    protected int accion;
    protected int buscarPor = 0;
    protected int valor = -1;
    
    public ControllerAdminEmpleado(){
        employeeRepository = new EmpleadoRepositoryFile();
        vehicleRepository = new VehiculoRepositoryFile();
        zoneRepository = new ZonaRepositoryFile();
        vistaAdminEmpleados = new VistaAdminEmpleados();
    }
    
    @Override
    public void iniciar(Usuario u){
        do{
            vistaAdminEmpleados.menu();
            accion = EntradaCons.ingresaInt();
            ejecutar(u);
        } while(accion == -1);
    }
    
    @Override
    public void ejecutar(Usuario usuarioLogueado) {
        switch (accion) {
            case 1 -> registrarEmpleado();
            case 2 -> modificarEmpleado();
            case 3 -> eliminarEmpleado();
            case 4 -> asignarEmpleadoZona();
            case 5 -> quitarEmpleadoZona();
            case 6 -> listarEmpleadoZonas();
            case 7 -> asignarVehiculoEmpleado();
            case 8 -> quitarEmpleadoVehiculo();
            case 9 -> listarEmpleadoVehiculos();
            case 10 -> employeeRepository.listarAll();
            case 0 -> {}
            default -> vistaAdminEmpleados.noReconocida();
        }
    }

    public void menuBusquedaEmpleado() {
        System.out.println("Ingrese clave por la que desea buscar al empleado.");
        System.out.println("1. Por DNI");
        System.out.println("2. Por codigo");
        buscarPor = EntradaCons.ingresaInt();
        
        switch(buscarPor){
            case 1 -> System.out.println("Ingrese DNI: ");
            case 2 -> System.out.println("Ingrese codigo: ");
            default -> {return;}
        }
        
        valor = EntradaCons.ingresaInt();
    }

    private void registrarEmpleado() {
        System.out.println("=== Registro de nuevo Empleado ===");

        System.out.print("Ingrese Nombre: ");
        String nombre = EntradaCons.ingresaString();

        System.out.print("Ingrese DNI: ");
        int DNI = EntradaCons.ingresaInt();

        if(employeeRepository.existePorValor(DNI,1)){
            System.out.println("Ya existe empleado con DNI " + DNI + ".");
            return;
        }

        System.out.print("Ingrese direccion: ");
        String direccion = EntradaCons.ingresaString();

        System.out.print("Ingrese Telefono: ");
        String telefono = EntradaCons.ingresaString();

        System.out.print("Ingrese Especialidad: ");
        String especialidad = EntradaCons.ingresaString();
        
        try{
            Empleado aux = new Empleado(nombre, DNI, direccion, telefono, especialidad);
            employeeRepository.crear(aux);
            System.out.println("Se agregó el empleado exitosamente.");
            employeeRepository.mostrarPorValor(DNI,1);
        } catch(Exception e) {
            System.out.println("Fallo registro de empleado." + e.getMessage());
        }
    }

    private void modificarEmpleado() {
        System.out.println("=== Modificar Empleado ===");
                
        menuBusquedaEmpleado();

        if(!employeeRepository.existePorValor(valor,buscarPor)){
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.print("Modificar direccion o vacio: ");
        String direccion = EntradaCons.ingresaString();

        System.out.print("Modificar telefono o vacio: ");
        String telefono = EntradaCons.ingresaString();

        System.out.print("Modificar especialidad o vacio: ");
        String especialidad = EntradaCons.ingresaString();
        
        Empleado empleado = new Empleado("", 0, direccion, telefono, especialidad);
        employeeRepository.modificarPorValor(valor, buscarPor, empleado);
        System.out.println("Se modificó existosamente el empleado.");
        employeeRepository.mostrarPorValor(valor,buscarPor);
    }

    private void eliminarEmpleado() {
        System.out.println("=== Eliminar Empleado ===");
        
        menuBusquedaEmpleado();

        if(employeeRepository.existePorValor(valor, buscarPor)){
            employeeRepository.eliminarPorValor(valor, buscarPor);
        } else {
            System.out.println("No existe el empleado ingresado.");
        }
        System.out.println("Se eliminó el empleado exitosamente.");
    }

    private void asignarEmpleadoZona() {
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
        
        zoneRepository.asignarZonaEmpleado(letra, codigo);
        employeeRepository.asignarEmpleadoZona(codigo, zoneRepository.buscarPorValorS(letra,0));
        
        System.out.println("Empleado asignado exitosamente.");
    }

    private void asignarVehiculoEmpleado() {
        System.out.println("=== Asignar vehículo a empleado ===");
        
        employeeRepository.listarAll();
        
        System.out.println("Ingrese codigo: ");
        int codigo = EntradaCons.ingresaInt();

        if(!employeeRepository.existePorValor(codigo,2)){
            System.out.println("El empleado no existe.");
            return;
        }
        
        vehicleRepository.listarAll();
        
        System.out.println("Ingrese patente: ");
        String patente = EntradaCons.ingresaString();

        if(!vehicleRepository.existePorValorS(patente,0)){
            System.out.println("El vehiculo no existe.");
            return;
        }
        
        if(vehicleRepository.tieneEmpleadoAsignado(patente)){
            vehicleRepository.quitarVehiculoEmpleado(patente);
        }
        
        vehicleRepository.asignarVehiculoEmpleado(patente,employeeRepository.buscarPorValor(codigo,2));
        employeeRepository.asignarEmpleadoVehiculo(codigo,vehicleRepository.buscarPorValorS(patente,0));
        // falta modificar el objeto VehiculoAsignado de Garage
    }

    private void listarEmpleadoVehiculos() {
        menuBusquedaEmpleado();

        if(employeeRepository.existePorValor(valor,buscarPor)){
            employeeRepository.listarEmpleadoVehiculos(employeeRepository.buscarPorValor(valor,buscarPor));
        } else {
            System.out.println("No existe el empleado ingresado.");
        }
    }
    
    private void listarEmpleadoZonas() {
        menuBusquedaEmpleado();
        
        if(employeeRepository.existePorValor(valor,buscarPor)){
            employeeRepository.listarEmpleadoZonas(employeeRepository.buscarPorValor(valor,buscarPor));
        } else {
            System.out.println("No existe el empleado ingresado.");
        }
    }

    private void quitarEmpleadoZona() {
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
        
        System.out.println("Empleado desasignado exitosamente.");
    }

    private void quitarEmpleadoVehiculo() {
        System.out.println("=== Quitar vehiculo a empleado ===");
        
        System.out.println("Ingrese codigo de empleado: ");
        int codigo = EntradaCons.ingresaInt();

        if(!employeeRepository.existePorValor(codigo,2)){
            System.out.println("El empleado no existe.");
            return;
        }
        
        employeeRepository.listarEmpleadoVehiculos(employeeRepository.buscarPorValor(codigo,2));
                
        System.out.print("Ingrese patente a quitar: ");
        String patente = EntradaCons.ingresaString();

        if(!vehicleRepository.existePorValorS(patente,0)){
            System.out.println("No existe vehiculo.");
            return;
        }
        
        vehicleRepository.quitarVehiculoEmpleado(patente);
        employeeRepository.quitarEmpleadoVehiculo(codigo, vehicleRepository.buscarPorValorS(patente,0));
        
        System.out.println("Vehiculo desasignado exitosamente.");
    }
     
}
