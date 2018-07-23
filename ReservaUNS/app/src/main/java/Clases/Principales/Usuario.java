package Clases.Principales;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class Usuario {

    protected int id;
    protected String password;
    protected String cuenta;

    public Usuario(int id, String cuenta, String password){
        this.id=id;
        this.password=password;
        this.cuenta=cuenta;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setPassword(String oldPassword,String newPassword) {
        //CONTROLAR QUE LA CONTRASEÑA VIEJA ES IGUAL A LA ACTUAL
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
}
