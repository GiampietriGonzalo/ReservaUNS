package Clases.Estados;

import Clases.DataBases.DBController;

public class Activo extends Estado {


    public Activo(){
        super(0);
    }

    public Estado aceptar() {
        return DBController.getEstadoAceptado();
    }

    public Estado cancelar() {
        return DBController.getEstadoCancelado();
    }

    public Estado rechazar() {
        return DBController.getEstadoRechazado();
    }

    public String toString(){return "Activo";}

}
