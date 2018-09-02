package Clases.Estados;

import Clases.DataBases.DBController;

/**
 * Created by gonza on 16/07/18.
 */

public class SolicitudCancelada extends EstadoSolicitud {

    public SolicitudCancelada(int id, int idSolicitud){
        super(id,idSolicitud);
    }

    public boolean guardarEstadoSolicitud(){
        return DBController.insertSolicitudCancelada(this);
    }

    @Override
    public String getEstado() {
        return "cancelada";
    }

    @Override
    public boolean estaActivo() {
        return false;
    }

}
