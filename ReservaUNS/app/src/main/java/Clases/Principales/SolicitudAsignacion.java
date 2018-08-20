package Clases.Principales;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaSolicitudes;

/**
 * Created by gonza on 16/07/18.
 */

public class SolicitudAsignacion extends Solicitud {

    public SolicitudAsignacion(int id, int idEstado, int idAutor, LinkedList<Integer> horarios, String fecha, String descripcion, int capacidadEstimada) {
        super(id,idEstado,idAutor,horarios,fecha,descripcion,capacidadEstimada);
    }

    public boolean guardarSolicitud(){
        return DBController.insertSolicitudAsignacion(this);
    }

}

