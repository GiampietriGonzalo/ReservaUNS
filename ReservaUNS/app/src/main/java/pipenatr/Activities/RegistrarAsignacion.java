package pipenatr.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;

import Clases.DataBases.DBController;

public class RegistrarAsignacion extends Fragment {

    private VerificadorDatosFormulario verificador;
    private DBController controller;
    private View myView;
    private LayoutInflater inflater;
    private int id;

    //variables datos asigna3
    private LinkedList<String> diasAsignacion, horariosAsignacion, aulas;
    private String capacidad;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        myView = inflater.inflate(R.layout.formulario_reserva, container, false);

        verificador = new VerificadorDatosFormulario(getActivity());
        id = 0;

        myView.findViewById(R.id.SVReserva).setVisibility(myView.GONE);
        myView.findViewById(R.id.spinnerEdificios).setVisibility(myView.GONE);
        myView.findViewById(R.id.spinnerTiposEspacio).setVisibility(myView.GONE);
        myView.findViewById(R.id.campoCantidadDias).setVisibility(myView.VISIBLE);
        Button btnEnviar = myView.findViewById(R.id.btnEnviarSolicitud);
       // btnEnviar.setOnClickListener(new ListenerAsignacion);

        Button btnSeleccionar = (Button) myView.findViewById(R.id.btnSeleccionarCantidad);
        btnSeleccionar.setOnClickListener( new ListenerConfirmacionCantidad());

        return myView;
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



            /*
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
                */

        }

    }

    private class ListenerConfirmacionCantidad implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            EditText elemento;
            Spinner spinnerDias;
            String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
            ArrayAdapter<String> adapter;
            TextView text = (TextView) myView.findViewById(R.id.txtCantDias);
            if(text.getText().toString().equals(""))
                verificador.mostrarMensajeError("Debe ingresar la cantidad de clases semanales de la asignación");
            else {
                Log.e("adsad", text.getText().toString());
                int cantDiasAsig = Integer.parseInt(text.getText().toString());
                LinearLayout layoutDiaHora = (LinearLayout) myView.findViewById(R.id.layoutRS);

                for(int i=0; i<cantDiasAsig; i++) {
                    View viewZ = inflater.inflate(R.layout.formulario_sublayout_dias_horarios, null);
                    layoutDiaHora.addView(viewZ, 5);
                    myView.findViewById(R.id.campoAulaDia).setVisibility(myView.VISIBLE);

                    //Setea el campo de fecha como invisible y lo reemplaza por un spinner
                    elemento = (EditText) viewZ.findViewById(R.id.txtFechaReserva);
                    elemento.setVisibility(myView.GONE);

                    spinnerDias = (Spinner) viewZ.findViewById(R.id.spinnerDiasAsignacion);
                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dias);
                    spinnerDias.setAdapter(adapter);
                    spinnerDias.setId(id);
                    spinnerDias.setVisibility(myView.VISIBLE);
                    id++;
                    elemento = (EditText) viewZ.findViewById(R.id.txtHoraInicio);
                    elemento.setId(id);
                    id++;
                    elemento = (EditText) viewZ.findViewById(R.id.txtHoraFin);
                    elemento.setId(id);
                    id++;
                    elemento = (EditText) viewZ.findViewById(R.id.txtAulaDia);
                    elemento.setId(id);
                    id++;
                }

                myView.findViewById(R.id.campoCantidadDias).setVisibility(myView.GONE);
                myView.findViewById(R.id.btnSeleccionarCantidad).setVisibility(myView.GONE);
                myView.findViewById(R.id.SVReserva).setVisibility(myView.VISIBLE);
                myView.findViewById(R.id.txtvPeriodo).setVisibility(myView.VISIBLE);
                myView.findViewById(R.id.formLayoutPeriodo).setVisibility(myView.VISIBLE);

                diasAsignacion = new LinkedList<>();
                horariosAsignacion = new LinkedList<>();
                aulas = new LinkedList<>();
            }
        }
    }
}
