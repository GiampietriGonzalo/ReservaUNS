package Clases.Principales;

import Clases.DataBases.DBController;

public class EmpleadoSecretaria extends Usuario{

    public EmpleadoSecretaria(String cuenta, String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(cuenta,password,legajo,nombre,apellido,mail,telefono);
    }

    public boolean actualizarUsuario(){
        return DBController.insertEmpleadoSecretaria(this);
    }

}
