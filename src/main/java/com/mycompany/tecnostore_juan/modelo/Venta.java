
package com.mycompany.tecnostore_juan.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venta {

    private int id;
    private Cliente cliente;
    private LocalDateTime fecha;
    private List<ItemVenta> items;

    public Venta(int id, Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                "Debe seleccionar un cliente."
            );
        }

        this.id = id;
        this.cliente = cliente;
        this.fecha = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemVenta item) {

        if (item == null) {
            throw new IllegalArgumentException(
                "El producto no puede estar vacio."
            );
        }

        items.add(item);
    }

    public double calcularSubtotal() {

        double subtotal = 0;

        for (ItemVenta item : items) {
            subtotal += item.calcularSubtotal();
        }

        return subtotal;
    }

    public double calcularIVA() {
        return calcularSubtotal() * 0.19;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<ItemVenta> getItems() {
        return items;
    }
}
