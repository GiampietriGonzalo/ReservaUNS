package Clases.Principales;

public class Docente
{
    protected String nombre;
    protected String apellido;
    protected String lu;
    protected String contraseña;
    protected String mail;
    protected String telefono;

    public Docente(String nombre, String apellido, String lu, String contraseña, String mail, String telefono)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.lu = lu;
        this.contraseña = contraseña;
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

    public void setContraseña(String contraseña)
    {
        this.contraseña = contraseña;
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

    public String getContraseña()
    {
        return contraseña;
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
