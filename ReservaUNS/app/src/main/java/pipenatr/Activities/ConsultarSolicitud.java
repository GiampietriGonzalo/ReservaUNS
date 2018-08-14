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

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Reserva;
import Clases.Principales.Solicitud;
import Clases.Principales.Usuario;

public class ConsultarSolicitud extends Fragment {
    View myView;
    LinkedList<Solicitud> listaSolicitudes;
    RecyclerView recyclerViewSolicitudes;
    DBController controller;
    Usuario usuario;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.consultar_solicitud, container, false);
        controller = DBController.getDBController(getActivity());

        controller = DBController.getDBController(getActivity());

        listaSolicitudes = new LinkedList<Solicitud>();

        recyclerViewSolicitudes = (RecyclerView) myView.findViewById(R.id.recyclerReservas);
        recyclerViewSolicitudes.setLayoutManager(new LinearLayoutManager(getActivity()));

        consultarListaReservas();

        return myView;
    }

    private void consultarListaReservas() {

        LinkedList<Solicitud> solicitudes=DBController.findSolicitudesUsuario(usuario.getId());
        //Necesito el id del usuario que esta logueado
        //TODO: AÚN FALTA LIGAR usuario AL USUARIO QUE LOGEO



    }
}
