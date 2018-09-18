package Clases.Estados;

import Clases.DataBases.DBController;

public class Activo extends Estado {


    public Activo(){
        super(0);
    }

    public Estado aceptar() {
        return StateController.getEstadoAceptado();
    }

    public Estado cancelar() {
        return StateController.getEstadoCancelado();
    }

    public Estado rechazar() {
        return StateController.getEstadoRechazado();
    }

    public String toString(){return "Activo";}

    public Estado finalizar(){return StateController.getEstadoFinalizado();}

    public Estado darDeBaja(){return StateController.getEstadoDeBaja();}

}
