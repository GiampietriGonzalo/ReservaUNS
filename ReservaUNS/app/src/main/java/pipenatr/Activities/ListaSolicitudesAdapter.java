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
            holder.btnEliminarSolicitud.setVisibility(View.VISIBLE);
        else{
            holder.btnCancelarSolicitud.setVisibility(View.VISIBLE);
            holder.btnAceptarSolicitud.setVisibility(View.GONE);
            holder.btnEliminarSolicitud.setVisibility(View.GONE);
            holder.btnAceptarSolicitud.setVisibility(View.INVISIBLE);
            holder.btnEliminarSolicitud.setVisibility(View.INVISIBLE);
        }

        //Crea boton eliminar
        holder.btnEliminarSolicitud.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                listener.eliminarSolicitud(holder.getAdapterPosition());
                holder.btnEliminarSolicitud.setEnabled(false);
                Toast.makeText(context, "La solicitud fue eliminada", Toast.LENGTH_LONG).show();
            }
        });

        //Crea boton cancelar
        holder.btnCancelarSolicitud.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar la cancelacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(listener.cancelarSolicitud(holder.getAdapterPosition())) {

                            holder.estado.setText(miSolicitud.getEstadoString());
                            holder.btnCancelarSolicitud.setEnabled(false);
                            holder.btnCancelarSolicitud.setVisibility(View.GONE);
                            holder.btnEliminarSolicitud.setVisibility(View.VISIBLE);
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

        //Crea boton aceptar
        holder.btnAceptarSolicitud.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar la aceptacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);

                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(listener.aceptarSolicitud(holder.getAdapterPosition())) {

                            Toast.makeText(context, "La solicitud fue aceptada", Toast.LENGTH_LONG).show();
                            holder.btnRechazarSolicitud.setEnabled(false);
                            holder.btnAceptarSolicitud.setEnabled(false);
                            holder.estado.setText(miSolicitud.getEstadoString());

                        }
                        else
                            Toast.makeText(context, "No se pudo aceptar la solicitud", Toast.LENGTH_LONG).show();
                    }
                });

                alerta.setNegativeButton("Cancelar", null);
                alerta.setMessage("¿desea aceptar la solicitud?");
                alerta.setTitle("Aceptar solicitud");
                alerta.setCancelable(true);
                alerta.create().show();
            }        });

        //Crea boton rechazar
        holder.btnRechazarSolicitud.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar la cancelacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);

                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(listener.rechazarSolicitud(holder.getAdapterPosition())) {

                            Toast.makeText(context, "La solicitud fue rechazada", Toast.LENGTH_LONG).show();
                            holder.btnRechazarSolicitud.setEnabled(false);
                            holder.btnAceptarSolicitud.setEnabled(false);
                            holder.estado.setText(miSolicitud.getEstadoString());

                        }
                        else
                            Toast.makeText(context, "No se pudo rechazar la solicitud", Toast.LENGTH_LONG).show();
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
        Button btnCancelarSolicitud, btnAceptarSolicitud, btnRechazarSolicitud, btnEliminarSolicitud;
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
            btnAceptarSolicitud = (Button) itemView.findViewById(R.id.btnAceptarSolicitud);
            btnRechazarSolicitud = (Button) itemView.findViewById(R.id.btnRechazarSolicitud);
            btnEliminarSolicitud = (Button) itemView.findViewById(R.id.btnEliminarSolicitud);
        }

        public void onClick(View view) {
            position = this.getLayoutPosition();
        }
    }
}