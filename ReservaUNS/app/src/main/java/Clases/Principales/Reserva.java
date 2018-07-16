package Clases.Principales;

public class Reserva
{
    protected String id;
    protected String fecha;
    protected String hora;
    protected String duracion;
    protected String estado;
    protected Espacio espacio;
    protected Docente responsable;

    public Reserva(String id, String fecha, String hora, String duracion, Espacio espacio, Docente responsable)
    {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.espacio = espacio;
        this.responsable = responsable;
        estado = "activa";
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public void setHora(String hora)
    {
        this.hora = hora;
    }

    public void setDuracion(String duracion)
    {
        this.duracion = duracion;
    }

    public void cancelar()
    {
        estado = "cancelada";
    }

    public void activa()
    {
        estado = "activa";
    }

    public void setEspacio(Espacio espacio)
    {
        this.espacio = espacio;
    }

    public void setResponsable(Docente responsable)
    {
        this.responsable = responsable;
    }

    public String getId()
    {
        return id;
    }

    public String getFecha()
    {
        return fecha;
    }

    public String getHora()
    {
        return hora;
    }

    public String getDuracion()
    {
        return duracion;
    }

    public String getEstado()
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
