package com.sherlockhomes;

import com.sherlockhomes.daos.UsuarioDAO;
import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.models.Empleado;
import com.sherlockhomes.models.Socio;
import java.util.ArrayList;
import java.util.Iterator;
import static com.sherlockhomes.Persistencia.cargarUsuarios;
import static com.sherlockhomes.Persistencia.guardarUsuarios;

public class UsuarioRepositoryFile implements UsuarioDAO {
    
    ArrayList<Usuario> usuarios;
    
    public UsuarioRepositoryFile(){}
    
    @Override
    public int ultimoUsuario() {
        usuarios = cargarUsuarios();
        
        return usuarios.get(usuarios.size() - 1).getId();
    }
    
    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarios = cargarUsuarios();
        
        usuarios.add(usuario);
        
        guardarUsuarios(usuarios);
    }
    
    @Override
    public void mostrarUsuario(Usuario usuario) {
        System.out.println("Id: " + usuario.getId() +
                            " | Usuario: " + usuario.getUserName() +
                            " | Tipo usuario: " + usuario.getTipoUsuario());
    }
    
    @Override
    public void listarUsuariosAll() {
        System.out.println("=== Lista de Usuarios ===");
        for (Usuario u : usuarios) {
            mostrarUsuario(u);
        }
    }

    public void listarUsuarios(ArrayList<Usuario> usuarios) {
        System.out.println("=== Lista de Usuarios ===");
        for (Usuario u : usuarios) {
            mostrarUsuario(u);
        }
    }

    public void modificarSocio(Socio socio) {
        usuarios = cargarUsuarios();
        
        for (Usuario u : usuarios) {
            if (u.getId() == socio.getId() && u instanceof Socio) {
                Socio socioAux = (Socio) u;
                socioAux.setTelefono(socio.getTelefono());
                socioAux.setDireccion(socio.getDireccion());
                socioAux.asignarVehiculos(socio.getVehiculos());
                socioAux.asignarGarages(socio.getGarages());
                break; 
            }
        }
        
        guardarUsuarios(usuarios);
    }
    
    public void modificarEmpleado(Empleado empleado) {
        usuarios = cargarUsuarios();
        
        for (Usuario u : usuarios) {
            if (u.getId() == empleado.getId() && u instanceof Empleado) {
                Empleado empleadoAux = (Empleado) u;
                empleadoAux.setTelefono(empleado.getTelefono());
                empleadoAux.setDireccion(empleado.getDireccion());
                empleadoAux.setEspecialidad(empleado.getEspecialidad());
                empleadoAux.asignarVehiculos(empleado.getVehiculosAsignados());
                empleadoAux.asignarZonas(empleado.getZonasAsignadas());
                break; 
            }
        }
        
        guardarUsuarios(usuarios);
    }
      
    
    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarios = cargarUsuarios();
        
        Iterator<Usuario> it = usuarios.iterator();
        while (it.hasNext()) {
            Usuario usuarioAux = it.next();
            if (usuarioAux.getId() == usuario.getId()) {
                it.remove(); 
            }
        }
        
        guardarUsuarios(usuarios);
    }
    
}
