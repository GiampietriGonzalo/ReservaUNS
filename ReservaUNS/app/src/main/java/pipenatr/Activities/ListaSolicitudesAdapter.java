package pipenatr.Activities;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Clases.DataBases.DBController;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;
import Clases.Principales.Horario;
import Clases.Principales.Solicitud;

public class ListaSolicitudesAdapter extends RecyclerView.Adapter<ListaSolicitudesAdapter.SolicitudesViewHolder> {

    DBController controller;
    ArrayList<Solicitud> listaSolicitud;
    RecyclerViewClickListener listener;
    Context context;

    public ListaSolicitudesAdapter(ArrayList<Solicitud> listaSolicitud, Context context, RecyclerViewClickListener listener) {
        this.listaSolicitud = listaSolicitud;
        controller = DBController.getDBController(context);
        this.listener = listener;
        this.context = context;
    }

    
    public SolicitudesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_solicitudes,null,false);
        return new SolicitudesViewHolder(view);
    }

    
    public void onBindViewHolder(final SolicitudesViewHolder holder, final int position) {

        final Solicitud miSolicitud = listaSolicitud.get(position);

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

        if(miSolicitud.getEstadoString()!= "Activo")
            holder.btnCancelarSolicitud.setEnabled(false);

        holder.btnCancelarSolicitud.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar la cancelacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);

                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(listener.cancelarSolicitud(holder.getAdapterPosition())) {

                            holder.btnCancelarSolicitud.setEnabled(false);
                            miSolicitud.cancelar();
                            controller.cancelarSolicitud(miSolicitud.getId());
                            controller.actualizarEstadoSolicitud(miSolicitud);
                            controller.insertSolicitudReserva(miSolicitud);
                            holder.estado.setText(miSolicitud.getEstadoString());
                            Toast.makeText(context, "Su solicitud fue cancelada", Toast.LENGTH_LONG).show();


                        }
                        else
                            Toast.makeText(context, "No se pudo eliminar la solicitud", Toast.LENGTH_LONG).show();
                    }
                });

                alerta.setNegativeButton("Cancelar", null);
                alerta.setMessage("¿desea cancelar la solicitud?");
                alerta.setTitle("Cancelar solicitud");
                alerta.setCancelable(true);
                alerta.create().show();
            }        });
    }

    public int getItemCount() {
        return listaSolicitud.size();
    }

    public int getSelectedItemId(int position) {
        return listaSolicitud.get(position).getId();
    }

    public class SolicitudesViewHolder extends RecyclerView.ViewHolder {

        TextView id, fecha, horarioIncio, horarioFin, estado, edificio,espacio;
        Button btnCancelarSolicitud;
        int position;

        public SolicitudesViewHolder(View itemView) {

            super(itemView);
            id = (TextView) itemView.findViewById(R.id.txtIdItemList);
            fecha = (TextView) itemView.findViewById(R.id.txtFechaItemList);
            horarioIncio = (TextView) itemView.findViewById(R.id.txtHorarioInicioItemList);
            horarioFin = (TextView) itemView.findViewById(R.id.txtHorarioFinItemList);
            estado = (TextView) itemView.findViewById(R.id.txtEstadoItemList);
            espacio=(TextView) itemView.findViewById(R.id.txtEspacioItemList);
            edificio=(TextView) itemView.findViewById(R.id.txtEdificioItemList);
            btnCancelarSolicitud = (Button) itemView.findViewById(R.id.btnCancelarSolicitud);

        }

        public void onClick(View view) {
            position = this.getLayoutPosition();
        }
    }
}