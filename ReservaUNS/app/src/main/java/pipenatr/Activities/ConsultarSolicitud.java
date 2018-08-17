package pipenatr.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Reserva;
import Clases.Principales.Solicitud;
import Clases.Principales.SolicitudReserva;
import Clases.Principales.Usuario;

public class ConsultarSolicitud extends Fragment {
    View myView;
    ArrayList<Solicitud> listaSolicitudes;
    RecyclerView recyclerViewSolicitudes;
    DBController controller;
    Usuario usuario;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.consultar_solicitud, container, false);
        controller = DBController.getDBController(getActivity());

        controller = DBController.getDBController(getActivity());

        listaSolicitudes = new ArrayList<Solicitud>();

        recyclerViewSolicitudes = (RecyclerView) myView.findViewById(R.id.recyclerReservas);
        recyclerViewSolicitudes.setLayoutManager(new LinearLayoutManager(getActivity()));

        consultarListaReservas();

        ListaSolicitudesAdapter adapter = new ListaSolicitudesAdapter(listaSolicitudes);
        recyclerViewSolicitudes.setAdapter(adapter);

        return myView;
    }

    private void consultarListaReservas()
    {
        usuario = controller.findUsuario(Integer.parseInt(SaveSharedPreference.getUserId(getActivity())));
        LinkedList<Solicitud> solicitudes = controller.findSolicitudesUsuario(usuario.getId());
        SolicitudReserva solicitud;
        for( int i = 0; i<solicitudes.size(); i++)
        {
            solicitud = (SolicitudReserva) solicitudes.get(i);
            listaSolicitudes.add(solicitud);
        }
    }
}
