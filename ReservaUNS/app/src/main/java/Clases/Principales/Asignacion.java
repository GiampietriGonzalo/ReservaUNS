package Clases.Principales;

import Clases.Estados.EstadoReserva;

/**
 * Created by gonza on 16/07/18.
 */

public class Asignacion extends Prestamo{

    protected int numAsignacion;
    protected String fechaDesde;
    protected String fechaHasta;
    protected Horario horario;

    public Asignacion(int id, String descripcion, String fecha, int numAsignacion,String fechaDesde, String fechaHasta, Horario horario){
        super(id,descripcion,fecha);
        this.numAsignacion=numAsignacion;
        this.fechaDesde=fechaDesde;
        this.fechaHasta=fechaHasta;
        this.horario=horario;
    }

    public Horario getHorario() {
        return horario;
    }

    public int getNumAsignacion() {
        return numAsignacion;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void cancelar(){estado.cancelar();}


}
