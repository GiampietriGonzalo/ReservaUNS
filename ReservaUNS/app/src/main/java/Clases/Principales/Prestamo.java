package Clases.Principales;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEspacios;
import Clases.DataBases.TablaEstadosReservas;
import Clases.DataBases.TablaHorarios;
import Clases.DataBases.TablaPrestamos;
import Clases.Estados.EstadoPrestamo;

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

        if(id==9999)
            id = TablaPrestamos.getNextID(DBController.getDB());
    }

     abstract public  boolean guardarPrestamo();

    public void cancelar(){}

    public int getId() {
        return id;
    }


    public EstadoPrestamo getEstado() {
            return TablaEstadosReservas.findEstado(idEspacio,DBController.getDB());
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }


    public Espacio getEspacio() {
        return TablaEspacios.findEspacio(idEspacio,DBController.getDB());
    }

    public Horario getHorario() {
        return TablaHorarios.findHorario(idHorario,DBController.getDB());
    }



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

    public int getIdEspacio() {
        return idEspacio;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public int getIdHorario() {
        return idHorario;
    }
}
