package Clases.Principales;

/**
 * Created by gonza on 18/07/18.
 */

public class Departamento extends Edificio {

    protected int codigo;

    public  Departamento(int id, String nombre, String direccion, String telefono, int idEncargado, int codigo){
        super(id,nombre,direccion,telefono,idEncargado);
        this.codigo=codigo;
    }

}
