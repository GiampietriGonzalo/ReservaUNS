package pipenatr.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import Clases.Principales.Espacio;
import Clases.Principales.Prestamo;

public class RegistrarAsignacion extends Fragment {

    private VerificadorDatosFormulario verificador;
    private DBController controller;
    private View myView;
    private LayoutInflater inflater;
    private int id;

    //variables datos asigna3
    private LinkedList<String> diasAsignacion, horariosAsignacion, aulas;
    private LinkedList<Espacio> espacios;
    private String capacidad, fechaInicioVig, fechaFinVig;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        myView = inflater.inflate(R.layout.formulario_reserva, container, false);

        verificador = new VerificadorDatosFormulario(getActivity());
        id = 0;

        myView.findViewById(R.id.SVReserva).setVisibility(myView.GONE);
        myView.findViewById(R.id.spinnerEdificios).setVisibility(myView.GONE);
        myView.findViewById(R.id.spinnerTiposEspacio).setVisibility(myView.GONE);
        myView.findViewById(R.id.txtSpinnerEd).setVisibility(myView.GONE);
        myView.findViewById(R.id.txtSpinnerEsp).setVisibility(myView.GONE);
        myView.findViewById(R.id.campoCantidadDias).setVisibility(myView.VISIBLE);
        Button btnEnviar = myView.findViewById(R.id.btnEnviarSolicitud);
        btnEnviar.setText("Registrar asignación");
        btnEnviar.setOnClickListener(new ListenerAsignacion());

        Button btnSeleccionar = (Button) myView.findViewById(R.id.btnSeleccionarCantidad);
        btnSeleccionar.setOnClickListener( new ListenerConfirmacionCantidad());

        return myView;
    }

    private class ListenerAsignacion implements View.OnClickListener {

        private ListView listView;
        private ScrollView scrollView;
        private EditText text;

        @Override
        public void onClick(View view) {

            //Obtiene la informacion ingresada por el usuario y verifica que la haya ingresado correctamente
            text = (EditText) myView.findViewById(R.id.txtCapacidadEspacio);
            capacidad = text.getText().toString().trim();
            text = (EditText) myView.findViewById(R.id.txtInicioPeriodo);
            fechaInicioVig = text.getText().toString().trim();
            text = (EditText) myView.findViewById(R.id.txtFinPeriodo);
            fechaFinVig = text.getText().toString().trim();

            Spinner dia;
            TextView aux;
            for(int i=0; i<id; i= i+4) {
                String num = String.valueOf(i);
                int resID = getResources().getIdentifier(num, "id", myView.getContext().getPackageName());
                dia = (Spinner) myView.findViewById(resID);
                diasAsignacion.addLast(dia.getSelectedItem().toString().trim().toLowerCase());

                num = String.valueOf((i+1));
                resID = getResources().getIdentifier(num, "id", myView.getContext().getPackageName());
                aux = (TextView) myView.findViewById(resID);
                horariosAsignacion.addLast(aux.getText().toString().trim());

                num = String.valueOf((i+2));
                resID = getResources().getIdentifier(num, "id", myView.getContext().getPackageName());
                aux = (TextView) myView.findViewById(resID);
                horariosAsignacion.addLast(aux.getText().toString().trim());

                num = String.valueOf((i+3));
                resID = getResources().getIdentifier(num, "id", myView.getContext().getPackageName());
                aux = (TextView) myView.findViewById(resID);
                aulas.addLast(aux.getText().toString().trim());
            }



            //Verifica si datos son nulos
            if(!horariosAsignacion.contains("") && !aulas.contains("") && !capacidad.matches("") && !fechaFinVig.matches("") && !fechaInicioVig.matches(""))
            {
                //Verifica si la hora de inicio ingresada es menor a la hora de finalizacion
                boolean error = false;
                for(int i=0; i<horariosAsignacion.size(); i= i+2)
                   if(Integer.parseInt(horariosAsignacion.get(i).replace(":",""))>=Integer.parseInt(horariosAsignacion.get((i+1)).replace(":","")))
                       error = true;
                if(error)
                    verificador.mostrarMensajeError("La hora de inicio de una asignación debe ser anterior a la hora de fin.");
                else {
                    //Verifica que la fecha y las horas de inicio y fin sean validas
                    for(int i=0; i<horariosAsignacion.size(); i++)
                        if(!verificador.verificarHorario(horariosAsignacion.get(i)))
                            error = true;
                    if(!error && verificador.verificarFecha(fechaFinVig) && verificador.verificarFecha(fechaInicioVig)) {
                        if(consultarDispoibilidad()) {

                        }
                    }
                }
            }
            else
                verificador.mostrarMensajeError("Debe ingresar toda la iformacion solicitada.");

        }

    }

    //Consulto si los espacios estan disponibles de acuerdo a las especificaciones del usuario
    private boolean consultarDispoibilidad() {
        boolean puedeAsignar = true;
        LinkedList<Prestamo> prestamos = controller.getPrestamos();
        espacios = new LinkedList<>();
        /*
        for(int i=0; i<aulas.size(); i++) {
            espacios.addLast(controller.findEspacio());
        }
        */
        return puedeAsignar;
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
                int cantDiasAsig = Integer.parseInt(text.getText().toString());
                LinearLayout layoutDiaHora = (LinearLayout) myView.findViewById(R.id.layoutRS);

                for(int i=0; i<cantDiasAsig; i++) {
                    View viewZ = inflater.inflate(R.layout.formulario_sublayout_dias_horarios, null);
                    layoutDiaHora.addView(viewZ, 7);
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
