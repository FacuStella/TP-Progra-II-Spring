package com.sherlockhomes.daos;

import com.sherlockhomes.daos.GerericDAO;
import com.sherlockhomes.daos.GenericIntDAO;
import com.sherlockhomes.models.Zona;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.models.Empleado;

public interface EmpleadoDAO extends GerericDAO<Empleado>,GenericIntDAO<Empleado,Integer>{
    public int ultimoEmpleado();
    public void asignarEmpleadoZona(int codigo, Zona zona);
    public void asignarEmpleadoVehiculo(int codigo, Vehiculo vehiculo);
    public void quitarEmpleadoZona(int codigo, Zona zona);
    public void quitarEmpleadoVehiculo(int codigo, Vehiculo vehiculo);
    public void listarEmpleadoVehiculos(Empleado empleado);
    public void listarEmpleadoZonas(Empleado empleado);
}
