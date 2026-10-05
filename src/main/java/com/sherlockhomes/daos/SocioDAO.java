package com.sherlockhomes.daos;

import com.sherlockhomes.daos.GerericDAO;
import com.sherlockhomes.daos.GenericIntDAO;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Socio;

public interface SocioDAO extends GerericDAO<Socio>,GenericIntDAO<Socio,Integer> {
    public void comprarGarage(int DNI, Garage garage);
    public void asignarSocioVehiculo(int DNI, Vehiculo vehiculo);
    public void quitarVehiculoPorPatente(String patente);
    public void listarSocioVehiculos(Socio socio);
    public void listarSocioGarages(Socio socio);
    public boolean tieneGarages(Socio propietario);
}
