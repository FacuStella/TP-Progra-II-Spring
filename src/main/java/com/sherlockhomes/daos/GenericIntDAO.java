package com.sherlockhomes.daos;

public interface GenericIntDAO <Object, Integer>{  
    public Object buscarPorValor(Integer v, Integer p);
    public boolean existePorValor(Integer v, Integer p);
    public void modificarPorValor(Integer v, Integer p, Object t);
    public void mostrarPorValor(Integer v, Integer p);
    public void eliminarPorValor(Integer v, Integer p); 
}
