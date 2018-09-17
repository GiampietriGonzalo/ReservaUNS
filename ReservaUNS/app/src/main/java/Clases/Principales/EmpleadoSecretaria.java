package Clases.Principales;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.Log;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Otras.ButtonListenerController;
import Clases.Otras.SolicitudesViewHolder;
import pipenatr.Activities.R;
import pipenatr.Activities.RecyclerViewClickListener;
import pipenatr.Activities.SaveSharedPreference;

public class EmpleadoSecretaria extends Usuario{

    public EmpleadoSecretaria(int id,String password, String nombre, String apellido, int legajo, String mail, String telefono) {
        super(id,password,legajo,nombre,apellido,mail,telefono);
    }

    public boolean actualizarUsuario(){
        return DBController.insertEmpleadoSecretaria(this);
    }

    public void actualizarNavView(NavigationView navigationView) {
        navigationView.getMenu().findItem(R.id.nav_consultar_asignaciones).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_admininistrar_solicitud_Secretaria).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_registrar_asignacion).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_consultar_prestamo).setVisible(true);
    }

    public LinkedList<Solicitud> filtrarEspacios(Context context) {

        DBController controller = DBController.getDBController(context);
        LinkedList<Solicitud> solicitudes = controller.getSolicitudes();
        LinkedList<Solicitud> solicitudesAulas = new LinkedList<Solicitud>();
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
                solicitudesAulas.addLast(solicitud);
        }

        return solicitudesAulas;
    }

    public void setListener(SolicitudesViewHolder holder, Solicitud solicitud, RecyclerViewClickListener listener, Context context){

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

    public LinkedList<Prestamo> filtrarPrestamos(Context context) {

        DBController controller = DBController.getDBController(context);
        LinkedList<Prestamo> prestamos = controller.getPrestamos();
        LinkedList<Prestamo> prestamosAulas = new LinkedList<Prestamo>();
        Espacio espacio;
        Edificio edificio;
        Prestamo solicitud ;

        //Para cada elemento de la lista de prestamos recibida
        while(!prestamos.isEmpty()) {

            solicitud = prestamos.removeLast();

            espacio=controller.findEspacio(solicitud.getIdEspacio());
            edificio = controller.findEdificio(espacio.getIdEdificio());


            //Verifica que el encargado del edificio de la reserva sea el usuario
            if(edificio.getEncargado().getId() == Integer.parseInt(SaveSharedPreference.getUserId(context)))
                prestamosAulas.addLast(solicitud);
        }

        return prestamosAulas;
    }


}
