package Clases.Principales;

import Clases.DataBases.DBController;

public class EmpleadoDepartamento extends Usuario{

    public EmpleadoDepartamento(int id, String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }

    public boolean actualizarUsuario() {
        return DBController.insertEmpleadoDepartamento(this);
    }
}
