package Clases.Principales;

import android.content.Context;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import pipenatr.Activities.SaveSharedPreference;

public class EmpleadoDepEconomia extends EmpleadoDepartamento {

    public EmpleadoDepEconomia(int id, String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id, password, nombre, apellido, legajo, mail, telefono);
    }

    @Override
    public LinkedList<Solicitud> filtrarEspacios(Context context, LinkedList<Solicitud> solicitudes) {
        DBController controller = DBController.getDBController(context);
        LinkedList<Solicitud> solicitudesEconomia = new LinkedList<>();
        Edificio edificio;
        Solicitud solicitud ;

        //Para cada elemento de la lista de solicitudes recibida
        for(int i = 0; i<solicitudes.size(); i++) {
            solicitud = solicitudes.get(i);
            edificio = controller.findEdificio(solicitud.getIdEspacio());
            //Verifica que el encargado del edificio de la reserva sea el usuario
            if(edificio.getEncargado().getId() == Integer.parseInt(SaveSharedPreference.getUserId(context)))
                solicitudesEconomia.addLast(solicitud);
        }
        return solicitudesEconomia;
    }
}