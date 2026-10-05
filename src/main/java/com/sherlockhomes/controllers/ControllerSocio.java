package com.sherlockhomes.controllers;

import com.sherlockhomes.views.socio.VistaSocio;
import com.sherlockhomes.EntradaCons;
import com.sherlockhomes.models.Socio;
import com.sherlockhomes.SocioRepositoryFile;

public class ControllerSocio implements Controller<Socio> {
    
    private SocioRepositoryFile asocciatedRepository;
    private VistaSocio vistaSocio;
    private int accion; 
    
    public ControllerSocio(){
        asocciatedRepository = new SocioRepositoryFile();
        vistaSocio = new VistaSocio();
    }
    
    @Override
    public void iniciar(Socio s){
            do{
                vistaSocio.menu();
                accion = EntradaCons.ingresaInt();
                ejecutar(s);
            } while(accion != 0);
    }

    @Override
    public void ejecutar(Socio socioAux) {
        switch (accion) {
            case 1 -> asocciatedRepository.listarSocioVehiculos(socioAux);
            case 2 -> asocciatedRepository.listarSocioGarages(socioAux);
            case 0 -> vistaSocio.salir();
            default -> vistaSocio.noReconocida();
        }
    }
    

    
}