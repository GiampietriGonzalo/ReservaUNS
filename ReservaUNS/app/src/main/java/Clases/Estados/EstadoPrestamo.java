package Clases.Estados;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEstadosReservas;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class EstadoPrestamo {

    protected int id;
    protected int idPrestamo;


    public EstadoPrestamo(int id, int idPrestamo){
        this.id=id;
        this.idPrestamo=idPrestamo;
        TablaEstadosReservas.getNextID(DBController.getDB());

        if(this.id==9999)
            this.id= TablaEstadosReservas.getNextID(DBController.getDB());
    }

    abstract public boolean guardarEstado();

    public void cancelar(){}

    public int getId() {
        return id;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public void setId(int id) {
        this.id = id;
    }

}