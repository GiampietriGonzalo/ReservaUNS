package Clases.Otras;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import Clases.DataBases.DBController;
import Clases.Principales.Prestamo;
import Clases.Principales.Solicitud;
import pipenatr.Activities.RecyclerViewClickListener;

public class ButtonListenerController {


    private static ButtonListenerController blc;

    private ButtonListenerController(){}

    public void setListenerBajaPrestamo(PrestamosViewHolder holder,Prestamo p){


        holder.btnBaja.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //DBController.cancelarPrestamo(p.getId());
                holder.btnBaja.setEnabled(false);
                holder.btnEliminar.setEnabled(true);
                p.darDeBaja();
                DBController.actualizarPrestamo(p);
                holder.estado.setText(p.getEstadoString());

            }
        });

    }

    public void setListenerEliminarPrestamo(PrestamosViewHolder holder, Prestamo p){

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                DBController.cancelarPrestamo(p.getId());
                holder.btnBaja.setEnabled(false);
                holder.btnEliminar.setEnabled(false);
                holder.estado.setText("De baja - Eliminado");

            }
        });


    }

    public void setListenerDocente(SolicitudesViewHolder holder, Solicitud solicitud, RecyclerViewClickListener listener, Context context){


        //Comportamiento de boton de ELIMINAR
        holder.btnEA.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar la cancelacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (listener.eliminarSolicitud(holder.getAdapterPosition())) {

                            holder.btnEA.setEnabled(false);
                            Toast.makeText(context, "La solicitud fue eliminada", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(context, "No se pudo eliminar la solicitud", Toast.LENGTH_LONG).show();


                    }
                });

                alerta.setNegativeButton("Cancelar", null);
                alerta.setMessage("¿Eliminar solicitud?");
                alerta.setTitle("Eliminar solicitud");
                alerta.setCancelable(true);
                alerta.create().show();
            }
        });

        //CANCELAR
        holder.btnCR.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar la cancelacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(listener.cancelarSolicitud(holder.getAdapterPosition())) {

                            holder.estado.setText(solicitud.getEstadoString());
                            holder.btnCR.setEnabled(false);
                            holder.btnEA.setEnabled(true);
                            Toast.makeText(context, "Su solicitud fue cancelada", Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(context, "No se pudo cancelar la solicitud", Toast.LENGTH_LONG).show();
                    }
                });

                alerta.setNegativeButton("Cancelar", null);
                alerta.setMessage("¿Cancelar solicitud?");
                alerta.setTitle("Cancelar solicitud");
                alerta.setCancelable(true);
                alerta.create().show();
            }
        });

    }

    public void setListenerEmpleado(SolicitudesViewHolder holder, Solicitud solicitud, RecyclerViewClickListener listener, Context context){

        //Comportamiento de boton de ACEPTAR
        holder.btnEA.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar la aceptacion de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);

                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i){

                        if(listener.aceptarSolicitud(holder.getAdapterPosition())){

                            holder.btnEA.setEnabled(false);
                            holder.btnCR.setEnabled(false);
                            Toast.makeText(context, "La solicitud fue Aceptada", Toast.LENGTH_LONG).show();

                        }
                    }
                });

                alerta.setNegativeButton("Cancelar", null);
                alerta.setMessage("¿Aceptar solicitud?");
                alerta.setTitle("Aceptar solicitud");
                alerta.setCancelable(true);
                alerta.create().show();

            }
        });

        //Comportamiento de boton de RECHAZAR
        holder.btnCR.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //Muestra mensaje para confirmar el rechazo de la solicitud
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);

                alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(listener.rechazarSolicitud(holder.getAdapterPosition())) {

                            holder.estado.setText(solicitud.getEstadoString());
                            holder.btnCR.setEnabled(false);
                            holder.btnEA.setEnabled(false);
                            Toast.makeText(context, "Su solicitud fue rechazada", Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(context, "No se pudo rechazar la solicitud", Toast.LENGTH_LONG).show();
                    }
                });

                alerta.setNegativeButton("Cancelar", null);
                alerta.setMessage("¿Desea rechazar la solicitud?");
                alerta.setTitle("Rechazar solicitud");
                alerta.setCancelable(true);
                alerta.create().show();
            }
        });

    }

    public void setListenerEmpleadoSecretaria(SolicitudesViewHolder holder, Solicitud solicitud, RecyclerViewClickListener listener, Context context){


    }

    public static ButtonListenerController getButtonListenerController(){

        if(blc==null)
            blc= new ButtonListenerController();

        return blc;
    }

}
