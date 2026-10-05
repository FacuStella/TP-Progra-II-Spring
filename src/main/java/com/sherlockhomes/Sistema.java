package com.sherlockhomes;

import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.models.Empleado;
import com.sherlockhomes.models.Socio;
import com.sherlockhomes.controllers.ControllerAdmin;
import com.sherlockhomes.controllers.ControllerSocio;
import com.sherlockhomes.controllers.ControllerEmpleado;
import static com.sherlockhomes.TipoUsuario.*;

public class Sistema {
    protected Usuario usuarioLogueado;
    protected ControllerEmpleado controllerEmpleado;
    protected ControllerSocio controllerSocio;
    protected ControllerAdmin controllerAdmin;
   
    public Sistema () {
        controllerEmpleado = new ControllerEmpleado();
        controllerSocio = new ControllerSocio();
        controllerAdmin = new ControllerAdmin();
    }
    
    public void iniciar() { 
        logueaUsuario();
        if(!(usuarioLogueado == null)){
            switch (usuarioLogueado.getTipoUsuario()) {
                case EMPLEADO -> controllerEmpleado.iniciar((Empleado) usuarioLogueado);
                case SOCIO -> controllerSocio.iniciar((Socio) usuarioLogueado);
                case ADMINISTRADOR -> controllerAdmin.iniciar(usuarioLogueado);
            }
        }
    }
    
    protected void logueaUsuario() {
        Login login = new Login();
        usuarioLogueado = login.ingresar();
    }   
}
