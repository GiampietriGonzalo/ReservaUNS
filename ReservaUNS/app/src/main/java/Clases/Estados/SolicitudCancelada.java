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

}
