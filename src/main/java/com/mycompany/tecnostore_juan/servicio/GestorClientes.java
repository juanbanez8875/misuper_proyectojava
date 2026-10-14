
package com.mycompany.tecnostore_juan.servicio;

import com.mycompany.tecnostore_juan.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class GestorClientes {

    private List<Cliente> clientes = new ArrayList<>();

    // Registrar un cliente
    public void registrar(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                "El cliente no puede ser nulo."
            );
        }

        if (buscarPorId(cliente.getId()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un cliente con ese ID."
            );
        }

        for (Cliente existente : clientes) {
            if (existente.getIdentificacion().equals(
                    cliente.getIdentificacion())) {
                throw new IllegalArgumentException(
                    "La identificación ya está registrada."
                );
            }
        }

        if (cliente.getCorreo() == null
                || !cliente.getCorreo().matches(
                    "^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException(
                "El correo no tiene un formato válido."
            );
        }

        clientes.add(cliente);
    }

    // Listar clientes
    public List<Cliente> listar() {
        return new ArrayList<>(clientes);
    }

    // Buscar por ID
    public Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }

        return null;
    }
}
