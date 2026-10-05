package com.sherlockhomes;

import com.sherlockhomes.daos.GarageDAO;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Socio;
import java.util.ArrayList;
import static com.sherlockhomes.Persistencia.cargarGarages;
import static com.sherlockhomes.Persistencia.guardarGarages;

public class GarageRepositoryFile implements GarageDAO {
    
    protected ArrayList<Garage> garages;
    protected VehiculoRepositoryFile vehicleRepository;
    
    public GarageRepositoryFile(){
    }
    
    @Override
    public int ultimoGarage() {
        garages = cargarGarages();
        
        return garages.get(garages.size() - 1).getNumeroGarage();
    }
    
    @Override
    public void crear(Garage garage) {
        int nuevoCodigo = ultimoGarage()+1; // se carga garages
        
        garages.add(new Garage(nuevoCodigo,garage.getZona()));
        
        guardarGarages(garages);
    }
    
    @Override
    public Garage buscarPorValor(Integer numero, Integer parametro) {
        garages = cargarGarages();
        
        for (Garage g : garages) {
            if (g.getNumeroGarage()== numero) {
                return g;
            }
        }
        return null;
    }
    
    @Override
    public ArrayList<Garage> buscarGaragePorSocio(Socio socio) {
        garages = cargarGarages();
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean existePorValor(Integer numero, Integer parametro) {
        return (buscarPorValor(numero,parametro) != null);
    }
    
    public boolean tieneVehiculoAsignado(int numero){
        return (buscarPorValor(numero,0).getVehiculoOcupante() != null);    
    }
    
    public boolean tienePropietario(int numero) {
        return (buscarPorValor(numero,0).getPropietario() != null);
    }
    
    public void comprarGarage(int numero, Socio socio) {
        Garage garage = buscarPorValor(numero,0);
        
        for (Garage g : garages) {
            if (g.getNumeroGarage()== garage.getNumeroGarage()) {
                g.asignarPropietario(socio);
                break; 
            }
        }
        
        guardarGarages(garages);
    }
    
    @Override
    public void asignarGarageVehiculo(int numero, String patente){
        vehicleRepository = new VehiculoRepositoryFile();
        Garage garage = buscarPorValor(numero,0);
        
        for (Garage g : garages) {
            if (g.getNumeroGarage()== garage.getNumeroGarage()) {
                g.asignarVehiculo(vehicleRepository.buscarPorValorS(patente,0));
                break; 
            }
        }
        
        guardarGarages(garages);
    }
    
    @Override
    public void quitarGarageVehiculo(int numero){
        Garage garage = buscarPorValor(numero,0);
        
        for (Garage g : garages) {
            if (g.getNumeroGarage()== garage.getNumeroGarage()) {
                g.removerVehiculo();
                break; 
            }
        }
        
        guardarGarages(garages);
    }
    
    @Override
    public void quitarVehiculoGarage(String patente){
        vehicleRepository = new VehiculoRepositoryFile();
        Garage garage = buscarPorValor(vehicleRepository.buscarPorValorS(patente,0).getGarageAsignado().getNumeroGarage(),0);
        
        for (Garage g : garages) {
            if (g.getNumeroGarage()== garage.getNumeroGarage()) {
                g.removerVehiculo();
                break; 
            }
        }
        
        guardarGarages(garages);
    }
    
    @Override
    public void mostrar(Garage garage) {
        System.out.println(
                "Numero: " + garage.getNumeroGarage() +
                " | Contador de luz: " + garage.getLecturaContadorLuz()+
                " | Mantenimiento: " + (garage.isMantenimientoContratado() ? "Si" : "No") +
                " | Zona: " + garage.getZona().getLetra() +
                (
                    (garage.getPropietario()!= null) ?
                    " | Propietario: " + garage.getPropietario().getNombre() + " DNI: " + garage.getPropietario().getDNI() +
                            ((garage.getVehiculoOcupante() != null) ? 
                                " | Vehiculo: " + garage.getVehiculoOcupante().getMarca() + " Patente: " + garage.getVehiculoOcupante().getPatente() 
                                : "")
                    : ""
                )
        );
    }
    
    @Override
    public void listarAll() {
        garages = cargarGarages();
        System.out.println("=== Lista de Garages ===");
        for (Garage g : garages) {
            mostrar(g);
        }
    }

    @Override
    public void listar(ArrayList<Garage> garages) {
        System.out.println("=== Lista de Garages ===");
        for (Garage g : garages) {
            mostrar(g);
        }
    }
    
    @Override
    public void eliminarSocio(Socio socioAux) {
        for (Garage g : socioAux.getGarages()) {
            quitarGarageVehiculo(g.getNumeroGarage());
        }
    }
    
    // VIOLA EL PRINCIPIO DE LISKOV
    @Override
    public void modificarPorValor(Integer v, Integer p, Garage t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarPorValor(Integer v, Integer p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Garage t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPorValor(Integer v, Integer p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // QUE LO REMIL PARIO
}
