package Clases.Principales;

import android.content.Context;
import android.support.design.widget.NavigationView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import pipenatr.Activities.R;
import pipenatr.Activities.SaveSharedPreference;

public class EmpleadoSecretaria extends Usuario{

    public EmpleadoSecretaria(int id,String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }

    public boolean actualizarUsuario(){
        return DBController.insertEmpleadoSecretaria(this);
    }

    @Override
    public void actualizarNavView(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.nav_consultar_asignaciones).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_anular_prestamo).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_evaluar_prestamo).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_consultar_prestamo).setVisible(true);
    }

    @Override
    public LinkedList<Solicitud> filtrarEspacios(Context context) {
        DBController controller = DBController.getDBController(context);
        LinkedList<Solicitud> solicitudes = controller.getSolicitudes();
        LinkedList<Solicitud> solicitudesAulas = new LinkedList<>();
        Edificio edificio;
        Solicitud solicitud ;

        //Para cada elemento de la lista de solicitudes recibida
        for(int i = 0; i<solicitudes.size(); i++) {
            solicitud = solicitudes.get(i);
            edificio = controller.findEdificio(solicitud.getIdEspacio());
            //Verifica que el encargado del edificio de la reserva sea el usuario
            if(edificio.getEncargado().getId() == Integer.parseInt(SaveSharedPreference.getUserId(context)))
                solicitudesAulas.addLast(solicitud);
        }
        return solicitudesAulas;
    }
}
