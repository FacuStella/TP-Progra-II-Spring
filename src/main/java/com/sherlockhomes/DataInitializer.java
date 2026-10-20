package com.sherlockhomes;

import com.sherlockhomes.daos.*;
import com.sherlockhomes.models.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            SocioDAO socioDAO,
            EmpleadoDAO empleadoDAO,
            ZonaDAO zonaDAO,
            GarageDAO garageDAO,
            VehiculoDAO vehiculoDAO) {
        
        return args -> {
            // Verificar si la BD está vacía antes de insertar
            if (socioDAO.count() == 0 && zonaDAO.count() == 0) {
                try {
                    // 1. Zona
                    Zona zonaA = new Zona();
                    zonaA.setLetra("A");
                    zonaA.setAnchoGarage(3);
                    zonaA.setProfundidadGarage(6);
                    zonaA.setTipoVehiculos("Autos y Camionetas");
                    zonaA.setNumeroVehiculos(10);
                    zonaDAO.save(zonaA);

                    // 2. Socio
                    Socio socio1 = new Socio("Gian", 12345678, "Av. Cabildo 1234", "1122334455");
                    socioDAO.save(socio1);

                    // 3. Empleado
                    Empleado emp1 = new Empleado("Carlos Perez", 87654321, "Calle Siempre Feliz 123", "1199887766", "Mantenimiento");
                    emp1.asignarZona(zonaA);
                    empleadoDAO.save(emp1);

                    // 4. Vehiculo
                    Vehiculo auto1 = new Vehiculo("AA123CD", "Toyota", "Corolla", "Medium", socio1);
                    auto1.asignarEmpleado(emp1);
                    vehiculoDAO.save(auto1);

                    // 5. Garage
                    Garage garage1 = new Garage();
                    garage1.setZona(zonaA);
                    garage1.setPropietario(socio1);
                    garage1.setVehiculoOcupante(auto1);
                    garage1.setLecturaContadorLuz(150.5f);
                    garage1.setMantenimientoContratado(true);
                    garageDAO.save(garage1);

                    System.out.println(">>> Datos iniciales cargados correctamente <<<");
                } catch (Exception e) {
                    System.err.println("Error al cargar datos iniciales: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        };
    }
}