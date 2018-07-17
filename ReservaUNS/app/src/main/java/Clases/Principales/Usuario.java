package Clases.Principales;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class Usuario {

    protected String password;
    protected String cuenta;

    public Usuario(String cuenta, String password){
        this.password=password;
        this.cuenta=cuenta;
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

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
}
