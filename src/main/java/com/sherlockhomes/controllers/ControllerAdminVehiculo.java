package com.sherlockhomes.controllers;

import com.sherlockhomes.EntradaCons;
import com.sherlockhomes.GarageRepositoryFile;
import com.sherlockhomes.SocioRepositoryFile;
import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.VehiculoRepositoryFile;
import com.sherlockhomes.views.admin.VistaAdminVehiculos;

public class ControllerAdminVehiculo implements ControllerAdminMenu<Usuario> {
    
    private VehiculoRepositoryFile vehicleRepository;
    private SocioRepositoryFile associatedRepository;
    private GarageRepositoryFile garageRepository;
    private VistaAdminVehiculos vistaAdminVehiculos;
    private int accion;
    
    public ControllerAdminVehiculo(){
        vehicleRepository = new VehiculoRepositoryFile();
        associatedRepository = new SocioRepositoryFile();
        garageRepository = new GarageRepositoryFile();
        vistaAdminVehiculos = new VistaAdminVehiculos();
    }
    
    @Override
    public void iniciar(Usuario u){
        do{
            vistaAdminVehiculos.menu();
            accion = EntradaCons.ingresaInt();
            ejecutar(u);
        } while(accion == -1);
    }

    @Override
    public void ejecutar(Usuario usuario) {
        switch (accion) {
            case 1 -> registrarVehiculo();
            case 2 -> asignarVehiculoGarage();
            case 3 -> quitarVehiculoGarage();
            case 4 -> eliminarVehiculo();
            case 5 -> vehicleRepository.listarAll();
            case 0 -> {}
            default -> System.out.println("Opcion no reconocida");
        }
    }

    public void registrarVehiculo() {
        System.out.println("=== Registro de vehículo para socio ===");

        System.out.println("Ingrese patente: ");
        String patente = EntradaCons.ingresaString();

        if(vehicleRepository.existePorValorS(patente,0)){
            System.out.println("Ya existe el vehiculo con patente " + patente + ".");
            return;
        }

        System.out.println("Ingrese marca: ");
        String marca = EntradaCons.ingresaString();

        System.out.println("Ingrese tipo de vehículo (Auto/Moto/Camioneta): ");
        String tipo = EntradaCons.ingresaString();

        System.out.println("Ingrese Dimensiones: ");
        String dimensiones = EntradaCons.ingresaString();

        System.out.println("Ingrese DNI propietario: ");
        int DNI = EntradaCons.ingresaInt();

        if(!associatedRepository.existePorValor(DNI,0)){
            System.out.print("No existe el socio DNI " + DNI + ".");
            return;
        }
        
        try{
            Vehiculo vehiculo = new Vehiculo(patente, marca, tipo, dimensiones, associatedRepository.buscarPorValor(DNI,0));
            vehicleRepository.crear(vehiculo);
            associatedRepository.asignarSocioVehiculo(DNI,vehiculo);
            System.out.println("Se agregó el vehiculo exitosamente.");
            vehicleRepository.mostrarPorValorS(patente,0);
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void asignarVehiculoGarage() {
        System.out.println("=== Asignar vehículo a garage ===");

        System.out.println("Ingrese patente: ");
        String patente = EntradaCons.ingresaString();

        if(!vehicleRepository.existePorValorS(patente,0)){
            System.out.println("El vehiculo no existe.");
            return;
        }
        
        if(!associatedRepository.tieneGarages(vehicleRepository.buscarPorValorS(patente,0).getPropietario())){
            System.out.println("El socio no tiene garages.");
            return;
        }
        
        associatedRepository.listarSocioGarages(vehicleRepository.buscarPorValorS(patente,0).getPropietario());
        
        System.out.println("Ingrese numero gge: ");
        int numero = EntradaCons.ingresaInt();

        if(!garageRepository.existePorValor(numero,0)){
            System.out.println("El garage no existe.");
            return;
        }
        
        if(vehicleRepository.tieneGarageAsignado(patente)){
            vehicleRepository.quitarVehiculoGarage(patente);
        }
        
        garageRepository.asignarGarageVehiculo(numero,patente);
        vehicleRepository.asignarVehiculoGarage(patente,numero);
    }

    public void quitarVehiculoGarage() {
        System.out.println("=== Quitar vehículo a garage ===");

        System.out.println("Ingrese patente: ");
        String patente = EntradaCons.ingresaString();

        if(!vehicleRepository.existePorValorS(patente,0)){
            System.out.println("El vehiculo no existe.");
            return;
        }
        
        if(!vehicleRepository.tieneGarageAsignado(patente)){
            System.out.println("El vehiculo no tiene garage asignado.");
            return;
        }
        
        garageRepository.quitarVehiculoGarage(patente);
        vehicleRepository.quitarVehiculoGarage(patente);
    }

    public void eliminarVehiculo() {
        System.out.println("=== Eliminar Vehiculo ===");
                
        System.out.print("Ingrese patente a eliminar: ");
        String patente = EntradaCons.ingresaString();

        if(!vehicleRepository.existePorValorS(patente,0)){
            System.out.println("No existe el vehiculo con patente " + patente + ".");
            return;
        }

        try{
            associatedRepository.quitarVehiculoPorPatente(patente);
            vehicleRepository.eliminarPorValorS(patente,0);
            System.out.println("Se eliminó el vehiculo patente " + patente + ".");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
