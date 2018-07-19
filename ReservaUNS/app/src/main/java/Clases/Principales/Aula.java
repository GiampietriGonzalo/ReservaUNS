package Clases.Principales;

public class Aula extends Espacio {
    protected int numero;
    protected String nombreAnterior;

    public Aula(String id, String nombre, int capacidad, Edificio edificio, int numero, String nombreAnterior)
    {
        super(id, nombre, capacidad, edificio);
        this.numero = numero;
        this.nombreAnterior=nombreAnterior;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public int getNumero()
    {
        return numero;
    }

    public void setNombreAnterior(String nombreAnterior) {
        this.nombreAnterior = nombreAnterior;
    }

    public String getNombreAnterior() {
        return nombreAnterior;
    }
}
