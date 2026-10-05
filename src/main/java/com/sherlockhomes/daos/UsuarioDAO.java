package com.sherlockhomes.daos;

import com.sherlockhomes.models.Usuario;
import java.util.ArrayList;

public interface UsuarioDAO {
    public int ultimoUsuario();
    public void agregarUsuario(Usuario usuario);
    public void mostrarUsuario(Usuario usuario);
    public void listarUsuariosAll();
    public void listarUsuarios(ArrayList<Usuario> usuarios);
    public void eliminarUsuario(Usuario usuario);
}
