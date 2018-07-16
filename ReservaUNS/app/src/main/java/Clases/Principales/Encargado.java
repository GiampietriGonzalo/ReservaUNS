package Clases.Principales;

public class Encargado
{
    protected String id;
    protected String nombre;
    protected String apellido;
    protected String lu;
    protected String contraseña;

    public Encargado(String id, String nombre, String apellido, String lu, String contraseña)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lu = lu;
        this.contraseña= contraseña;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public void setLu(String lu)
    {
        this.lu = lu;
    }

    public void setContraseña(String contraseña)
    {
        this.contraseña = contraseña;
    }

    public String getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public String getLu()
    {
        return lu;
    }

    public String getContraseña()
    {
        return contraseña;
    }
}
