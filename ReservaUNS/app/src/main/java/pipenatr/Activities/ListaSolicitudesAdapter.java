package pipenatr.Activities;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Clases.Principales.Solicitud;

/**
 * Created by CHENAO on 8/07/2017.
 */

public class ListaSolicitudesAdapter extends RecyclerView.Adapter<ListaSolicitudesAdapter.SolicitudsViewHolder> {

    ArrayList<Solicitud> listaSolicitud;

    public ListaSolicitudesAdapter(ArrayList<Solicitud> listaSolicitud) {
        this.listaSolicitud = listaSolicitud;
    }

    
    public SolicitudsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_solicitudes,null,false);
        return new SolicitudsViewHolder(view);
    }

    
    public void onBindViewHolder(SolicitudsViewHolder holder, int position) {
        holder.id.setText(listaSolicitud.get(position).getId());
        holder.fecha.setText(listaSolicitud.get(position).getFecha());
        //holder.horario.setText(listaSolicitud.get(position).);
        //holder.estado.setText(listaSolicitud.get(position).get);
    }

    
    public int getItemCount() {
        return listaSolicitud.size();
    }

    public class SolicitudsViewHolder extends RecyclerView.ViewHolder {

        TextView id, fecha, horario, estado;

        public SolicitudsViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.txtIdItemList);
            fecha = (TextView) itemView.findViewById(R.id.txtFechaItemList);
            horario = (TextView) itemView.findViewById(R.id.txtHorarioItemList);
            estado = (TextView) itemView.findViewById(R.id.txtEstadoItemList);
        }
    }
}