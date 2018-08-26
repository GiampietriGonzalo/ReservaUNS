package Clases.Principales;

import android.content.Context;

import java.util.LinkedList;

import Clases.DataBases.DBController;

public class EmpleadoDCIC extends EmpleadoDepartamento {

    public EmpleadoDCIC(int id, String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id, password, nombre, apellido, legajo, mail, telefono);
    }

    @Override
    public LinkedList<Solicitud> filtrarEspacios(Context context, LinkedList<Solicitud> solicitudes) {
        DBController controller = DBController.getDBController(context);
        LinkedList<Solicitud> solicitudesDCIC = new LinkedList<>();
        Solicitud solicitud = null;
        LinkedList<Solicitud> solicitudesUsuario = null;
        boolean encontre = false;

        for(int i = 0; i<solicitudes.size(); i++) {
            solicitud = solicitudes.get(i);
            solicitudesUsuario = controller.findSolicitudesUsuario(solicitud.getIdAutor());
            for( int j = 0; j<solicitudesUsuario.size() && !encontre; j++) {
                if(solicitudesUsuario.get(j).getId() == solicitud.getId()) {

                }
            }
        }
        return solicitudesDCIC;
    }
}
