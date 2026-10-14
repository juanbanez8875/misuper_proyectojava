
package com.mycompany.tecnostore_juan.servicio;

import com.mycompany.tecnostore_juan.modelo.Celular;
import java.util.ArrayList;
import java.util.List;

public class GestorCelulares {

    private List<Celular> celulares = new ArrayList<>();

    public void registrar(Celular celular) {
        if (celular == null) {
            throw new IllegalArgumentException(
                "El celular no puede ser nulo."
            );
        }

        if (buscarPorId(celular.getId()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un celular con ese ID."
            );
        }

        celulares.add(celular);
    }

    public List<Celular> listar() {
        return new ArrayList<>(celulares);
    }

    public Celular buscarPorId(int id) {
        for (Celular celular : celulares) {
            if (celular.getId() == id) {
                return celular;
            }
        }
        return null;
    }

    public boolean actualizar(Celular actualizado) {
        if (actualizado == null) {
            throw new IllegalArgumentException(
                "El celular no puede ser nulo."
            );
        }

        for (int i = 0; i < celulares.size(); i++) {
            if (celulares.get(i).getId() == actualizado.getId()) {
                celulares.set(i, actualizado);
                return true;
            }
        }

        return false;
    }

    public boolean eliminar(int id) {
        Celular celular = buscarPorId(id);

        if (celular != null) {
            celulares.remove(celular);
            return true;
        }

        return false;
    }
}

