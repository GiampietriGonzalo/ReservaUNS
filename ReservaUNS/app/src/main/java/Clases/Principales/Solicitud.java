package Clases.Principales;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaSolicitudes;
import Clases.Estados.Estado;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class Solicitud {

    protected int id;
    protected int idAutor;
    protected LinkedList<Integer> horarios;
    protected String fecha;
    protected int idEspacio ;
    protected int capacidadEstimada;
    protected Estado estado;

    public Solicitud(int id,int idAutor, LinkedList<Integer> horarios, String fecha, int idEspacio, int capacidadEstimada, Estado estado){

        this.idAutor=idAutor;
        this.horarios=horarios;
        this.fecha=fecha;
        this.idEspacio=idEspacio;
        this.capacidadEstimada=capacidadEstimada;

        if(id==9999)
            this.id=TablaSolicitudes.getNextID(DBController.getDB());
        else
            this.id=id;

        this.estado=estado;
    }

    public abstract boolean guardarSolicitud();

    public String getEstadoString(){return estado.toString();}

    public int getIdEspacio() {
        return idEspacio;
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

    public void setIdEspacio(int idEspacio) {
        this.idEspacio=idEspacio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Integer> getHorarios() {
        return horarios;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setIdHorario(LinkedList<Integer> horarios) {
        this.horarios = horarios;
    }

    public void cancelar(){estado=estado.cancelar();}
    public void aceptar(){estado=estado.aceptar();}
    public void rechazar(){estado=estado.rechazar();}

    public void setCapacidadEstimada(int capacidadEstimada) {
        this.capacidadEstimada = capacidadEstimada;
    }

}
