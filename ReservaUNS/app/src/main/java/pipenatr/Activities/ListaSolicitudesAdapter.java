package pipenatr.Activities;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Clases.DataBases.DBController;
import Clases.Estados.EstadoSolicitud;
import Clases.Principales.Horario;
import Clases.Principales.Solicitud;

/**
 * Created by CHENAO on 8/07/2017.
 */

public class ListaSolicitudesAdapter extends RecyclerView.Adapter<ListaSolicitudesAdapter.SolicitudesViewHolder> {

    DBController controller;
    ArrayList<Solicitud> listaSolicitud;

    public ListaSolicitudesAdapter(ArrayList<Solicitud> listaSolicitud, Context context) {
        this.listaSolicitud = listaSolicitud;
        controller = DBController.getDBController(context);
    }

    
    public SolicitudesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_solicitudes,null,false);
        return new SolicitudesViewHolder(view);
    }

    
    public void onBindViewHolder(SolicitudesViewHolder holder, int position) {

        Solicitud miSolicitud=listaSolicitud.get(position);

        holder.id.setText(""+miSolicitud.getId());
        holder.fecha.setText(miSolicitud.getFecha());
        Horario horario = controller.findHorario(miSolicitud.getHorarios().getFirst());
        holder.horarioIncio.setText(horario.horaInicioConFormato());
        holder.horarioFin.setText(horario.horaFinConFormato());
        EstadoSolicitud estado = controller.findEstadoSolicitud(miSolicitud.getIdEstado());
        if(estado!=null)
            holder.estado.setText(estado.getEstado());
    }

    
    public int getItemCount() {
        return listaSolicitud.size();
    }

    public class SolicitudesViewHolder extends RecyclerView.ViewHolder {

        TextView id, fecha, horarioIncio, horarioFin,estado;

        public SolicitudesViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.txtIdItemList);
            fecha = (TextView) itemView.findViewById(R.id.txtFechaItemList);
            horarioIncio = (TextView) itemView.findViewById(R.id.txtHorarioInicioItemList);
            horarioFin = (TextView) itemView.findViewById(R.id.txtHorarioFinItemList);
            estado = (TextView) itemView.findViewById(R.id.txtEstadoItemList);
        }
    }
}