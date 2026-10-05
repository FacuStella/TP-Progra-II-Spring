package com.sherlockhomes.controllers;

import com.sherlockhomes.EntradaCons;
import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.views.admin.VistaAdministrador;

public class ControllerAdmin implements Controller<Usuario> {
    
    private VistaAdministrador vistaAdmin;
    private ControllerAdminMenu controllerAdmin;
    private int accion;
    
    public ControllerAdmin(){
        vistaAdmin = new VistaAdministrador();
    }
    
    @Override
    public void iniciar(Usuario u){
        do{
            vistaAdmin.menu();
            accion = EntradaCons.ingresaInt();
            ejecutar(u);
        } while(accion != 0);
    }
    

    @Override
    public void ejecutar(Usuario usuarioLogueado) {
        controllerAdmin = null;
        switch (accion) {
            case 1 -> controllerAdmin = new ControllerAdminSocio();
            case 2 -> controllerAdmin = new ControllerAdminVehiculo();
            case 3 -> controllerAdmin = new ControllerAdminGarage();
            case 4 -> controllerAdmin = new ControllerAdminZona();
            case 5 -> controllerAdmin = new ControllerAdminEmpleado();  
            case 0 -> vistaAdmin.salir();
            default -> {}
        }
        if (controllerAdmin != null){
            controllerAdmin.iniciar(usuarioLogueado);
        }
    }
    
    

}
