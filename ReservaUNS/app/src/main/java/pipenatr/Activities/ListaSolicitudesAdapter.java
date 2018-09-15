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

        usuario.setListener(holder,miSolicitud,listener,context);

        /*
        if(miSolicitud.getEstadoString()!= "Activo")
            holder.btnEA.setEnabled(true);
        else{
            holder.btnCR.setEnabled(true);
            holder.btnEA.setEnabled(false);
        }

        //Crea boton eliminar
        holder.btnEA.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                listener.eliminarSolicitud(holder.getAdapterPosition());
                holder.btnEA.setEnabled(false);
                Toast.makeText(context, "La solicitud fue eliminada", Toast.LENGTH_LONG).show();
            }
        });

        //Crea boton cancelar
        holder.btnCR.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar la cancelacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(listener.cancelarSolicitud(holder.getAdapterPosition())) {

                            holder.estado.setText(miSolicitud.getEstadoString());
                            holder.btnCR.setEnabled(false);
                            holder.btnEA.setVisibility(View.VISIBLE);
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
        holder.btnEA.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar la aceptacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);

                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(listener.aceptarSolicitud(holder.getAdapterPosition())) {

                            Toast.makeText(context, "La solicitud fue aceptada", Toast.LENGTH_LONG).show();
                            holder.btnCR.setEnabled(false);
                            holder.btnEA.setEnabled(false);
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


            */

    }

    public int getItemCount() {
        return listaSolicitud.size();
    }

    public int getSelectedItemId(int position) {
        return listaSolicitud.get(position).getId();
    }


}