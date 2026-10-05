package com.sherlockhomes.daos;

public interface GenericStringDAO<Object, Integer, String> {
    
    public boolean existePorValorS(String v, Integer p);
    public Object buscarPorValorS(String v, Integer p);
    public void modificarPorValorS(String v, Integer p, Object t);
    public void mostrarPorValorS(String v, Integer p);
    public void eliminarPorValorS(String v, Integer p);
}
