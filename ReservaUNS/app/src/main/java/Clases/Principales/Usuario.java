package Clases.Principales;

import android.content.Context;
import android.support.design.widget.NavigationView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaUsuarios;
import Clases.Otras.AsignacionesViewHolder;
import Clases.Otras.PrestamosViewHolder;
import Clases.Otras.SolicitudesViewHolder;
import pipenatr.Activities.RecyclerViewClickListener;

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
            this.id= TablaUsuarios.getNextID(DBController.getDB());
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

    public abstract void actualizarNavView(NavigationView navigationView);

    public abstract LinkedList<Solicitud> getEspacios(Context context);

    public abstract LinkedList<Prestamo> getPrestamos(Context context);

    public abstract LinkedList<Prestamo> getAsignaciones(Context context);

    public abstract void setListenerSolicitudes(SolicitudesViewHolder holder, Solicitud solicitud, RecyclerViewClickListener listener, Context context);

    public abstract void setListenerPrestamos(PrestamosViewHolder holder,Prestamo p);


}
