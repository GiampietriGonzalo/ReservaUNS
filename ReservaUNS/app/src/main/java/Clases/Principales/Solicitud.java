package Clases.Principales;

import Clases.Estados.EstadoSolicitud;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class Solicitud {

    protected int numSolicitud;
    protected int id;
    protected EstadoSolicitud estado;
    protected Usuario autor;
    protected Horario horario;
    protected String fecha;
    protected String descripcion;
    protected int capacidadEstimada;

    public Horario getHorario() {
        return horario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public int getCapacidadEstimada() {
        return capacidadEstimada;
    }

    public int getNumSolicitud() {
        return numSolicitud;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setCapacidadEstimada(int capacidadEstimada) {
        this.capacidadEstimada = capacidadEstimada;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public void setNumSolicitud(int numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public void cancelar(){estado.cancelar();}
    public void rechazar(){estado.rechazar();}
    public void aceptar(){estado.aceptar();}
}
