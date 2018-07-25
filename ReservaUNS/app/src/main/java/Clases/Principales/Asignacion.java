package Clases.Principales;

import Clases.Estados.EstadoReserva;

/**
 * Created by gonza on 16/07/18.
 */

public class Asignacion extends Prestamo{

    protected String fechaDesde;
    protected String fechaHasta;

    public Asignacion(int id, String descripcion, String fecha, int idHorario, int idEspacio, String fechaDesde, String fechaHasta){
        super(id,descripcion,fecha,idEspacio,idHorario);
        this.fechaDesde=fechaDesde;
        this.fechaHasta=fechaHasta;
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
