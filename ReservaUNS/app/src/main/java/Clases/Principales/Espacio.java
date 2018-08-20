package Clases.Principales;

import android.os.NetworkOnMainThreadException;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEspacios;

public abstract class Espacio
{
    protected int id;
    protected String nombre;
    protected int capacidad;
    protected int idEdificio;
    protected int piso;
    protected String cuerpo;

    public Espacio(int id, String nombre, int capacidad, int idEdificio, int piso, String cuerpo) {

        this.nombre= nombre;
        this.capacidad = capacidad;
        this.idEdificio = idEdificio;
        this.piso=piso;
        this.cuerpo=cuerpo;

        if(id==9999)
            this.id= TablaEspacios.getNextID(DBController.getDB());
        else
            this.id=id;

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
        return DBController.findEdificio(idEdificio);
    }

    public String toString(){
        return ""+nombre;
    }

}
