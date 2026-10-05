package com.sherlockhomes;

import com.sherlockhomes.models.Zona;
import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.models.Empleado;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Socio;
import java.io.*;
import java.util.ArrayList;

public class Persistencia {
    
    public static void guardarUsuarios(ArrayList<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"))) {
            oos.writeObject(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Usuario> cargarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.dat"))) {
            return (ArrayList<Usuario>) ois.readObject();
        } catch (Exception e) {
            ArrayList<Usuario> usuariosHard = new ArrayList<>();

            usuariosHard.add(new Usuario(0, "admin", 11111111, "1122223333", "CABA", "admin", "admin", TipoUsuario.ADMINISTRADOR));
            
            return usuariosHard; 
        }
    }
    
    public static ArrayList<Socio> cargarSocios() {
        ArrayList<Socio> socios = new ArrayList<>();
        
        for (Usuario u : cargarUsuarios()) {
            if (u instanceof Socio) {
                    socios.add((Socio) u); 
                }
        }
            
        return socios;
    }
        
    public static ArrayList<Empleado> cargarEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        for (Usuario u : cargarUsuarios()) {
            if (u instanceof Empleado) {
                    empleados.add((Empleado) u); 
                }
        }
            
        return empleados;
    }
    
    public static void guardarVehiculos(ArrayList<Vehiculo> vehiculos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vehiculos.dat"))) {
            oos.writeObject(vehiculos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Vehiculo> cargarVehiculos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vehiculos.dat"))) {
            return (ArrayList<Vehiculo>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>(); 
        }
    }
    
        public static void guardarGarages(ArrayList<Garage> garages) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("garages.dat"))) {
            oos.writeObject(garages);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Garage> cargarGarages() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("garages.dat"))) {
            return (ArrayList<Garage>) ois.readObject();
        } catch (Exception e) {
            ArrayList<Garage> garages = new ArrayList<>();
            ArrayList<Zona> zonas = cargarZonas();
            int id = 0;
            // 4 garages por cada zona
            for (int i = 0; i < 4; i++) {
                garages.add(new Garage(id++,zonas.get(0)));
                garages.add(new Garage(id++,zonas.get(1)));
                garages.add(new Garage(id++,zonas.get(2)));
                garages.add(new Garage(id++,zonas.get(3)));
                garages.add(new Garage(id++,zonas.get(4)));
            }
            guardarGarages(garages);
            return garages; 
        }
    }
    
        public static void guardarZonas(ArrayList<Zona> zonas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("zonas.dat"))) {
            oos.writeObject(zonas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Zona> cargarZonas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("zonas.dat"))) {
            return (ArrayList<Zona>) ois.readObject();
        } catch (Exception e) {
            Zona zonaA = new Zona("A", "Autos", 5, 3);
            Zona zonaB = new Zona("B", "Motos", 3, 2);
            Zona zonaC = new Zona("C", "Camionetas", 6, 4);
            Zona zonaD = new Zona("D", "Camiones", 10, 6);
            Zona zonaE = new Zona("E", "SUV", 7, 4);

            ArrayList<Zona> zonas = new ArrayList<>();
            zonas.add(zonaA);
            zonas.add(zonaB);
            zonas.add(zonaC);
            zonas.add(zonaD);
            zonas.add(zonaE);
            
            guardarZonas(zonas);
            return zonas; 
        }
    }
}
