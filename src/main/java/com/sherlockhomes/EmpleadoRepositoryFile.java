package com.sherlockhomes;

import com.sherlockhomes.daos.EmpleadoDAO;
import com.sherlockhomes.models.Zona;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.models.Empleado;
import java.util.ArrayList;
import static com.sherlockhomes.Persistencia.cargarEmpleados;

public class EmpleadoRepositoryFile implements EmpleadoDAO{
    
    protected ArrayList<Empleado> empleados;
    protected UsuarioRepositoryFile userRepository;
    protected ZonaRepositoryFile zoneRepository;
    protected VehiculoRepositoryFile vehicleRepository;
    
    public EmpleadoRepositoryFile(){
        userRepository = new UsuarioRepositoryFile();
    }
    
    @Override
    public void crear(Empleado empleado){ 
        int ultimoId = userRepository.ultimoUsuario();
        
        int ultimoCodigo = ultimoEmpleado();
        
        Empleado empleadoAux = new Empleado(ultimoId+1,ultimoCodigo+1, empleado.getNombre(), empleado.getDNI(), empleado.getDireccion(), empleado.getTelefono(), empleado.getEspecialidad());

        userRepository.agregarUsuario(empleadoAux);
    }
    
    @Override
    public Empleado buscarPorValor(Integer valor, Integer parametro ) {
        empleados = cargarEmpleados();
        
        switch(parametro){
            case 1: 
                for (Empleado e : empleados) {
                    if (e.getDNI() == valor) {
                        return e;
                    }
                }
                break;
            case 2:
                for (Empleado e : empleados) {
                if (e.getCodigo() == valor) {
                        return e;
                    }
                }
                break;
            default: return null; 
        }
        return null;
    }
    
    @Override
    public boolean existePorValor(Integer valor, Integer parametro){
        return (buscarPorValor(valor,parametro) != null);
    }
    
    @Override
    public void modificarPorValor(Integer valor, Integer parametro, Empleado empleado){
        empleados = cargarEmpleados();
        
        Empleado empleadoAux =  buscarPorValor(valor, parametro);
        
        if(!empleado.getDireccion().isBlank()){empleadoAux.setDireccion(empleado.getDireccion());}
        if(!empleado.getTelefono().isBlank()){empleadoAux.setTelefono(empleado.getTelefono());}
        if(!empleado.getEspecialidad().isBlank()){empleadoAux.setEspecialidad(empleado.getEspecialidad());}
          
        userRepository.modificarEmpleado(empleado);
    }
    
    @Override
    public void asignarEmpleadoZona(int codigo, Zona zona){
        Empleado empleado = buscarPorValor(codigo, 2);
                
        empleado.asignarZona(zona);
        
        userRepository.modificarEmpleado(empleado);
    }
    
    @Override
    public void asignarEmpleadoVehiculo(int codigo, Vehiculo vehiculo){
        Empleado empleado = buscarPorValor(codigo, 2);
                
        empleado.asignarVehiculo(vehiculo);
        
        userRepository.modificarEmpleado(empleado);
    }
    
    @Override
    public void mostrar(Empleado empleado) {
        System.out.println("Nombre: " + empleado.getNombre() +
                           " | Codigo: " + empleado.getCodigo()+
                           " | DNI: " + empleado.getDNI() +
                           " | Teléfono: " + empleado.getTelefono() +
                           " | Dirección: " + empleado.getDireccion());
    }
    
    @Override
    public void mostrarPorValor(Integer valor, Integer parametro){
        mostrar(buscarPorValor(valor,parametro));
    }
    
    @Override
    public void listarAll() {
        empleados = cargarEmpleados();
        System.out.println("=== Lista de Empleados ===");
        for (Empleado e : empleados) {
            mostrar(e);
        }
    }

    @Override
    public void listar(ArrayList<Empleado> empleados) {
        System.out.println("=== Lista de Empleados ===");
        for (Empleado e : empleados) {
            mostrar(e);
        }
    }
    
    @Override
    public void listarEmpleadoVehiculos(Empleado empleado) {
        vehicleRepository = new VehiculoRepositoryFile();
        if(!(empleado.getVehiculosAsignados() == null)){
            vehicleRepository.listar(empleado.getVehiculosAsignados());
        }
    }

    @Override
    public void listarEmpleadoZonas(Empleado empleado) {
        zoneRepository = new ZonaRepositoryFile();
        if(!(empleado.getZonasAsignadas() == null)){
            for (Zona z : empleado.getZonasAsignadas()){
                int vehiculosAsignadosZona = 0;
                zoneRepository.mostrar(z);
                for(Vehiculo v : empleado.getVehiculosAsignados()){
                    if(v.getGarageAsignado().getZona().equals(z.getLetra())){
                        vehiculosAsignadosZona++;
                    }
                }
                System.out.println("Vehiculos asignados:" + vehiculosAsignadosZona);
            }
        }
    }

    @Override
    public void eliminar(Empleado empleado) {
        userRepository.eliminarUsuario(empleado);
    }

    @Override
    public void eliminarPorValor(Integer valor, Integer parametro) {
        Empleado empleado = buscarPorValor(valor, parametro);
        
        try{
            zoneRepository.eliminarEmpleado(empleado);
            vehicleRepository.eliminarEmpleado(empleado);
            eliminar(empleado);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void quitarEmpleadoZona(int codigo, Zona zona) {
        Empleado empleado = buscarPorValor(codigo, 2);
                
        empleado.quitarZona(zona);
        
        userRepository.modificarEmpleado(empleado);
    }
    
    @Override
    public void quitarEmpleadoVehiculo(int codigo, Vehiculo vehiculo) {
        Empleado empleado = buscarPorValor(codigo, 2);
                
        empleado.quitarVehiculo(vehiculo);
        
        userRepository.modificarEmpleado(empleado);
    }
    
    @Override
    public int ultimoEmpleado() {
        empleados = cargarEmpleados();
        
        if (empleados.isEmpty()) {
            return 0;
        }

        return empleados.get(empleados.size() - 1).getCodigo();
    }
    
}
