package Clases.Principales;
import android.content.Context;
import android.content.Intent;

import Clases.DataBases.DBController;
import pipenatr.Activities.PantallaPrincipal;

public class Docente extends Usuario {

    public Docente(int id,String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }


    public boolean actualizarUsuario() {
        return DBController.insertDocente(this);
    }

    @Override
    public void iniciarSesion(Context context)
    {
        Intent intent = new Intent(context, PantallaPrincipal.class);
        context.startActivity(intent);
    }

}
