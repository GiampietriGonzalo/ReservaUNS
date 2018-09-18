package Clases.Otras;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pipenatr.Activities.R;

public class SolicitudesViewHolder extends RecyclerView.ViewHolder {

    public TextView id, fecha, horarioIncio, horarioFin, estado, edificio,espacio;
    public Button btnCR, btnEA;
    public int position;

    public SolicitudesViewHolder(View itemView) {

        super(itemView);
        id = (TextView) itemView.findViewById(R.id.txtIdItemList);
        fecha = (TextView) itemView.findViewById(R.id.txtFechaItemList);
        horarioIncio = (TextView) itemView.findViewById(R.id.txtHorarioInicioItemList);
        horarioFin = (TextView) itemView.findViewById(R.id.txtHorarioFinItemList);
        estado = (TextView) itemView.findViewById(R.id.txtEstadoItemList);
        espacio=(TextView) itemView.findViewById(R.id.txtEspacioItemList);
        edificio=(TextView) itemView.findViewById(R.id.txtEdificioItemList);
        btnCR= (Button) itemView.findViewById(R.id.btnCR);
        btnEA = (Button) itemView.findViewById(R.id.btnEA);

    }

    public void onClick(View view) {
        position = this.getLayoutPosition();
    }


}