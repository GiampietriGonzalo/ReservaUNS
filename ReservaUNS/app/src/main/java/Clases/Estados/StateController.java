package Clases.Estados;

public class StateController {

    private static Estado activo;
    private static Estado aceptado;
    private static Estado cancelado;
    private static Estado rechazado;
    private static StateController sc;

    private StateController(){

        activo= new Activo();
        cancelado= new Cancelado();
        rechazado= new Rechazado();
        aceptado= new Aceptado();

    }

    public static Estado getEstadoAceptado() {
        return aceptado;
    }

    public static Estado getEstadoActivo() {
        return activo;
    }

    public static Estado getEstadoRechazado() {
        return rechazado;
    }

    public static Estado getEstadoCancelado() {
        return cancelado;
    }

    public StateController getStateController(){

        if (sc==null)
            sc = new StateController();

        return sc;
    }


}
