package Clases.Principales;

import Clases.Estados.EstadoReserva;
import Clases.Estados.ReservaActiva;

public class Reserva extends Prestamo{

    protected Horario horario;
    protected Espacio espacio;
    protected Docente responsable;

    public Reserva(int id, String descripcion,String fecha, Horario horario, Espacio espacio, Docente responsable)
    {
        super(id,descripcion,fecha);
        this.horario=horario;
        this.espacio = espacio;
        this.responsable = responsable;
        estado = new ReservaActiva();
    }


    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public void setHorario(Horario horario)
    {
        this.horario = horario;
    }

    public void cancelar()
    {
        estado.cancelar();
    }

    public void setEspacio(Espacio espacio)
    {
        this.espacio = espacio;
    }

    public void setResponsable(Docente responsable)
    {
        this.responsable = responsable;
    }

    public String getFecha()
    {
        return fecha;
    }

    public Horario getHorario()
    {
        return horario;
    }

    public EstadoReserva getEstado()
    {
        return estado;
    }

    public Espacio getEspacio()
    {
        return espacio;
    }

    public Docente getResponsable()
    {
        return responsable;
    }
}
