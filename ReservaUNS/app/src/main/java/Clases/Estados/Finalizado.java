package Clases.Estados;



public class Finalizado extends Estado {

    public Finalizado(){
        super(4);
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

    public String toString(){return "Finalizado";}

    public Estado finalizar(){return this;}

    public Estado darDeBaja(){return StateController.getEstadoDeBaja();}

}
