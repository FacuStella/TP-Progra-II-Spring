package com.sherlockhomes.models;

import java.io.Serializable;
import jakarta.persistence.*;
import com.sherlockhomes.TipoUsuario;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends Persona implements Serializable {
    private static final long serialVersionUID = 1L; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int ID;

    protected String username;
    protected String password;

    @Enumerated(EnumType.STRING)
    protected TipoUsuario tipoUsuario;
    
    // Constructor vacío (Obligatorio para JPA)
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

    // Getters y Setters necesarios para JPA
    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validarPassword(String password) {
        return this.password != null && this.password.equals(password);
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public String toCSV() {
        return ID + "," + username + "," + password + "," + tipoUsuario;
    }
}