package pipenatr.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Estados.PrestamoCancelado;
import Clases.Estados.SolicitudCancelada;
import Clases.Principales.Horario;
import Clases.Principales.Reserva;
import Clases.Principales.Solicitud;
import Clases.Principales.Usuario;

public class ConsultarSolicitud extends Fragment implements RecyclerViewClickListener {

    View myView;
    ArrayList<Solicitud> listaSolicitudes;
    RecyclerView recyclerViewSolicitudes;
    ListaSolicitudesAdapter adapter;
    DBController controller;
    Usuario usuario;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.consultar_solicitud, container, false);
        controller = DBController.getDBController(getActivity());
        listaSolicitudes = new ArrayList<Solicitud>();

        recyclerViewSolicitudes = myView.findViewById(R.id.recyclerReservas);
        recyclerViewSolicitudes.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ListaSolicitudesAdapter(listaSolicitudes, getActivity(), this);
        recyclerViewSolicitudes.setAdapter(adapter);

        consultarListaReservas();
        return myView;
    }

    private void consultarListaReservas() {

        usuario = controller.findUsuario(Integer.parseInt(SaveSharedPreference.getUserId(getActivity())));
        LinkedList<Solicitud> solicitudes = controller.findSolicitudesUsuario(usuario.getId());

        for( int i = 0; i<solicitudes.size(); i++)
                listaSolicitudes.add(solicitudes.get(i));
    }

    @Override
    public boolean cancelarSolicitud(int position) {
        Solicitud miSolicitud = listaSolicitudes.get(position);
        Horario miHorario = controller.findHorario(miSolicitud.getHorarios().getFirst());
        Reserva miReserva = null;
        if(miHorario.getIdPrestamo() != 9999) {
            miReserva = (Reserva) controller.findPrestamo(miHorario.getIdPrestamo());
            PrestamoCancelado nuevoEstadoReserva = new PrestamoCancelado(9999, miReserva.getId());

            controller.eliminarEstadoPrestamo(miReserva.getIdEstado());
            miReserva.setIdEstado(nuevoEstadoReserva.getId());
        }

        SolicitudCancelada nuevoEstado = new SolicitudCancelada(9999, miSolicitud.getId());
        controller.eliminarEstadoSolicitud(miSolicitud.getIdEstado());
        miSolicitud.setIdEstado(nuevoEstado.getId());

        return true;
    }

    @Override
    public boolean eliminarSolicitud(int position) {
        Solicitud miSolicitud = listaSolicitudes.get(position);
        Horario miHorario = controller.findHorario(miSolicitud.getHorarios().getFirst());
        Reserva miReserva = null;

        if(miHorario.getIdPrestamo() != 9999) {
            miReserva = (Reserva) controller.findPrestamo(miHorario.getIdPrestamo());

            controller.eliminarEstadoPrestamo(miReserva.getIdEstado());
            controller.cancelarPrestamo(miReserva.getId());
        }

        return controller.cancelarSolicitud(adapter.getSelectedItemId(position)) && controller.eliminarHorario(miHorario.getId()) && controller.eliminarEstadoSolicitud(miSolicitud.getIdEstado());
    }

    @Override
    public boolean aceptarSolicitud(int position) {
        return false;
    }

    @Override
    public boolean rechazarSolicitud(int position) {
        return false;
    }
}
