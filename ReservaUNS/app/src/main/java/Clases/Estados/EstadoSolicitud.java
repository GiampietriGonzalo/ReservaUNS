package Clases.Estados;

import Clases.DataBases.DBController;
import Clases.DataBases.Tabla;
import Clases.DataBases.TablaEstadosSolicitud;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class EstadoSolicitud {

    protected int id;
    protected int idSolicitud;

    public EstadoSolicitud(int id, int idSolicitud){

        this.idSolicitud=idSolicitud;

        if(id==9999)
            this.id= TablaEstadosSolicitud.getNextID(DBController.getDB());
        else
            this.id=id;

    }

    abstract public boolean guardarEstadoSolicitud();

    public int getId() {
        return id;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public void cancelar(){}
    public void aceptar(){}
    public void rechazar(){}

    public abstract String getEstado();
}
