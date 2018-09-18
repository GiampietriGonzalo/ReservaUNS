package Clases.Otras;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pipenatr.Activities.R;

public class PrestamosViewHolder extends RecyclerView.ViewHolder {

    public TextView id, fecha, horarioIncio, horarioFin, estado, edificio,espacio;
    public int position;
    public Button btnBaja;

    public PrestamosViewHolder(View itemView) {

        super(itemView);
        id = (TextView) itemView.findViewById(R.id.txtIdItemListPrestamo);
        fecha = (TextView) itemView.findViewById(R.id.txtFechaItemListPrestamo);
        horarioIncio = (TextView) itemView.findViewById(R.id.txtHorarioInicioItemListPrestamo);
        horarioFin = (TextView) itemView.findViewById(R.id.txtHorarioFinItemListPrestamo);
        estado = (TextView) itemView.findViewById(R.id.txtEstadoItemListPrestamo);
        espacio=(TextView) itemView.findViewById(R.id.txtEspacioItemListPrestamo);
        edificio=(TextView) itemView.findViewById(R.id.txtEdificioItemListPrestamo);
        btnBaja= (Button) itemView.findViewById(R.id.btnBajaPrestamo);

    }

    public void onClick(View view) {
        position = this.getLayoutPosition();
    }


}
