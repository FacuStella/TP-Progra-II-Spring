package com.sherlockhomes.models;

import java.io.Serializable;
import com.sherlockhomes.TipoUsuario;

public class Usuario extends Persona implements Serializable {
    private static final long serialVersionUID = 1L; 

    protected int ID;
    protected String username;
    protected String password;
    protected TipoUsuario tipoUsuario;
    
    public Usuario() {
    }
    
    public Usuario(String nombre, int DNI, String direccion, String telefono) {
        super(nombre, DNI, direccion, telefono);
    }

    public Usuario(int id, String nombre, int DNI, String direccion, String telefono, String username, String password, TipoUsuario tipoUsuario) {
        super(nombre, DNI, direccion, telefono);
        this.ID = id;
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsername() {
        return username;
    }

    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    
    public String toCSV() {
        return ID + "," + username + "," + password + "," + tipoUsuario;
    }

    public int getId() {
        return ID;
    }

    public String getUserName() {
        return username;
    }

}
