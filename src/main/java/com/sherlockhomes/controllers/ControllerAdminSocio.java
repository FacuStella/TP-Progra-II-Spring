package com.sherlockhomes.controllers;

import com.sherlockhomes.EntradaCons;
import com.sherlockhomes.models.Socio;
import com.sherlockhomes.SocioRepositoryFile;
import com.sherlockhomes.models.Usuario;
import com.sherlockhomes.views.admin.VistaAdminSocios;


public class ControllerAdminSocio implements ControllerAdminMenu<Usuario> {
    
    protected SocioRepositoryFile associatedRepository;
    private VistaAdminSocios vistaGestionSocios;
    private int accion;
    
    public ControllerAdminSocio(){
        associatedRepository = new SocioRepositoryFile();
        vistaGestionSocios = new VistaAdminSocios();
    }
    
    @Override
    public void iniciar(Usuario u){
        do{
            vistaGestionSocios.menu();
            accion = EntradaCons.ingresaInt();
            ejecutar(u);
        } while(accion == -1);
    }
    
    @Override
    public void ejecutar(Usuario usuarioLogueado) {
        switch (accion) {
            case 1 -> registrar();
            case 2 -> modificar();
            case 3 -> eliminar();
            case 4 -> listarSocioVehiculos();
            case 5 -> listarSocioGarages();
            case 6 -> associatedRepository.listarAll();
            case 0 -> {}
            default -> vistaGestionSocios.noReconocida();
        }
    }
    
    public void registrar() {
        System.out.println("=== Registro de nuevo Socio ===");

        System.out.print("Ingrese Nombre: ");
        String nombre = EntradaCons.ingresaString();

        System.out.print("Ingrese DNI: ");
        int DNI = EntradaCons.ingresaInt();
        
        if(associatedRepository.existePorValor(DNI,0)){
            System.out.println("Socio ya existe.");
            return;
        }

        System.out.print("Ingrese dirección: ");
        String direccion = EntradaCons.ingresaString();

        System.out.print("Ingrese teléfono: ");
        String telefono = EntradaCons.ingresaString();

        try{ 
            Socio aux = new Socio(nombre, DNI, direccion, telefono);
            associatedRepository.crear(aux);
            System.out.println("Se agregó el socio exitosamente.");
            associatedRepository.mostrarPorValor(DNI,0);
        } catch(Exception e) {
            System.out.println("Fallo registro de socio." + e.getMessage());
        }
    }

    public void modificar() {
        System.out.println("=== Modificar Socio ===");
                
        System.out.print("Ingrese DNI: ");
        int DNI = EntradaCons.ingresaInt();
        EntradaCons.ingresaString();

        if(!associatedRepository.existePorValor(DNI,0)){
            System.out.println("Socio no encontrado.");
            return;
        }

        System.out.println("Modificar direccion o vacio: ");
        String direccion = EntradaCons.ingresaString();

        System.out.println("Modificar telefono o vacio: ");
        String telefono = EntradaCons.ingresaString();
        
        Socio aux = new Socio("", DNI, direccion, telefono);
        associatedRepository.modificarPorValor(DNI,0, aux);

        System.out.println("Se modificó el socio DNI " + DNI + ".");
    }

    public void eliminar() {
        System.out.println("=== Eliminar Socio ===");
        
        int DNI;

        System.out.println("Ingrese DNI: ");
        DNI = EntradaCons.ingresaInt();
        EntradaCons.ingresaString();
        
        if(!associatedRepository.existePorValor(DNI,0)){
            System.out.println("Socio no encontrado.");
            return;
        }

        associatedRepository.eliminarPorValor(DNI,0);
    }

    private void listarSocioVehiculos() {
        System.out.println("=== Muestra vehiculos de un socio ===");
        System.out.print("Ingrese DNI: ");
        int DNI = EntradaCons.ingresaInt();

        if(!associatedRepository.existePorValor(DNI,0)){
            System.out.println("Socio no encontrado.");
            return;
        }
        
        associatedRepository.listarSocioVehiculos(associatedRepository.buscarPorValor(DNI,0));
    }
    
    private void listarSocioGarages() {
        System.out.println("=== Muestra garages de un socio ===");
        System.out.print("Ingrese DNI: ");
        int DNI = EntradaCons.ingresaInt();

        if(!associatedRepository.existePorValor(DNI,0)){
            System.out.println("Socio no encontrado.");
            return;
        }
        
        associatedRepository.listarSocioGarages(associatedRepository.buscarPorValor(DNI,0));
    }
}
