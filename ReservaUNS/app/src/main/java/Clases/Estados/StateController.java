package Clases.Estados;

public class StateController {

    private static Estado activo;
    private static Estado aceptado;
    private static Estado cancelado;
    private static Estado rechazado;
    private static Estado finalizado;
    private static StateController sc;

    private StateController(){

        activo= new Activo();
        cancelado= new Cancelado();
        rechazado= new Rechazado();
        aceptado= new Aceptado();
        finalizado= new Finalizado();

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

    public static Estado getEstadoFinalizado() {
        return finalizado;
    }

    public static StateController getStateController(){

        if (sc==null)
            sc=new StateController();

        return sc;
    }


}
