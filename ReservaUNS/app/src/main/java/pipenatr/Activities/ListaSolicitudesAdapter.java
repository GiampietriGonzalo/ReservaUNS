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
        holder.id.setText(""+listaSolicitud.get(position).getId());
        holder.fecha.setText(listaSolicitud.get(position).getFecha());

        Horario horario = controller.findHorario(listaSolicitud.get(position).getIdHorario());
        holder.horario.setText(""+horario.getHoraInicio());

        EstadoSolicitud estado = controller.findEstadoSolicitud(listaSolicitud.get(position).getIdEstado());
//        holder.estado.setText(estado.getEstado());
    }

    
    public int getItemCount() {
        return listaSolicitud.size();
    }

    public class SolicitudesViewHolder extends RecyclerView.ViewHolder {

        TextView id, fecha, horario, estado;

        public SolicitudesViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.txtIdItemList);
            fecha = (TextView) itemView.findViewById(R.id.txtFechaItemList);
            horario = (TextView) itemView.findViewById(R.id.txtHorarioItemList);
            estado = (TextView) itemView.findViewById(R.id.txtEstadoItemList);
        }
    }
}