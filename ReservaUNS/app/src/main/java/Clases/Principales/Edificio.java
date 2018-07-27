package Clases.Principales;

import java.util.ArrayList;
import java.util.LinkedList;

import Clases.DataBases.DBController;

public abstract class Edificio {
    protected int id;
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected int idEncargado;
    protected LinkedList<Integer> espacios;

    public Edificio(int id, String nombre, String direccion, String telefono, int idEncargado)
    {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion; this.telefono = telefono;
        this.idEncargado = idEncargado;
        espacios = new LinkedList<Integer>();
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

    /*public void addEspacio(Espacio espacio)
    {
        espacios.add(espacio);
    }*/

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

    public LinkedList<Integer> getEspacios() {
        //BUSCAR EN LA BD LOS ESPACIOS CON LOS ID DE LA LISTA, ARMAR UNA LISTA DE ESPACIOS Y RETORNARLA

        return espacios;
    }

    public void setEspacios(LinkedList<Integer> espacios) {
        this.espacios = espacios;
    }
}
