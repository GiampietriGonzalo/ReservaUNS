package pipenatr.reservauns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Aula;
import Clases.Principales.Espacio;

public class PantallaReservarEspacio extends AppCompatActivity {

    private Spinner comboEspacios;
    private TextView txtNombre, txtEdificio, txtTipo;
    private LinkedList<String> listaIds;
    private LinkedList<Espacio> listaEspacios;
    private DBController controller;
    //Variables del formulario
    private String tipoEspacio, nombreEdificio, fecha, horaIni, horaFin;
    private int numAlumnosComision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_reservar_espacio);

        //Seteo las variables con los valores insertados en el formulario
        tipoEspacio = getIntent().getStringExtra("tipoEspacio");
        nombreEdificio = getIntent().getStringExtra("nombreEdificio");
        fecha = getIntent().getStringExtra("fecha");
        horaIni = getIntent().getStringExtra("horaIni");
        horaFin = getIntent().getStringExtra("horaFin");
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
        listaEspacios = controller.findEspaciosAReservar(tipoEspacio.toLowerCase().trim(), nombreEdificio.toLowerCase().trim(), numAlumnosComision);
        for (int i = 0; i < listaEspacios.size(); i++) {
            listaIds.addLast(listaEspacios.get(i).getNombre());
        }
    }

}
