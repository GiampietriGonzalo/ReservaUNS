package pipenatr.reservauns;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import Clases.Principales.Espacio;

public class AdapterConsulta extends RecyclerView.Adapter<AdapterConsulta.ViewHolderConsultas>
{

    private LinkedList<Espacio> listaEspacios;

    public AdapterConsulta(LinkedList<Espacio> listaEspacios) {
        this.listaEspacios = listaEspacios;
    }

    @NonNull
    @Override
    public ViewHolderConsultas onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.consultar_solicitud, null, false);
        return new ViewHolderConsultas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderConsultas viewHolderConsultas, int i) {
        viewHolderConsultas.asignarDatos(listaEspacios.get(i));
    }

    @Override
    public int getItemCount() {
        return listaEspacios.size();
    }

    public class ViewHolderConsultas extends RecyclerView.ViewHolder {

        TextView nombre, tipo, fecha;

        public ViewHolderConsultas(@NonNull View itemView) {
            super(itemView);
            tipo = (TextView) itemView.findViewById(R.id.txtTipo);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            fecha = (TextView) itemView.findViewById(R.id.txtFecha);

        }

        public void asignarDatos(Espacio espacio) {
            tipo.setText(espacio.getID());
            nombre.setText(espacio.getNombre()); ///////////////////// ACOMODAR ESTA WEA
        }
    }
}
