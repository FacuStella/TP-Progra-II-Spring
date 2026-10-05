package com.sherlockhomes.daos;

import java.util.ArrayList;

public interface GerericDAO<Object>  {
    public void crear(Object t);
    public void mostrar(Object t);
    public void listarAll();
    public void listar(ArrayList<Object> list);
    public void eliminar(Object t);
}