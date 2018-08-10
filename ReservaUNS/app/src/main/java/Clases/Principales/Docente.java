package Clases.Principales;

import Clases.DataBases.DBController;

public class Docente extends Usuario {



    public Docente(int id,String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }


    public boolean actualizarUsuario() {
        return DBController.insertDocente(this);
    }
}
