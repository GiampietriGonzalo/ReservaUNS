package Clases.Otras;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import pipenatr.Activities.R;

public class AsignacionesViewHolder extends RecyclerView.ViewHolder{

    public TextView id, fecha, horarioIncio, horarioFin, estado, edificio,espacio;
    public int position;
    public Button btnBaja;

    public AsignacionesViewHolder(View itemView) {

        super(itemView);
        id = (TextView) itemView.findViewById(R.id.txtIdItemListAsignacion);
        fecha = (TextView) itemView.findViewById(R.id.txtFechaItemListAsignacion);
        horarioIncio = (TextView) itemView.findViewById(R.id.txtHorarioInicioItemListAsignacion);
        horarioFin = (TextView) itemView.findViewById(R.id.txtHorarioFinItemListAsignacion);
        estado = (TextView) itemView.findViewById(R.id.txtEstadoItemListAsignacion);
        espacio=(TextView) itemView.findViewById(R.id.txtEspacioItemListAsignacion);
        edificio=(TextView) itemView.findViewById(R.id.txtEdificioItemListAsignacion);

    }

    public void onClick(View view) {
        position = this.getLayoutPosition();
    }


}
