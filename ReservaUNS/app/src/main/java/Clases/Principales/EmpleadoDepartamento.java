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

public class EmpleadoDepartamento extends Usuario{

    public EmpleadoDepartamento(int id, String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }

    public boolean actualizarUsuario() {
        return DBController.insertEmpleadoDepartamento(this);
    }
    
    public void actualizarNavView(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.nav_consultar_solicitud).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_consultar_asignaciones).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_consultar_prestamo).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_administrar_docentes).setVisible(true);
    }

    public LinkedList<Solicitud> getEspacios(Context context) {

        DBController controller = DBController.getDBController(context);

        LinkedList<Solicitud> solicitudes = controller.getSolicitudes();
        LinkedList<Solicitud> solicitudesDepto = new LinkedList<Solicitud>();
        Espacio espacio;
        Edificio edificio;
        Solicitud solicitud ;

        //Para cada elemento de la lista de solicitudes recibida
        while(!solicitudes.isEmpty()) {

            solicitud = solicitudes.removeLast();

            espacio=controller.findEspacio(solicitud.getIdEspacio());
            edificio = controller.findEdificio(espacio.getIdEdificio());

            //Verifica que el encargado del edificio de la reserva sea el usuario
            if(edificio.getEncargado().getId() == Integer.parseInt(SaveSharedPreference.getUserId(context)))
                solicitudesDepto.addLast(solicitud);
        }
        return solicitudesDepto;
    }

    public void setListenerSolicitudes(SolicitudesViewHolder holder, Solicitud solicitud, RecyclerViewClickListener listener, Context context){

        if(solicitud.getEstadoString()!="Activo"){
            holder.btnCR.setEnabled(false);
            holder.btnEA.setEnabled(false);
        }
        else{
            holder.btnEA.setEnabled(true);
            holder.btnCR.setEnabled(true);
        }

        holder.btnEA.setText("Aceptar");
        holder.btnCR.setText("Rechazar");

        ButtonListenerController bcl= ButtonListenerController.getButtonListenerController();
        bcl.setListenerEmpleado(holder,solicitud,listener,context);
    }

    public LinkedList<Prestamo> getPrestamos(Context context) {

        DBController controller = DBController.getDBController(context);

        LinkedList<Prestamo> prestamos = controller.getReservas();
        LinkedList<Prestamo> prestamosDepto = new LinkedList<Prestamo>();
        Espacio espacio;
        Edificio edificio;
        Prestamo prestamo;

        //Para cada elemento de la lista de prestamos recibida
        while(!prestamos.isEmpty()) {

            prestamo = prestamos.removeLast();
            espacio=controller.findEspacio(prestamo.getIdEspacio());
            edificio = controller.findEdificio(espacio.getIdEdificio());

            //Verifica que el encargado del edificio de la reserva sea el usuario
            if(edificio.getEncargado().getId() == Integer.parseInt(SaveSharedPreference.getUserId(context)))
                prestamosDepto.addLast(prestamo);
        }
        
        return prestamosDepto;
    }

    public void setListenerPrestamos(PrestamosViewHolder holder,Prestamo p){
        ButtonListenerController blc= ButtonListenerController.getButtonListenerController();
        blc.setListenerBajaPrestamo(holder,p);
    }


    public LinkedList<Prestamo> getAsignaciones(Context context){

        LinkedList<Prestamo> asignaciones=DBController.getAsignaciones();
        LinkedList<Prestamo> asignacionesDepto= new LinkedList<Prestamo>();
        Prestamo asignacion;
        Edificio edificio;
        Espacio espacio;
        DBController controller=DBController.getDBController(context);

        while(!asignaciones.isEmpty()) {

            asignacion = asignaciones.removeLast();
            espacio=controller.findEspacio(asignacion.getIdEspacio());
            edificio = controller.findEdificio(espacio.getIdEdificio());

            //Verifica que el encargado del edificio de la reserva sea el usuario
            if(edificio.getEncargado().getId() == Integer.parseInt(SaveSharedPreference.getUserId(context)))
                asignacionesDepto.addLast(asignacion);
        }

        return asignacionesDepto;
    }

}



