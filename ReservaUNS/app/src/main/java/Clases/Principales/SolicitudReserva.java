package Clases.Principales;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Estados.Estado;

/**
 * Created by gonza on 16/07/18.
 */

public class SolicitudReserva extends Solicitud{

    public SolicitudReserva(int id, int idAutor, LinkedList<Integer> horarios, String fecha, int idEspacio, int capacidadEstimada, Estado estado) {
        super(id,idAutor,horarios,fecha,idEspacio,capacidadEstimada,estado);
    }


    public boolean guardarSolicitud() {
        return DBController.insertSolicitudReserva(this);
    }
}
