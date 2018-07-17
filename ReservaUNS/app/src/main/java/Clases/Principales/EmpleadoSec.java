package Clases.Principales;

public class EmpleadoSec extends Usuario{

    protected String id;
    protected String nombre;
    protected String apellido;
    protected String lu;

    public EmpleadoSec(String cuenta, String password, String id, String nombre, String apellido, String lu)
    {
        super(cuenta,password);
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lu = lu;
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

}
