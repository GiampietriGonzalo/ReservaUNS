package Clases.Principales;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaPrestamos;
import Clases.Estados.Estado;

/**
 * Created by gonza on 16/07/18.
 */

public class Asignacion extends Prestamo{

    protected String fechaDesde;
    protected String fechaHasta;

    public Asignacion(int id, String fecha, int idHorario, int idEspacio, String fechaDesde, String fechaHasta, Estado estado){
        super(id,fecha,idEspacio,idHorario,estado);
        this.fechaDesde=fechaDesde;
        this.fechaHasta=fechaHasta;
    }


    public boolean guardarPrestamo() {
        return TablaPrestamos.insertAsignacion(this, DBController.getDB());
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void cancelar(){
        //hacer
    }


}
