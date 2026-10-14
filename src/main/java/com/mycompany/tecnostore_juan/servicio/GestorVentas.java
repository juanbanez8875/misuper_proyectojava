
package com.mycompany.tecnostore_juan.servicio;

import com.mycompany.tecnostore_juan.modelo.Celular;
import com.mycompany.tecnostore_juan.modelo.ItemVenta;
import com.mycompany.tecnostore_juan.modelo.Venta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorVentas {

    private List<Venta> ventas = new ArrayList<>();

    // Registrar una venta y descontar el inventario
    public void registrar(Venta venta) {

        if (venta == null) {
            throw new IllegalArgumentException(
                "La venta no puede ser nula."
            );
        }

        if (buscarPorId(venta.getId()) != null) {
            throw new IllegalArgumentException(
                "Ya existe una venta con ese ID."
            );
        }

        if (venta.getItems().isEmpty()) {
            throw new IllegalArgumentException(
                "La venta debe tener al menos un producto."
            );
        }

        // Agrupar las cantidades por ID de celular.
        // Esto evita vender más unidades de las disponibles
        // cuando un celular aparece varias veces en la venta.
        Map<Integer, Integer> cantidades = new HashMap<>();
        Map<Integer, Celular> celulares = new HashMap<>();

        for (ItemVenta item : venta.getItems()) {

            if (item == null || item.getCelular() == null) {
                throw new IllegalArgumentException(
                    "Hay un producto inválido en la venta."
                );
            }

            Celular celular = item.getCelular();
            int idCelular = celular.getId();

            cantidades.merge(
                idCelular,
                item.getCantidad(),
                Integer::sum
            );

            celulares.putIfAbsent(idCelular, celular);
        }

        // Primero verificar TODO el stock.
        // No descontamos nada si algún producto no alcanza.
        for (Map.Entry<Integer, Integer> entrada
                : cantidades.entrySet()) {

            Celular celular = celulares.get(entrada.getKey());
            int cantidadSolicitada = entrada.getValue();

            if (celular.getStock() < cantidadSolicitada) {
                throw new IllegalArgumentException(
                    "Stock insuficiente para: "
                    + celular.getModelo()
                );
            }
        }

        // Si todos los productos tienen stock, descontar.
        for (Map.Entry<Integer, Integer> entrada
                : cantidades.entrySet()) {

            Celular celular = celulares.get(entrada.getKey());

            celular.setStock(
                celular.getStock() - entrada.getValue()
            );
        }

        ventas.add(venta);
    }

    // Listar todas las ventas
    public List<Venta> listar() {
        return new ArrayList<>(ventas);
    }

    // Buscar una venta por ID
    public Venta buscarPorId(int id) {

        for (Venta venta : ventas) {
            if (venta.getId() == id) {
                return venta;
            }
        }

        return null;
    }
}
