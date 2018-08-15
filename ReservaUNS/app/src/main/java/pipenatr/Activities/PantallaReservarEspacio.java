package pipenatr.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Estados.EstadoSolicitud;
import Clases.Estados.SolicitudActiva;
import Clases.Principales.Espacio;
import Clases.Principales.Horario;
import Clases.Principales.Prestamo;
import Clases.Principales.Reserva;
import Clases.Principales.Solicitud;
import Clases.Principales.SolicitudReserva;

public class PantallaReservarEspacio extends AppCompatActivity {

    private Spinner comboEspacios;
    private TextView txtNombre, txtEdificio, txtTipo;
    private LinkedList<String> listaIds;
    private LinkedList<Espacio> listaEspacios;
    private DBController controller;
    //Variables del formulario
    private String tipoEspacio, nombreEdificio, fecha;
    private int numAlumnosComision, horaIni, horaFin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_reservar_espacio);

        //Seteo las variables con los valores insertados en el formulario
        tipoEspacio = getIntent().getStringExtra("tipoEspacio");
        nombreEdificio = getIntent().getStringExtra("nombreEdificio");
        fecha = getIntent().getStringExtra("fecha").replace("/","").toLowerCase().trim();
        horaIni = Integer.parseInt(getIntent().getStringExtra("horaIni").replace(":","").toLowerCase().trim());
        horaFin = Integer.parseInt(getIntent().getStringExtra("horaFin").replace(":","").toLowerCase().trim());
        numAlumnosComision = Integer.parseInt(getIntent().getStringExtra("numAlumnosComision"));

        txtEdificio = (TextView) findViewById(R.id.txtEdificioSpinner);
        txtNombre = (TextView) findViewById(R.id.txtNombreSpinner);

        comboEspacios = (Spinner) findViewById(R.id.comboEspacios);


        Button boton = (Button) findViewById(R.id.btnSeleccionarEspacio);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarSolicitud();
            }
        });

        controller = controller.getDBController(this);

        consultarTablaEspacios();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaIds);
        comboEspacios.setAdapter(adaptador);

        comboEspacios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txtEdificio.setText(listaEspacios.get(comboEspacios.getSelectedItemPosition()).getEdificio().getNombre());
                txtNombre.setText(listaEspacios.get(comboEspacios.getSelectedItemPosition()).getNombre());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void enviarSolicitud() {
        LinkedList<String> fechas = new LinkedList<String>();
        fechas.addLast(fecha);

        Espacio espacioSeleccionado = null;

        for( int i = 0; i<listaEspacios.size(); i++)
            if(comboEspacios.getSelectedItem().toString()==listaEspacios.get(i).getNombre())
                espacioSeleccionado = listaEspacios.get(i);

        SolicitudActiva estadoSolicitud = new SolicitudActiva(9999, 9999);
        Reserva reservaAula = new Reserva(9999, "Descripcion", fecha, 9999, espacioSeleccionado.getID(), Integer.parseInt(SaveSharedPreference.getUserId(this)));
        Horario horarioReserva = new Horario(9999, horaIni, horaFin, 9999,new LinkedList<String>());
        SolicitudReserva nuevaSolicitud = new SolicitudReserva(9999, estadoSolicitud.getId(), Integer.parseInt(SaveSharedPreference.getUserId(this)), horarioReserva.getId(), fecha, "descripcion",numAlumnosComision);


        estadoSolicitud.setIdSolicitud(nuevaSolicitud.getId());
        reservaAula.setIdHorario(horarioReserva.getId());

        controller.insertSolicitudReserva(nuevaSolicitud);
        controller.insertSolicitudActiva(estadoSolicitud);
        controller.insertHorario(horarioReserva);
        controller.insertReserva(reservaAula);
    }

    private void consultarTablaEspacios() {

        listaIds = new LinkedList();
        LinkedList<Horario> listaHorarios;
        Horario horario;
        LinkedList<Espacio> listaEspaciosAux = controller.findEspaciosAReservar(tipoEspacio, nombreEdificio, numAlumnosComision);

        for (int i = 0; i < listaEspaciosAux.size(); i++) {
            Log.e("E3","LA LISTA NO ESTA VACIA");

            listaHorarios = controller.findHorariosEspacio(listaEspaciosAux.get(i));

            if(!listaHorarios.isEmpty()) {
                for( int k = 0; k<listaHorarios.size(); k++) {
                    horario = listaHorarios.get(k);
                    boolean encontre = false;
                    for(int j = 0; j<horario.getDiasSemana().size(); j++) {
                        if(horario.getDiasSemana().get(j)==fecha) {
                            if(horario.getHoraFin()<=horaIni || horario.getHoraInicio()>=horaFin) {
                                listaIds.addLast(listaEspaciosAux.get(i).getNombre());
                                listaEspacios.addLast(listaEspaciosAux.get(i));
                            }
                        }
                    }
                }
            }
            else{
                listaIds.addLast(listaEspaciosAux.get(i).getNombre());
                Log.e("E2","entro bien");
            }
        }
    }

}