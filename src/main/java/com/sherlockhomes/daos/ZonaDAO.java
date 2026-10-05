package com.sherlockhomes.daos;

import com.sherlockhomes.daos.GerericDAO;
import com.sherlockhomes.daos.GenericStringDAO;
import com.sherlockhomes.models.Zona;
import com.sherlockhomes.models.Empleado;

public interface ZonaDAO extends GerericDAO<Zona>, GenericStringDAO<Zona,Integer,String> {
    public void listarZonaGarages(Zona zona);
    public void asignarZonaEmpleado(String letra, int codigo);
    public void quitarZonaEmpleado(String letra, Empleado empleado);
}
