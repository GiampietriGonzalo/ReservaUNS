package Clases.Estados;

public class Rechazado extends Estado {

    public Rechazado(){
        super(2);
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

    public String toString(){return "Rechazado";}

    public Estado finalizar(){return null;}

    public Estado darDeBaja(){return null;}


}
