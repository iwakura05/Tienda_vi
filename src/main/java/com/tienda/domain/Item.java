package com.tienda.domain;

public class Item extends Producto {
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Item() {
        
    }
    
    public Item(Producto p) {
        super.setActivo(p.isActivo());
        super.setCategoria(p.getCategoria());
        super.setDescripcion(p.getDescripcion());
        super.setDetalle(p.getDetalle());
        super.setExistencias(p.getExistencias());
        super.setIdProducto(p.getIdProducto());
        super.setPrecio(p.getPrecio());
        super.setRutaImagen(p.getRutaImagen());
        cantidad=0;
    }
}
