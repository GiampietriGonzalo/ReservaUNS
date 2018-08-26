package Clases.Principales;

import android.content.Context;

import java.util.LinkedList;

public class EmpleadoDepEconomia extends EmpleadoDepartamento {

    public EmpleadoDepEconomia(int id, String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id, password, nombre, apellido, legajo, mail, telefono);
    }

    @Override
    public LinkedList<Solicitud> filtrarEspacios(Context context, LinkedList<Solicitud> solicitudes) {
        LinkedList<Solicitud> solicitudesEconomia = new LinkedList<>();
        return solicitudesEconomia;
    }
}
