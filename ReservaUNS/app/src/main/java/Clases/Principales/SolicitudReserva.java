package Clases.Principales;

import Clases.DataBases.DBController;

/**
 * Created by gonza on 16/07/18.
 */

public class SolicitudReserva extends Solicitud{

    public SolicitudReserva(int id,int idEstado,int idAutor, int idHorario, String fecha, String descripcion, int capacidadEstimada) {
        super(id,idEstado,idAutor,idHorario,fecha,descripcion,capacidadEstimada);
    }


    public boolean guardarSolicitud() {
        return DBController.insertSolicitudReserva(this);
    }
}
