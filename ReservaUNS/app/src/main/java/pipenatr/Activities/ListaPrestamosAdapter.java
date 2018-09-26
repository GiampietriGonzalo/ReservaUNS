package pipenatr.Activities;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import Clases.DataBases.DBController;
import Clases.Otras.PrestamosViewHolder;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;
import Clases.Principales.Horario;
import Clases.Principales.Prestamo;
import Clases.Principales.Usuario;

public class ListaPrestamosAdapter extends RecyclerView.Adapter<PrestamosViewHolder> {

    private DBController controller;
    private ArrayList<Prestamo> listaPrestamos;
    private Context context;
    private Usuario usuario;

    public ListaPrestamosAdapter(ArrayList<Prestamo> listaPrestamos, Context context,Usuario usuario) {
        this.listaPrestamos = listaPrestamos;
        controller = DBController.getDBController(context);
        this.context = context;
        this.usuario=usuario;
    }


    public PrestamosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_prestamo,null,false);
        return new PrestamosViewHolder(view);
    }

    public void onBindViewHolder(PrestamosViewHolder holder,int position) {

        Prestamo miPrestamo = listaPrestamos.get(position);

        Espacio miEspacio= controller.findEspacio(miPrestamo.getIdEspacio());
        Edificio miEdificio= miEspacio.getEdificio();

        holder.id.setText(""+miPrestamo.getId());
        holder.fecha.setText(miPrestamo.getFecha());
        holder.espacio.setText(miEspacio.getNombre());
        holder.edificio.setText(miEdificio.getNombre());
        Horario horario = controller.findHorario(miPrestamo.getIdHorario());
        holder.horarioIncio.setText(horario.horaInicioConFormato());
        holder.horarioFin.setText(horario.horaFinConFormato());
        holder.estado.setText(miPrestamo.getEstadoString());

        usuario.setListenerPrestamos(holder,miPrestamo);

    }

    public int getItemCount() {
        return listaPrestamos.size();
    }






}
