package pipenatr.Activities;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import Clases.DataBases.DBController;
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

    public boolean recyclerViewListClicked(int position) {
        return controller.cancelarSolicitud(adapter.getSelectedItemId(position));
    }
}
