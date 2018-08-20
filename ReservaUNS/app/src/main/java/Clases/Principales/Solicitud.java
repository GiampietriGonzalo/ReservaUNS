package Clases.Principales;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaSolicitudes;
import Clases.Estados.EstadoSolicitud;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class Solicitud {

    protected int id;
    protected int idEstado;
    protected int idAutor;
    protected LinkedList<Integer> horarios;
    protected String fecha;
    protected String descripcion;
    protected int capacidadEstimada;

    public Solicitud(int id,int idEstado,int idAutor, LinkedList<Integer> horarios, String fecha, String descripcion, int capacidadEstimada){

        this.idEstado=idEstado;
        this.idAutor=idAutor;
        this.horarios=horarios;
        this.fecha=fecha;
        this.descripcion=descripcion;
        this.capacidadEstimada=capacidadEstimada;

        if(id==9999)
            this.id=TablaSolicitudes.getNextID(DBController.getDB());
        else
            this.id=id;

    }

    public abstract boolean guardarSolicitud();


    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }

    public int getCapacidadEstimada() {
        return capacidadEstimada;
    }



    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Integer> getHorarios() {
        return horarios;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public void setIdHorario(LinkedList<Integer> horarios) {
        this.horarios = horarios;
    }

    public void setCapacidadEstimada(int capacidadEstimada) {
        this.capacidadEstimada = capacidadEstimada;
    }


    //public void cancelar(){estado.cancelar();}
    //public void rechazar(){estado.rechazar();}
    //public void aceptar(){estado.aceptar();}
}
