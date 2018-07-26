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

        if(this.id==999)
            this.id= TablaEstadosReservas.getNextID(DBController.getDB());
    }
    public void cancelar(){}

    public int getId() {
        return id;
    }
}
