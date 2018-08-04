package pipenatr.reservauns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Espacio;

public class PantallaReservarEspacio extends AppCompatActivity {

    private Spinner comboEspacios;
    private TextView txtId;
    private LinkedList<String> listaIds;
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


        comboEspacios = (Spinner) findViewById(R.id.comboEspacios);

        txtId = (TextView) findViewById(R.id.txtId);

        controller = controller.getDBController(this);

        consultarTablaEspacios();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaIds);
        comboEspacios.setAdapter(adaptador);
    }

    private void consultarTablaEspacios() {
        LinkedList<Espacio> listaEspacios;
        listaIds = new LinkedList();
        listaEspacios = controller.findEspaciosAReservar(tipoEspacio.toLowerCase().trim(), nombreEdificio.toLowerCase().trim(), numAlumnosComision);
        for (int i = 0; i < listaEspacios.size(); i++) {
            listaIds.addLast(listaEspacios.get(i).getNombre());
        }
    }
}
