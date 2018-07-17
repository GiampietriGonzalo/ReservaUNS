package Clases.Principales;

import java.util.ArrayList;

public class Edificio {
    protected String id;
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected Usuario encargado;
    protected ArrayList<Espacio> espacios;

    public Edificio(String id, String nombre, String direccion, String telefono, Usuario encargado)
    {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion; this.telefono = telefono;
        this.encargado = encargado;
        espacios = new ArrayList<Espacio>();
    }

    public Edificio(String id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public void setId(String id)
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

    public void setEncargado(Usuario encargado)
    {
        this.encargado = encargado;
    }

    public void addEspacio(Espacio espacio)
    {
        espacios.add(espacio);
    }

    public String getId()
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

    public Usuario getEncargado()
    {
        return encargado;
    }

    public ArrayList<Espacio> getEspacios()
    {
        return espacios;
    }
}
