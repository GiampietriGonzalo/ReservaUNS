package Clases.Principales;

import java.util.ArrayList;
import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEdificios;

public abstract class Edificio {
    protected int id;
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected int idEncargado;
    protected LinkedList<Integer> espacios;

    public Edificio(int id, String nombre, String direccion, String telefono, int idEncargado){

        this.nombre = nombre;
        this.direccion = direccion; this.telefono = telefono;
        this.idEncargado = idEncargado;
        espacios = new LinkedList<Integer>();

        if(id==9999)
            this.id= TablaEdificios.getNextID(DBController.getDB());
        else
            this.id=id;
    }



    public Edificio(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public void setEncargado(int idEncargado)
    {
        this.idEncargado = idEncargado;
    }

    public void addEspacio(Espacio espacio)
    {
        espacios.add(espacio.getID());
    }

    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public Usuario getEncargado() {
        return DBController.findUsuario(idEncargado);
    }

    public LinkedList<Espacio> getEspacios() {
        LinkedList<Integer> idEdficios = DBController.findEspaciosDeEdificio(id);
        LinkedList<Espacio> toReturn= new LinkedList<Espacio>();

        for(Integer id: idEdficios)
            toReturn.addLast(DBController.findEspacio(id));

        return toReturn;
    }

    public void setEspacios(LinkedList<Integer> espacios) {
        this.espacios = espacios;
    }
}
