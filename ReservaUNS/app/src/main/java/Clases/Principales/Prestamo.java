package Clases.Principales;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEspacios;
import Clases.DataBases.TablaHorarios;
import Clases.DataBases.TablaPrestamos;
import Clases.Estados.Estado;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class Prestamo {

    protected int id;
    protected String fecha;
    protected int idEspacio;
    protected int idHorario;
    protected Estado estado;

    public Prestamo(int id, String fecha, int idEspacio,int idHorario, Estado estado){

        this.idEspacio=idEspacio;
        this.idHorario=idHorario;
        this.fecha=fecha;
        if(id==9999)
            this.id = TablaPrestamos.getNextID(DBController.getDB());
        else
            this.id=id;

        this.estado=estado;
    }

     abstract public boolean guardarPrestamo();

    public void finalizar(){estado=estado.finalizar();}

    public int getId() {
        return id;
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

    public int getIdEspacio() {
        return idEspacio;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void darDeBaja(){estado=estado.darDeBaja();}

    public void setId(int id) {
        this.id = id;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstadoString(){return estado.toString();}

}
