package Clases.Principales;
import android.content.Context;
import android.support.design.widget.NavigationView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import pipenatr.Activities.R;

public class Docente extends Usuario {

    public Docente(int id,String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }


    public boolean actualizarUsuario() {
        return DBController.insertDocente(this);
    }

    public void actualizarNavView(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.nav_solicitar_reserva).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_consultar_solicitud).setVisible(true);
    }

    public LinkedList<Solicitud> filtrarEspacios(Context context) {
        DBController controller = DBController.getDBController(context);
        LinkedList<Solicitud> solicitudes = controller.findSolicitudesUsuario(id);
        return solicitudes;
    }
}
