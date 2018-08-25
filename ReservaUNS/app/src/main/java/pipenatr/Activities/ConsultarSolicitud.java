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
    int position;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.consultar_solicitud, container, false);
        controller = DBController.getDBController(getActivity());
        listaSolicitudes = new ArrayList<Solicitud>();

        recyclerViewSolicitudes = myView.findViewById(R.id.recyclerReservas);
        recyclerViewSolicitudes.setLayoutManager(new LinearLayoutManager(getActivity()));


        adapter = new ListaSolicitudesAdapter(listaSolicitudes, getActivity(), this);
        recyclerViewSolicitudes.setAdapter(adapter);

        View view = inflater.inflate(R.layout.list_item_solicitudes, container, false);

        Button botonCancelar = (Button) view.findViewById(R.id.btnCancelarSolicitud);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Muestra mensaje para confirmar la cancelacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(cancelarSolicitud())
                            Toast.makeText(getActivity(), "Su solicitud fue cancelada", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(), "No se pudo eliminar la solicitud", Toast.LENGTH_LONG).show();
                    }
                });
                alerta.setNegativeButton("Cancelar", null);
                alerta.setMessage("¿desea cancelar la solicitud?");
                alerta.setTitle("Cancelar solicitud");
                alerta.setCancelable(true);
                alerta.create().show();
            }
        });
        botonCancelar.setText("ke wea");

        consultarListaReservas();
        return myView;
    }

    private void consultarListaReservas() {

        usuario = controller.findUsuario(Integer.parseInt(SaveSharedPreference.getUserId(getActivity())));
        LinkedList<Solicitud> solicitudes = controller.findSolicitudesUsuario(usuario.getId());

        for( int i = 0; i<solicitudes.size(); i++)
                listaSolicitudes.add(solicitudes.get(i));
    }

    private boolean cancelarSolicitud(){
        int id = adapter.getSelectedItemId(position);
        return controller.cancelarSolicitud(id);
    }

    public void recyclerViewListClicked(View v, int position) {
        this.position = position;
    }
}
