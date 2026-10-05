package com.sherlockhomes.daos;

import com.sherlockhomes.daos.GerericDAO;
import com.sherlockhomes.daos.GenericIntDAO;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Socio;
import java.util.ArrayList;

public interface GarageDAO extends GerericDAO<Garage>,GenericIntDAO<Garage, Integer>{
    public int ultimoGarage();
    public ArrayList<Garage> buscarGaragePorSocio(Socio socio);
    public boolean tieneVehiculoAsignado(int numero);
    public boolean tienePropietario(int numero);
    public void asignarGarageVehiculo(int garage, String patente);
    public void quitarGarageVehiculo(int garage);
    public void quitarVehiculoGarage(String patente);
    public void eliminarSocio(Socio socio);
    
}
