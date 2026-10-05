package com.sherlockhomes;

import com.sherlockhomes.daos.ZonaDAO;
import com.sherlockhomes.models.Zona;
import com.sherlockhomes.models.Empleado;
import com.sherlockhomes.models.Garage;
import java.util.ArrayList;
import static com.sherlockhomes.Persistencia.cargarZonas;
import static com.sherlockhomes.Persistencia.guardarZonas;

public class ZonaRepositoryFile implements ZonaDAO {
    
    ArrayList<Zona> zonas;
    GarageRepositoryFile garageRepository;
    EmpleadoRepositoryFile employeeRepository;
    
    public ZonaRepositoryFile(){
        garageRepository = new GarageRepositoryFile();
    }
    
    @Override
    public void crear(Zona zona) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Zona buscarPorValorS(String letra, Integer parametro) {
        zonas = cargarZonas();
        
        for (Zona z : zonas) {
            if (z.getLetra().equals(letra)) {
                return z;
            }
        }
        return null;
    }
    
    @Override
    public boolean existePorValorS(String letra, Integer parametro) {
        return (buscarPorValorS(letra,parametro) != null);
    }
    
    @Override
    public void mostrar(Zona zona) {
        System.out.println(
                "Letra: " + zona.getLetra()+
                " | Tipo vehiculos: " + zona.getTipoVehiculos()+
                " | Zona: " + zona.getNumeroVehiculos() +
                " | Dimensiones garage: " + zona.getAnchoGarage() + "x" + zona.getProfundidadGarage()
        );
    }
    
    @Override
    public void listarAll() {
        zonas = cargarZonas();
        System.out.println("=== Lista de todas las zonas ===");
        for (Zona z : zonas) {
            mostrar(z);
        }
    }


    @Override
    public void listar(ArrayList<Zona> zonas) {
        System.out.println("=== Lista de zonas ===");
        for (Zona z : zonas) {
            mostrar(z);
        }
    }
   
    @Override
    public void listarZonaGarages(Zona zona) {
        System.out.println("=== Lista de Garages de Zona "+zona.getLetra()+" ===");
        for (Garage g : zona.getGarages()) {
            garageRepository.mostrar(g);
        }
    }
    
    @Override
    public void asignarZonaEmpleado(String letra, int codigo) {
        employeeRepository = new EmpleadoRepositoryFile();
        Zona zona = buscarPorValorS(letra,0);
        
        for (Zona z : zonas) {
            if (z.getLetra().equals(zona.getLetra())) {
                z.asignarEmpleado(employeeRepository.buscarPorValor(codigo,2));
                break; 
            }
        }
        
        guardarZonas(zonas);
    }
    
    @Override
    public void quitarZonaEmpleado(String letra, Empleado empleado) {
        Zona zona = buscarPorValorS(letra,0);
        
        for (Zona z : zonas) {
            if (z.getLetra().equals(zona.getLetra())) {
                z.quitarEmpleado(empleado);
                break; 
            }
        }
        
        guardarZonas(zonas);
    }

    public void eliminarEmpleado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Zona t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificarPorValorS(String v, Integer p, Zona t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarPorValorS(String v, Integer p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPorValorS(String v, Integer p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
