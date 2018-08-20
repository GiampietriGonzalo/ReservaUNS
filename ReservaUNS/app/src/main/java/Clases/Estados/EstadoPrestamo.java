package Clases.Estados;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEstadosPrestamos;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class EstadoPrestamo {

    protected int id;
    protected int idPrestamo;


    public EstadoPrestamo(int id, int idPrestamo){

        this.idPrestamo=idPrestamo;
        TablaEstadosPrestamos.getNextID(DBController.getDB());

        if(this.id==9999)
            this.id= TablaEstadosPrestamos.getNextID(DBController.getDB());
        else
            this.id=id;
    }

    abstract public boolean guardarEstado();

    public void cancelar(){}
    public void aceptar(){}

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
