package Clases.Estados;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEstadosPrestamos;
import Clases.Principales.Prestamo;

/**
 * Created by gonza on 16/07/18.
 */

public class PrestamoActivo extends  EstadoPrestamo{

    public PrestamoActivo(int id, int idPrestamo){
        super(id, idPrestamo);
    }

    public boolean guardarEstado() {
        return TablaEstadosPrestamos.insertPrestamoActivo(this, DBController.getDB());
    }


    public void cancelar(){
        Prestamo p= DBController.findPrestamo(idPrestamo);
        EstadoPrestamo nuevo= new PrestamoCancelado(9999,idPrestamo);
        nuevo.guardarEstado();
        p.setEstado(nuevo.getId());
    }
}
