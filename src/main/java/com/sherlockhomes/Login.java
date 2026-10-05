package com.sherlockhomes;

import com.sherlockhomes.models.Usuario;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import static com.sherlockhomes.Persistencia.cargarUsuarios;

public class Login {
    
    protected String username;
    protected String password;
    protected Usuario usuario;
    
    public Usuario ingresar() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        pedirCredenciales();
        
        usuario = autenticar(username, password);
        
        if(usuario == null){
            System.out.println("Credenciales inválidas.");
        }
            
        return usuario;
    }
    
    protected void pedirCredenciales(){
        System.out.print("Usuario: ");
        username = EntradaCons.ingresaString();

        System.out.print("Contraseña: ");
        password = EntradaCons.ingresaString();
    }

    protected Usuario autenticar(String username, String password) {
        ArrayList<Usuario> usuarios = cargarUsuarios();
        
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.validarPassword(password)) {
                return u;
            }
        }
        
        return null;
    }

}
