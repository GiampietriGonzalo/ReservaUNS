package Clases;

public class SalaReuniones extends Espacio
{
    protected String numero;

    public SalaReuniones(String id, String nombre, int capacidad, Edificio edificio, String numero)
    {
        super(id, nombre, capacidad, edificio);
        this.numero = numero;
    }

    public void ssetNumero(String numero)
    {
        this.numero = numero;
    }

    public String getNumero()
    {
        return numero;
    }
}
