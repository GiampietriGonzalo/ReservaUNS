package Clases.Principales;

public class Aula extends Espacio
{
    protected String numero;

    public Aula(String id, String nombre, int capacidad, Edificio edificio, String numero)
    {
        super(id, nombre, capacidad, edificio);
        this.numero = numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getNumero()
    {
        return numero;
    }
}
