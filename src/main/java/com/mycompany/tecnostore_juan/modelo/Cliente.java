
package com.mycompany.tecnostore_juan.modelo;

public class Cliente extends Persona {

    private String identificacion;
    private String correo;
    private String telefono;

    public Cliente(int id, String nombre, String identificacion,
                   String correo, String telefono) {

        super(id, nombre);

        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombre()
                + " | ID: " + getId()
                + " | Identificación: " + identificacion
                + " | Correo: " + correo
                + " | Teléfono: " + telefono;
    }
}
