package Clases.Principales;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.view.View;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Otras.AsignacionesViewHolder;
import Clases.Otras.ButtonListenerController;
import Clases.Otras.PrestamosViewHolder;
import Clases.Otras.SolicitudesViewHolder;
import pipenatr.Activities.R;
import pipenatr.Activities.RecyclerViewClickListener;
import pipenatr.Activities.SaveSharedPreference;

public class Docente extends Usuario {

    public Docente(int id,String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }


    public boolean actualizarUsuario() {
        return DBController.insertDocente(this);
    }

    public void actualizarNavView(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.nav_solicitar_reserva).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_consultar_solicitud).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_consultar_prestamo).setVisible(true);
    }

    public LinkedList<Solicitud> getEspacios(Context context) {
        DBController controller = DBController.getDBController(context);
        LinkedList<Solicitud> solicitudes = controller.findSolicitudesUsuario(id);
        return solicitudes;
    }

    public void setListenerSolicitudes(SolicitudesViewHolder holder, Solicitud solicitud, RecyclerViewClickListener listener, Context context){

        if(solicitud.getEstadoString()!="Activo"){
            holder.btnCR.setEnabled(false);
            holder.btnEA.setEnabled(true);
        }
        else{
            holder.btnCR.setEnabled(true);
            holder.btnEA.setEnabled(false);
        }

        holder.btnEA.setText("Eliminar");
        holder.btnCR.setText("Cancelar");

        ButtonListenerController bcl= ButtonListenerController.getButtonListenerController();
        bcl.setListenerDocente(holder,solicitud,listener,context);
    }


    public LinkedList<Prestamo> getPrestamos(Context context) {

        DBController controller = DBController.getDBController(context);
        LinkedList<Prestamo> prestamos = controller.getReservas();
        LinkedList<Prestamo> prestamosDocente = new LinkedList<Prestamo>();
        Espacio espacio;
        Prestamo prestamo;

        //Para cada elemento de la lista de prestamos recibida
        while(!prestamos.isEmpty()) {

            prestamo = prestamos.removeLast();
            espacio=controller.findEspacio(prestamo.getIdEspacio());

            //Verifica que el encargado del edificio de la reserva sea el usuario
            if(((Reserva)prestamo).getIdDocente() == Integer.parseInt(SaveSharedPreference.getUserId(context)))
                prestamosDocente.addLast(prestamo);
        }

        return prestamosDocente;
    }

    public void setListenerPrestamos(PrestamosViewHolder holder,Prestamo p){

        ButtonListenerController blc = ButtonListenerController.getButtonListenerController();

        holder.btnBaja.setEnabled(false);
        holder.btnBaja.setVisibility(View.GONE);
        holder.btnEliminar.setEnabled(false);
        blc.setListenerEliminarPrestamo(holder,p);

        if(p.getEstadoString()=="Dado de baja")
            holder.btnEliminar.setEnabled(true);


    }

    public LinkedList<Prestamo> getAsignaciones(Context context) {
        return null;
    }
}
