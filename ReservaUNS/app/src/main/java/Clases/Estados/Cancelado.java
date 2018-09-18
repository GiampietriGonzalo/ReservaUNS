package Clases.Estados;

public class Cancelado extends Estado {


    public Cancelado(){
        super(3);
    }

    public Estado aceptar() {
        return null;
    }

    public Estado cancelar() {
        return null;
    }

    public Estado rechazar() {
        return null;
    }

    public String toString(){return "Cancelado";}

    public Estado finalizar(){return null;}

    public Estado darDeBaja(){return StateController.getEstadoDeBaja();}

}
