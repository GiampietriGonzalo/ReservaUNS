package Clases.Estados;

import Clases.DataBases.DBController;

/**
 * Created by gonza on 16/07/18.
 */

public class SolicitudAceptada extends EstadoSolicitud {

    public SolicitudAceptada(int id, int idSolicitud){
        super(id,idSolicitud);
    }

    public boolean guardarEstadoSolicitud(){
        return DBController.insertSolicitudAceptada(this);
    }

    @Override
    public String getEstado() {
        return "aceptada";
    }

}
