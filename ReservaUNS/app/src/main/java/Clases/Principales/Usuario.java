package Clases.Principales;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaUsuarios;

/**
 * Created by gonza on 16/07/18.
 */

public abstract class Usuario {

    protected int id;
    protected String password;
    protected int legajo;
    protected String nombre;
    protected String apellido;
    protected String mail;
    protected String telefono;

    public Usuario(int id, String password, int legajo,String nombre, String apellido,String mail,String telefono){

        if(id==9999)
            id= TablaUsuarios.getNextID(DBController.getDB());
        else
            this.id=id;
        this.mail=mail;
        this.telefono=telefono;
        this.nombre=nombre;
        this.apellido=apellido;
        this.password=password;
        this.legajo=legajo;
    }

    public abstract boolean actualizarUsuario();

    public int getLegajo() {
        return legajo;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String oldPassword,String newPassword) {
        //CONTROLAR QUE LA CONTRASEÑA VIEJA ES IGUAL A LA ACTUAL
        if(password==oldPassword)
            this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }
}
