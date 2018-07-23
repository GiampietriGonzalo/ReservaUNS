package Clases.Principales;

public class Aula extends Espacio {
    protected String nombreAnterior;

    public Aula(int id, String nombre, int capacidad, int idEdificio, String nombreAnterior, int piso, String cuerpo)
    {
        super(id, nombre, capacidad, idEdificio,piso,cuerpo);
        this.nombreAnterior=nombreAnterior;

    }


    public void setNombreAnterior(String nombreAnterior) {
        this.nombreAnterior = nombreAnterior;
    }

    public String getNombreAnterior() {
        return nombreAnterior;
    }
}
