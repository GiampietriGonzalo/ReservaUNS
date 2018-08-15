package Clases.Estados;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEstadosPrestamos;
import Clases.Principales.Prestamo;

/**
 * Created by gonza on 16/07/18.
 */

public class PrestamoCancelado extends  EstadoPrestamo {

    public PrestamoCancelado(int id, int idPrestamo){
        super(id,idPrestamo);
    }

    public boolean guardarEstado() {
        return TablaEstadosPrestamos.insertPrestamoCancelado(this, DBController.getDB());
    }



}
