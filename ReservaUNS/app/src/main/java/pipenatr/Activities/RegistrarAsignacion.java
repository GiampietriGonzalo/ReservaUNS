package pipenatr.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Espacio;

public class RegistrarAsignacion extends Fragment {

    private VerificadorDatosFormulario verificador;
    private DBController controller;
    private View myView;
    private LayoutInflater inflater;

    //variables datos asigna3
    private String[] diasAsignacion, horariosAsignacion;
    private String capacidad;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        myView = inflater.inflate(R.layout.formulario_reserva, container, false);

        verificador = new VerificadorDatosFormulario(getActivity());

        myView.findViewById(R.id.SVReserva).setVisibility(myView.GONE);
        myView.findViewById(R.id.spinnerEdificios).setVisibility(myView.GONE);
        myView.findViewById(R.id.spinnerTiposEspacio).setVisibility(myView.GONE);
        myView.findViewById(R.id.campoCantidadDias).setVisibility(myView.VISIBLE);
        Button btn = myView.findViewById(R.id.btnEnviarSolicitud);
       // btn.setOnClickListener(new ListenerAsignacion);

        final EditText text = myView.findViewById(R.id.txtCantDias);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                TextView text = (TextView) editable;
                modificarFormulario(Integer.parseInt(text.getText().toString()));
            }
        });

        return myView;
    }

    public void modificarFormulario(int cantDiasAsig) {
        LinearLayout layoutDiaHora = (LinearLayout) myView.findViewById(R.id.layoutRS);

        for(int i=0; i<cantDiasAsig; i++) {
            View view = inflater.inflate(R.layout.formulario_sublayout_dias_horarios, null);
            layoutDiaHora.addView(view, 5);
            myView.findViewById(R.id.campoAulaDia).setVisibility(View.VISIBLE);
        }

        myView.findViewById(R.id.campoCantidadDias).setVisibility(myView.GONE);
        myView.findViewById(R.id.SVReserva).setVisibility(myView.VISIBLE);
    }


    private class ListenerAsignacion implements View.OnClickListener {

        private ListView listView;
        private ScrollView scrollView;
        private TextView text;

        @Override
        public void onClick(View view) {

            //Obtiene la informacion ingresada por el usuario y verifica que la haya ingresado correctamente
            text = (TextView) myView.findViewById(R.id.txtCapacidadEspacio);
            capacidad = text.getText().toString().trim();



            if(!fecha.matches("") && !capacidad.matches("") && !horaIni.matches("") && !horaFin.matches("") && spinnerEspacio.getSelectedItemPosition()!=0)
            {
                //Verifica si la hora de inicio ingresada es menor a la hora de finalizacion
                if(Integer.parseInt(horaIni.replace(":",""))>=Integer.parseInt(horaFin.replace(":","")))
                    verificador.mostrarMensajeError("La hora de inicio de la reserva debe ser anterior a la hora de fin.");
                else
                    //Verifica que la fecha y las horas de inicio y fin sean validas
                    if(verificador.verificarFecha(fecha) && verificador.verificarHorario(horaIni) && verificador.verificarHorario(horaFin))
                    {
                        //Inicializo listas donde se almacenaran los ids de los espacios para mostrar en la lista y los espacios para reservar
                        listaEspacios = new LinkedList<Espacio>();
                        toAdapter= new LinkedList<String>();

                        //Consulto los espacios disponibles de acuerdo a las especificaciones del usuario
                        consultarTablaEspacios();

                        //Seteo el adapter para mostrar en el listView
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, toAdapter);
                        listView.setAdapter(adapter);

                        //HABILITAR LISTVIEW
                        listView.setVisibility(View.VISIBLE);
                        listView.setEnabled(true);

                        //DESHABILITAR EL RESTO
                        scrollView=(ScrollView) myView.findViewById(R.id.SVReserva);
                        scrollView.setVisibility(view.INVISIBLE);
                        scrollView.setEnabled(false);
                    }
            }
            else
                verificador.mostrarMensajeError("Debe ingresar toda la iformacion solicitada.");

        }

    }
}
