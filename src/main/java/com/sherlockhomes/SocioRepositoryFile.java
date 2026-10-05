package com.sherlockhomes;

import com.sherlockhomes.daos.SocioDAO;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Socio;
import java.util.ArrayList;
import static com.sherlockhomes.Persistencia.cargarSocios;

public class SocioRepositoryFile implements SocioDAO {
    
    protected ArrayList<Socio> socios;
    protected UsuarioRepositoryFile userRepository;
    protected VehiculoRepositoryFile vehicleRepository;
    protected GarageRepositoryFile garageRepository;
    
    public SocioRepositoryFile(){
        userRepository = new UsuarioRepositoryFile();
    }
    
    @Override
    public void crear(Socio socio){
        int ultimoId = userRepository.ultimoUsuario();
        
        Socio socioAux = new Socio(ultimoId+1, socio.getNombre(), socio.getDNI(), socio.getDireccion(), socio.getTelefono());

        userRepository.agregarUsuario(socioAux);
    }
    
    @Override
    public Socio buscarPorValor(Integer dni, Integer parametro) {
        socios = cargarSocios();
        for (Socio s : socios) {
            if (s.getDNI() == dni) {
                return s;
            }
        }
        return null;
    }
    
    @Override
    public boolean existePorValor(Integer dni, Integer parametro) {
        return (buscarPorValor(dni,parametro) != null);
    }
    
    @Override
    public void modificarPorValor(Integer dni, Integer parametro, Socio socio) {
        Socio socioAux = buscarPorValor(dni,0);
        
        if(!socio.getDireccion().isBlank()){socioAux.setDireccion(socio.getDireccion());}
        if(!socio.getTelefono().isBlank()){socioAux.setTelefono(socio.getTelefono());}

        userRepository.modificarSocio(socioAux);
    }
    
    @Override
    public void comprarGarage(int dni, Garage garage) {
        Socio socio = buscarPorValor(dni,0);
        
        socio.comprarGarage(garage);

        userRepository.modificarSocio(socio);
    }
    
    public void asignarSocioVehiculo(int dni, Vehiculo vehiculo){
        socios = cargarSocios();
        
        Socio socio = buscarPorValor(dni,0);
        
        socio.agregarVehiculo(vehiculo);
        
        userRepository.modificarSocio(socio);
    }
    
    @Override
    public void quitarVehiculoPorPatente(String patente){
        vehicleRepository = new VehiculoRepositoryFile();
        
        Vehiculo vehiculo = vehicleRepository.buscarPorValorS(patente,0);
        
        Socio socio = vehiculo.getPropietario();
                
        socio.quitarVehiculo(vehiculo);
        
        userRepository.modificarSocio(socio);
    }
    
    @Override
    public void mostrar(Socio socio) {
        System.out.println("Nombre: " + socio.getNombre() +
                       " | DNI: " + socio.getDNI() +
                       " | Teléfono: " + socio.getTelefono() +
                       " | Dirección: " + socio.getDireccion() +
                       " | Fecha ingreso: " + socio.getFechaIngreso());
    }
    
    @Override
    public void mostrarPorValor(Integer dni, Integer parametro) {
        mostrar(buscarPorValor(dni,parametro));
    }

    @Override
    public void listarAll() {
        socios = cargarSocios();
        System.out.println("=== Lista de Socios ===");
        for (Socio s : socios) {
            mostrar(s);
        }
    }
    
    @Override
    public void listar(ArrayList<Socio> socios) {
        System.out.println("=== Lista de Socios ===");
        for (Socio s : socios) {
            mostrar(s);
        }
    }
    
    @Override
    public void listarSocioVehiculos(Socio socio) {
        vehicleRepository = new VehiculoRepositoryFile();
        if(!(socio.getVehiculos()==null)){
            vehicleRepository.listar(socio.getVehiculos());
        }
    }
    
    @Override
    public void listarSocioGarages(Socio socio) {
        garageRepository = new GarageRepositoryFile();
        if(!(socio.getGarages()==null)){
            garageRepository.listar(socio.getGarages());
        }
    }

    @Override
    public void eliminar(Socio socio) {
        userRepository.eliminarUsuario(socio);
    }
    
    @Override
    public void eliminarPorValor(Integer dni, Integer parametro){
        Socio socio = buscarPorValor(dni,parametro);
        
        try{
            if(!socio.getGarages().isEmpty()){garageRepository.eliminarSocio(socio);}
            if(!socio.getVehiculos().isEmpty()){vehicleRepository.eliminarSocio(socio);}
            eliminar(socio);
            System.out.println("Se eliminó el socio DNI " + dni + ".");
        } catch (Exception e){
            System.out.println("Hubo un error al eliminar el socio:");
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public boolean tieneGarages(Socio propietario) {
        return (propietario.getGarages() != null);
    }
}
