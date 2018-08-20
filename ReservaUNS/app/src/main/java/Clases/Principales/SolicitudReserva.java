package Clases.Principales;

import java.util.LinkedList;

import Clases.DataBases.DBController;

/**
 * Created by gonza on 16/07/18.
 */

public class SolicitudReserva extends Solicitud{

    public SolicitudReserva(int id, int idEstado, int idAutor, LinkedList<Integer> horarios, String fecha, String descripcion, int capacidadEstimada) {
        super(id,idEstado,idAutor,horarios,fecha,descripcion,capacidadEstimada);
    }


    public boolean guardarSolicitud() {
        return DBController.insertSolicitudReserva(this);
    }
}
