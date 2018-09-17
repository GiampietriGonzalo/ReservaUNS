package Clases.Otras;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pipenatr.Activities.R;

public class AsignacionViewHolder extends RecyclerView.ViewHolder {

    public TextView fecha, horaInicio, horaFin;


    public AsignacionViewHolder(@NonNull View itemView) {
        super(itemView);

        fecha = (TextView) itemView.findViewById(R.id.txtFechaReserva);
        horaInicio = (TextView) itemView.findViewById(R.id.txtHoraInicio);
        horaFin = (TextView) itemView.findViewById(R.id.txtHoraFin);
    }
}
