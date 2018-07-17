package Clases.Principales;

public class Docente extends Usuario {
    protected String nombre;
    protected String apellido;
    protected String lu;
    protected String mail;
    protected String telefono;

    public Docente(String cuenta, String password, String nombre, String apellido, String lu, String mail, String telefono)
    {
        super(cuenta,password);
        this.nombre = nombre;
        this.apellido = apellido;
        this.lu = lu;
        this.mail = mail;
        this.telefono = telefono;
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

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
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

    public String getMail()
    {
        return mail;
    }

    public String getTelefono()
    {
        return telefono;
    }
}
