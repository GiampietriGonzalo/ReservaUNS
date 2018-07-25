package Clases.Principales;

import Clases.Estados.EstadoReserva;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class Prestamo {

    protected int id;
    protected String descripcion;
    protected String fecha;
    protected int idEstado;
    protected int idEspacio;
    protected int idHorario;

    public Prestamo(int id, String descripcion, String fecha, int idEspacio,int idHorario){
        this.id=id;
        this.descripcion=descripcion;
        this.idEspacio=idEspacio;
        this.idHorario=idHorario;
    }

    public void cancelar(){}

    public int getId() {
        return id;
    }

    /* HACER
        public EstadoReserva getEstado() {
            return estado;
        }*/

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    /*HACER
    public Espacio getEspacio() {
        return idEspacio;
    }*/


    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



}
