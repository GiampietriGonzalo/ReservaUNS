package pipenatr.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Solicitud;
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

        listaSolicitudes = new ArrayList<>();

        recyclerViewSolicitudes = myView.findViewById(R.id.recyclerReservas);
        recyclerViewSolicitudes.setLayoutManager(new LinearLayoutManager(getActivity()));

        consultarListaReservas();

        ListaSolicitudesAdapter adapter = new ListaSolicitudesAdapter(listaSolicitudes, getActivity());
        recyclerViewSolicitudes.setAdapter(adapter);

        return myView;
    }

    private void consultarListaReservas()
    {
        usuario = controller.findUsuario(Integer.parseInt(SaveSharedPreference.getUserId(getActivity())));
        LinkedList<Solicitud> solicitudes = controller.findSolicitudesUsuario(usuario.getId());
        for( int i = 0; i<solicitudes.size(); i++) {
            Log.e("IDSol", ""+solicitudes.get(i).getId());
            listaSolicitudes.add(solicitudes.get(i));
        }
    }
}
