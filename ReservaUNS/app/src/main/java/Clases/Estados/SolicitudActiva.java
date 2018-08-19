package Clases.Estados;

import Clases.DataBases.DBController;
import Clases.Principales.Solicitud;

/**
 * Created by gonza on 16/07/18.
 */

public class SolicitudActiva extends EstadoSolicitud{

    public SolicitudActiva(int id, int idSolicitud){
        super(id,idSolicitud);
    }

    public boolean guardarEstadoSolicitud(){
        return DBController.insertSolicitudActiva(this);
    }


    public void aceptar() {
        Solicitud solicitud= DBController.findSolicitud(idSolicitud);
        EstadoSolicitud nuevo= new SolicitudAceptada(9999,idSolicitud);
        nuevo.guardarEstadoSolicitud();
        solicitud.setIdEstado(nuevo.getId());
    }

    public void cancelar() {
        Solicitud solicitud= DBController.findSolicitud(idSolicitud);
        EstadoSolicitud nuevo= new SolicitudCancelada(9999,idSolicitud);
        nuevo.guardarEstadoSolicitud();
        solicitud.setIdEstado(nuevo.getId());
    }

    public void rechazar() {
        Solicitud solicitud= DBController.findSolicitud(idSolicitud);
        EstadoSolicitud nuevo= new SolicitudRechazada(9999,idSolicitud);
        nuevo.guardarEstadoSolicitud();
        solicitud.setIdEstado(nuevo.getId());
    }

    @Override
    public String getEstado() {
        return "activa";
    }
}
