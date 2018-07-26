package Clases.Principales;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEstadosReservas;
import Clases.Estados.EstadoReserva;
import Clases.Estados.PrestamoActivo;

public class Reserva extends Prestamo{

    protected int idDocente;

    public Reserva(int id, String descripcion,String fecha, int idHorario, int idEspacio, int idDocente) {
        super(id,descripcion,fecha,idEspacio,idHorario);
        this.idHorario=idHorario;
        this.idEspacio = idEspacio;
        this.idDocente = idDocente;
        EstadoReserva aux = new PrestamoActivo(TablaEstadosReservas.getNextID(DBController.getDB()));
        this.idEstado=aux.getId();
    }


    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public void setHorario(Horario horario) {
        idHorario = horario.getId();
    }

    /*Hacer

    public void cancelar(){
        estado.cancelar();
    }*/

    public void setEspacio(Espacio espacio) {
        idEspacio = espacio.getID();
    }

    public void setResponsable(Docente responsable) {
        idDocente = responsable.getId();
    }

    /*HACER
    public Horario getHorario() {
        return horario;
    }

    public EstadoReserva getEstado() {
        return estado;
    }


    public Espacio getEspacio(){
        return espacio;
    }

    public Docente getResponsable() {
        return responsable;
    }*/
}
