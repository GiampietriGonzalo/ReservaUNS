package Clases;

public abstract class Espacio
{
    protected String id;
    protected String nombre;
    protected int capacidad;
    protected boolean disponible;
    protected Edificio edificio;

    public Espacio(String id, String nombre, int capacidad, Edificio edificio)
    {
        this.id = id;
        this.nombre= nombre;
        this.capacidad = capacidad;
        this.edificio = edificio;
        disponible = true;
    }

    public String getID()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getCapacidad()
    {
        return capacidad;
    }

    public boolean getDisponible()
    {
        return disponible;
    }

    public Edificio getEdifiio()
    {
        return edificio;
    }

    public void setDisponibilidad(boolean estado)
    {
        disponible = estado;
    }
}
