package Clases.Estados;

import Clases.DataBases.DBController;

/**
 * Created by gonza on 16/07/18.
 */

public class SolicitudRechazada  extends EstadoSolicitud{

    public SolicitudRechazada(int id, int idSolicitud){
        super(id,idSolicitud);
    }

    public boolean guardarEstadoSolicitud(){
        return DBController.insertSolicitudRechazada(this);
    }

    @Override
    public String getEstado() {
        return "rechazada";
    }

}

