
package com.mycompany.tecnostore_juan.modelo;

public class ItemVenta {

    private Celular celular;
    private int cantidad;

    public ItemVenta(Celular celular, int cantidad) {

        if (celular == null) {
            throw new IllegalArgumentException(
                "Debe seleccionar un celular."
            );
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                "La cantidad debe ser mayor que cero."
            );
        }

        this.celular = celular;
        this.cantidad = cantidad;
    }

    public Celular getCelular() {
        return celular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double calcularSubtotal() {
        return celular.getPrecio() * cantidad;
    }
}