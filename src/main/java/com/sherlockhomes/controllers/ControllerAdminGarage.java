package com.sherlockhomes.controllers;

import com.sherlockhomes.EntradaCons;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.GarageRepositoryFile;
import com.sherlockhomes.SocioRepositoryFile;
import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.VehiculoRepositoryFile;
import com.sherlockhomes.ZonaRepositoryFile;
import com.sherlockhomes.views.admin.VistaAdminGarages;

public class ControllerAdminGarage implements ControllerAdminMenu<Usuario> {
    
    protected GarageRepositoryFile garageRepository;
    protected VehiculoRepositoryFile vehicleRepository;
    protected SocioRepositoryFile asocciatedRepository;
    protected ZonaRepositoryFile zoneRepository;
    protected VistaAdminGarages vistaAdminGarages;
    protected int accion;
    
    public ControllerAdminGarage(){
        garageRepository = new GarageRepositoryFile();
        vehicleRepository = new VehiculoRepositoryFile();
        asocciatedRepository = new SocioRepositoryFile();
        zoneRepository = new ZonaRepositoryFile();
        vistaAdminGarages = new VistaAdminGarages();
    }
    
    @Override
    public void iniciar(Usuario u){
        do{
            vistaAdminGarages.menu();
            accion = EntradaCons.ingresaInt();
            ejecutar(u);
        } while(accion == -1);
    }


    @Override
    public void ejecutar(Usuario usuario) {
        switch (accion) {
            case 1 -> registrarGarage(); 
            case 2 -> asignarGarageVehiculo();
            case 3 -> quitarGarageVehiculo();
            case 4 -> comprarGarage();
            case 5 -> garageRepository.listarAll();
            case 0 -> {}
            default -> vistaAdminGarages.noReconocida();
        }
    }

    private void registrarGarage() {
        System.out.println("=== Registro de garage ===");
        
        System.out.print("Ingrese zona donde se agrega el garage: ");
        String letra = EntradaCons.ingresaString();

        if(!zoneRepository.existePorValorS(letra,0)){
            System.out.println("No existe la zona.");
            return;
        }
        
        try{
            Garage garage = new Garage(zoneRepository.buscarPorValorS(letra,0)); 
            garageRepository.crear(garage);
            System.out.println("Se agregó el garage exitosamente.");
            //garageRepository.mostrarGaragePorNumero(patente);
        } catch(Exception e) {
            System.out.println("Error inesperado al crear el garage" + e.getMessage());
        }
    }

    private void quitarGarageVehiculo() {
        System.out.println("=== Quitar garage a vehiculo ===");

        System.out.println("Ingrese numero: ");
        int numero = EntradaCons.ingresaInt();
        
        if(!garageRepository.existePorValor(numero,0)){
            System.out.println("El garage no existe.");
            return;
        }
        
        if(!garageRepository.tieneVehiculoAsignado(numero)){
            System.out.println("El garage no tiene vehiculo asignado.");
            return;
        }
        
        vehicleRepository.quitarGarageVehiculo(numero);
        garageRepository.quitarGarageVehiculo(numero);  
    }

    private void asignarGarageVehiculo() {
        System.out.println("=== Asignar garage a vehículo===");
        
        System.out.println("Ingrese numero gge: ");
        int numero = EntradaCons.ingresaInt();
        
        if(!garageRepository.existePorValor(numero,0)){
            System.out.println("El garage no existe.");
            return;
        }
        
        if(!garageRepository.tienePropietario(numero)){
            System.out.println("El garage no tiene propietario, por tanto no puede asignarse.");
            return;
        }
        
        asocciatedRepository.listarSocioVehiculos(garageRepository.buscarPorValor(numero,0).getPropietario());
        
        System.out.println("Ingrese patente: ");
        String patente = EntradaCons.ingresaString();

        if(!vehicleRepository.existePorValorS(patente,0)){
            System.out.println("El vehiculo no existe.");
            return;
        }
        
        if(garageRepository.tieneVehiculoAsignado(numero)){
            garageRepository.quitarGarageVehiculo(numero);
        }
        
        if(vehicleRepository.tieneGarageAsignado(patente)){
            garageRepository.quitarVehiculoGarage(patente);
        }
        
        garageRepository.asignarGarageVehiculo(numero,patente);
        vehicleRepository.asignarVehiculoGarage(patente,numero);
    }

    private void comprarGarage() {
        System.out.println("=== Venta de garage ===");
        
        System.out.println("Ingrese numero gge: ");
        int numero = EntradaCons.ingresaInt();
        
        if(!garageRepository.existePorValor(numero,0)){
            System.out.println("El garage no existe.");
            return;
        }
        
        if(garageRepository.tienePropietario(numero)){
            System.out.println("El garage tiene propietario, por tanto no puede comprarse.");
            return;
        }
        
        System.out.println("Ingrese DNI del socio que compra: ");
        int DNI = EntradaCons.ingresaInt();
        
        if(!asocciatedRepository.existePorValor(DNI,0)){
            System.out.println("El socio no existe.");
            return;
        }
        
        asocciatedRepository.comprarGarage(DNI,garageRepository.buscarPorValor(numero,0));
        garageRepository.comprarGarage(numero,asocciatedRepository.buscarPorValor(DNI,0));
    }
}
