package com.sherlockhomes.daos;

import com.sherlockhomes.daos.GerericDAO;
import com.sherlockhomes.daos.GenericStringDAO;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.models.Empleado;

public interface VehiculoDAO extends GerericDAO<Vehiculo>,GenericStringDAO<Vehiculo,Integer,String> {
    public boolean tieneGarageAsignado(String patente);
    public boolean tieneEmpleadoAsignado(String patente);
    public void asignarVehiculoGarage(String patente, int garage);
    public void asignarVehiculoEmpleado(String patente, Empleado empleado);
    public void quitarGarageVehiculo(int numero);
    public void quitarVehiculoGarage(String patente);
    public void quitarVehiculoEmpleado(String patente);
    public void mostrarVehiculoGarage(Vehiculo vehiculo);
}
