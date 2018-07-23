package Clases.Principales;

import Clases.DataBases.DBController;

public abstract class Espacio
{
    protected int id;
    protected String nombre;
    protected int capacidad;
    protected int idEdificio;
    protected int piso;
    protected String cuerpo;

    public Espacio(int id, String nombre, int capacidad, int idEdificio, int piso, String cuerpo) {
        this.id = id;
        this.nombre= nombre;
        this.capacidad = capacidad;
        this.idEdificio = idEdificio;
        this.piso=piso;
        this.cuerpo=cuerpo;
    }

    public int getID()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getPiso() {
        return piso;
    }

    public String getCuerpo() {
        return cuerpo;
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
