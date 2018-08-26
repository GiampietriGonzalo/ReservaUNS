package Clases.Principales;

import android.content.Context;
import android.support.design.widget.NavigationView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import pipenatr.Activities.R;

public class EmpleadoDepartamento extends Usuario{

    public EmpleadoDepartamento(int id, String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }

    public boolean actualizarUsuario() {
        return DBController.insertEmpleadoDepartamento(this);
    }

    @Override
    public void actualizarNavView(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.grupoEmpDepartamento).setVisible(true);
    }

    @Override
    public LinkedList<Solicitud> filtrarEspacios(Context context, LinkedList<Solicitud> solicitudes) {
        return null;
    }
}
