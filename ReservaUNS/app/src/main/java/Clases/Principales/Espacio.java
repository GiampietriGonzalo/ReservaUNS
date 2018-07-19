package Clases.Principales;

import Clases.DataBases.DBController;

public abstract class Espacio
{
    protected String id;
    protected String nombre;
    protected int capacidad;
    protected String nombreEdificio;

    public Espacio(String id, String nombre, int capacidad, String nombreEdificioedificio)
    {
        this.id = id;
        this.nombre= nombre;
        this.capacidad = capacidad;
        this.nombreEdificio = nombreEdificioedificio;
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

    public Edificio getEdificio() {
        //SOBREESCRIBIR EN CADA CLASE HIJO CON DEPARTAMENTE
        return null;
    }

}
