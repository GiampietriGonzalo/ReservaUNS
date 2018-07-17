package Clases.Principales;

import Clases.Estados.EstadoReserva;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class Prestamo {

    protected int id;
    protected String descripcion;
    protected String fecha;
    protected EstadoReserva estado;

    public Prestamo(int id, String descripcion, String fecha){
        this.id=id;
        this.descripcion=descripcion;
    }

    public void cancelar(){}

    public int getId() {
        return id;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
