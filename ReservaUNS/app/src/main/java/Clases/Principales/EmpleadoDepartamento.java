package Clases.Principales;

import android.content.Context;
import android.support.design.widget.NavigationView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import pipenatr.Activities.R;
import pipenatr.Activities.SaveSharedPreference;

public class EmpleadoDepartamento extends Usuario{

    public EmpleadoDepartamento(int id, String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }

    public boolean actualizarUsuario() {
        return DBController.insertEmpleadoDepartamento(this);
    }
    
    public void actualizarNavView(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.nav_administrar_docentes).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_administrar_solicitudes_departamento);
    }

    public LinkedList<Solicitud> filtrarEspacios(Context context) {
        DBController controller = DBController.getDBController(context);
        LinkedList<Solicitud> solicitudes = controller.getSolicitudes();
        LinkedList<Solicitud> solicitudesDepto = new LinkedList<Solicitud>();
        Edificio edificio;
        Solicitud solicitud ;

        //Para cada elemento de la lista de solicitudes recibida
        for(int i = 0; i<solicitudes.size(); i++) {
            solicitud = solicitudes.get(i);
            edificio = controller.findEdificio(solicitud.getIdEspacio());
            //Verifica que el encargado del edificio de la reserva sea el usuario
            if(edificio.getEncargado().getId() == Integer.parseInt(SaveSharedPreference.getUserId(context)))
                solicitudesDepto.addLast(solicitud);
        }
        return solicitudesDepto;
    }
}

