package Clases.Estados;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class EstadoReserva {

    protected int id;
    public EstadoReserva(int id){
        this.id=id;
    }
    public void cancelar(){}

    public int getId() {
        return id;
    }
}
