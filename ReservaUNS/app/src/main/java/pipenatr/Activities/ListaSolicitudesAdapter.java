package pipenatr.Activities;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Clases.DataBases.DBController;
import Clases.Otras.SolicitudesViewHolder;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;
import Clases.Principales.Horario;
import Clases.Principales.Solicitud;
import Clases.Principales.Usuario;

public class ListaSolicitudesAdapter extends RecyclerView.Adapter<SolicitudesViewHolder> {

    private DBController controller;
    private ArrayList<Solicitud> listaSolicitud;
    private RecyclerViewClickListener listener;
    private Context context;
    private Usuario usuario;

    public ListaSolicitudesAdapter(ArrayList<Solicitud> listaSolicitud, Context context, RecyclerViewClickListener listener, Usuario usuario) {
        this.listaSolicitud = listaSolicitud;
        controller = DBController.getDBController(context);
        this.listener = listener;
        this.context = context;
        this.usuario=usuario;
    }

    
    public SolicitudesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_solicitudes,null,false);
        return new SolicitudesViewHolder(view);
    }

    
    public void onBindViewHolder(SolicitudesViewHolder holder, int position) {

        Solicitud miSolicitud = listaSolicitud.get(position);

        Espacio miEspacio= controller.findEspacio(miSolicitud.getIdEspacio());
        Edificio miEdificio= miEspacio.getEdificio();

        holder.id.setText(""+miSolicitud.getId());
        holder.fecha.setText(miSolicitud.getFecha());
        holder.espacio.setText(miEspacio.getNombre());
        holder.edificio.setText(miEdificio.getNombre());
        Horario horario = controller.findHorario(miSolicitud.getHorarios().getFirst());
        holder.horarioIncio.setText(horario.horaInicioConFormato());
        holder.horarioFin.setText(horario.horaFinConFormato());
        holder.estado.setText(miSolicitud.getEstadoString());

        usuario.setListenerSolicitudes(holder,miSolicitud,listener,context);

    }

    public int getItemCount() {
        return listaSolicitud.size();
    }

    public int getSelectedItemId(int position) {
        return listaSolicitud.get(position).getId();
    }


}