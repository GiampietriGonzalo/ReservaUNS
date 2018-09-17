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
import Clases.Principales.Prestamo;
import Clases.Principales.Solicitud;
import Clases.Principales.Usuario;

public class ConsultarPrestamos extends Fragment{

    View myView;
    ArrayList<Prestamo> listaPrestamos;
    RecyclerView recyclerViewPrestamos;
    ListaPrestamosAdapter adapter;
    DBController controller;
    Usuario usuario;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.consultar_prestamo, container, false);
        controller = DBController.getDBController(getActivity());
        listaPrestamos = new ArrayList<Prestamo>();

        recyclerViewPrestamos = myView.findViewById(R.id.recyclerPrestamos);
        recyclerViewPrestamos.setLayoutManager(new LinearLayoutManager(getActivity()));

        usuario = controller.findUsuario(Integer.parseInt(SaveSharedPreference.getUserId(getActivity())));


        consultarListaPrestamos();
        adapter = new ListaPrestamosAdapter(listaPrestamos, getActivity(),usuario);
        recyclerViewPrestamos.setAdapter(adapter);

        return myView;
    }


    private void consultarListaPrestamos() {


        LinkedList<Prestamo> prestamos = usuario.filtrarPrestamos(this.getActivity());
        if(prestamos.isEmpty())
            Log.e("ListaDePrestamos","ESTA VACIA");
        for( int i = 0; i<prestamos.size(); i++){
            listaPrestamos.add(prestamos.get(i));
        }
    }


}
