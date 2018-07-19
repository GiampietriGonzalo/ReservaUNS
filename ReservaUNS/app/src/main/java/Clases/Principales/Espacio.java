package Clases.Principales;

public abstract class Espacio
{
    protected String id;
    protected String nombre;
    protected int capacidad;
    protected Edificio edificio;

    public Espacio(String id, String nombre, int capacidad, Edificio edificio)
    {
        this.id = id;
        this.nombre= nombre;
        this.capacidad = capacidad;
        this.edificio = edificio;
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

    public Edificio getEdifiio()
    {
        return edificio;
    }

}
