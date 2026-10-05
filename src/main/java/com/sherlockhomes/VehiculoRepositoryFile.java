package com.sherlockhomes;

import com.sherlockhomes.daos.VehiculoDAO;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.models.Empleado;
import com.sherlockhomes.models.Socio;
import java.util.ArrayList;
import java.util.Iterator;
import static com.sherlockhomes.Persistencia.cargarVehiculos;
import static com.sherlockhomes.Persistencia.guardarVehiculos;

public class VehiculoRepositoryFile implements VehiculoDAO {
    
    private ArrayList<Vehiculo> vehiculos;
    private GarageRepositoryFile garageRepository;
    
    public VehiculoRepositoryFile(){
    }
    
    @Override
    public void crear(Vehiculo vehiculo){ 
        vehiculos = cargarVehiculos();
        
        vehiculos.add(vehiculo);

        guardarVehiculos(vehiculos);
    }
               
    @Override
    public Vehiculo buscarPorValorS(String patente, Integer parametro) {
        vehiculos = cargarVehiculos();
        
        for (Vehiculo v : vehiculos) {
            if (v.getPatente().equals(patente)) {
                return v;
            }
        }
        return null;
    }
    
    @Override
    public boolean existePorValorS(String patente, Integer parametro) {
        return (buscarPorValorS(patente,0) != null);
    }
    
    @Override
    public boolean tieneGarageAsignado(String patente){
        return (buscarPorValorS(patente,0).getGarageAsignado() != null);
    }
   
    @Override
    public boolean tieneEmpleadoAsignado(String patente) {
        return (buscarPorValorS(patente,0).getEmpleadoAsignado() != null);
    }
    
    @Override
    public void asignarVehiculoGarage(String patente, int garage){
        garageRepository = new GarageRepositoryFile();
        Vehiculo vehiculo = buscarPorValorS(patente,0);
        
        for (Vehiculo v : vehiculos) {
            if (v.getPatente() == vehiculo.getPatente()) {
                v.asignarGarage(garageRepository.buscarPorValor(garage,0));
                break; 
            }
        }
        
        guardarVehiculos(vehiculos);
    }
    
    @Override
    public void asignarVehiculoEmpleado(String patente, Empleado empleado){
        buscarPorValorS(patente,0).asignarEmpleado(empleado);
    }
    
    @Override
    public void quitarVehiculoGarage(String patente){
        Vehiculo vehiculo = buscarPorValorS(patente,0);
        
        for (Vehiculo v : vehiculos) {
            if (v.getPatente().equals(vehiculo.getPatente())) {
                v.removerGarage();
                break; 
            }
        }
        
        guardarVehiculos(vehiculos);
    }
    
    @Override
    public void quitarGarageVehiculo(int numero){
        garageRepository = new GarageRepositoryFile();
        Vehiculo vehiculo = buscarPorValorS(garageRepository.buscarPorValor(numero,0).getVehiculoOcupante().getPatente(),0);
        
        for (Vehiculo v : vehiculos) {
            if (v.getPatente().equals(vehiculo.getPatente())) {
                v.removerGarage();
                break; 
            }
        }
        
        guardarVehiculos(vehiculos);
    }

    @Override
    public void quitarVehiculoEmpleado(String patente) {
        Vehiculo vehiculo = buscarPorValorS(patente,0);
        
        for (Vehiculo v : vehiculos) {
            if (v.getPatente()== vehiculo.getPatente()) {
                v.removerEmpleado();
                break; 
            }
        }
        
        guardarVehiculos(vehiculos);
    }
    
    @Override
    public void modificarPorValorS(String v, Integer p, Vehiculo t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void mostrar(Vehiculo vehiculo) {
        System.out.println("Patente: " + vehiculo.getPatente() +
                           " | Marca: " + vehiculo.getMarca() +
                           " | Tipo: " + vehiculo.getTipo() +
                           " | Dimensiones: " + vehiculo.getDimensiones() +
                           " | Propietario: " + vehiculo.getPropietario().getNombre() + " DNI: " + vehiculo.getPropietario().getDNI());
    }
    
    @Override
    public void mostrarPorValorS(String patente,Integer parametro){
        mostrar(buscarPorValorS(patente,parametro));
    }
    
    @Override
    public void mostrarVehiculoGarage(Vehiculo vehiculo) {
        garageRepository = new GarageRepositoryFile();
        System.out.println("=== Garaga del vehiculo ===");
        garageRepository.mostrar(vehiculo.getGarageAsignado());
    }
    
    @Override
    public void listarAll() { 
        vehiculos = cargarVehiculos();
        
        System.out.println("=== Lista de todos los vehiculos ===");
        for (Vehiculo v : vehiculos) {
            mostrar(v);
        }
    }
    
    @Override
    public void listar(ArrayList<Vehiculo> vehiculos) {
        System.out.println("=== Lista de vehiculos ===");
        for (Vehiculo v : vehiculos) {
            mostrar(v);
        }
    }
    
    @Override
    public void eliminar(Vehiculo vehiculo) {
        vehiculos = cargarVehiculos();
        
        Iterator<Vehiculo> it = vehiculos.iterator();
        while (it.hasNext()) {
            Vehiculo vehiculoAux = it.next();
            if (vehiculoAux.getPatente().equals(vehiculo.getPatente())) {
                it.remove(); 
            }
        }
        
        guardarVehiculos(vehiculos);
    }
    
    @Override
    public void eliminarPorValorS(String patente, Integer parametro) {
        eliminar(buscarPorValorS(patente,parametro));
    }

    void eliminarSocio(Socio socioAux) {
        for (Vehiculo v : socioAux.getVehiculos()){
            eliminar(v);
        }
    }

    void eliminarEmpleado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
