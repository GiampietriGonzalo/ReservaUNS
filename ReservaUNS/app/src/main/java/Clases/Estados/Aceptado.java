package Clases.Estados;

public class Aceptado extends Estado{

    public Aceptado(){
        super(1);
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

    public String toString(){return "Aceptado";}

    public Estado finalizar(){return null;}


}
