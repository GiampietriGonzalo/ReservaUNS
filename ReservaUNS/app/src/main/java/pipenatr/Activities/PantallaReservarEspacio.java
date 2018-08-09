package pipenatr.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Espacio;
import Clases.Principales.Horario;

public class PantallaReservarEspacio extends AppCompatActivity {

    private Spinner comboEspacios;
    private TextView txtNombre, txtEdificio, txtTipo;
    private LinkedList<String> listaIds;
    private LinkedList<Espacio> listaEspacios;
    private DBController controller;
    //Variables del formulario
    private String tipoEspacio, nombreEdificio, fecha;
    private int numAlumnosComision, horaIni, horaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_reservar_espacio);

        //Seteo las variables con los valores insertados en el formulario
        tipoEspacio = getIntent().getStringExtra("tipoEspacio").toLowerCase().trim();
        nombreEdificio = getIntent().getStringExtra("nombreEdificio").toLowerCase().trim();
        fecha = getIntent().getStringExtra("fecha").replace("/","").toLowerCase().trim();
        horaIni = Integer.parseInt(getIntent().getStringExtra("horaIni").replace(":","").toLowerCase().trim());
        horaFin = Integer.parseInt(getIntent().getStringExtra("horaFin").replace(":","").toLowerCase().trim());
        numAlumnosComision = Integer.parseInt(getIntent().getStringExtra("numAlumnosComision"));

        txtEdificio = (TextView) findViewById(R.id.txtEdificioSpinner);
        txtNombre = (TextView) findViewById(R.id.txtNombreSpinner);

        comboEspacios = (Spinner) findViewById(R.id.comboEspacios);
        comboEspacios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txtEdificio.setText(listaEspacios.get(comboEspacios.getSelectedItemPosition()).getEdificio().getNombre());
                txtNombre.setText(listaEspacios.get(comboEspacios.getSelectedItemPosition()).getNombre());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        controller = controller.getDBController(this);

        consultarTablaEspacios();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaIds);
        comboEspacios.setAdapter(adaptador);
    }

    private void consultarTablaEspacios() {
        listaIds = new LinkedList();
        LinkedList<Horario> listaHorarios;
        Horario horario;
        LinkedList<Espacio> listaEspaciosAux = controller.findEspaciosAReservar(tipoEspacio, nombreEdificio, numAlumnosComision);
        for (int i = 0; i < listaEspaciosAux.size(); i++) {
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
            else
                listaIds.addLast(listaEspaciosAux.get(i).getNombre());
        }
    }

}