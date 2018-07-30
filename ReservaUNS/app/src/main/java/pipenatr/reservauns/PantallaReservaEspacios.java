package pipenatr.reservauns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Espacio;

public class PantallaReservaEspacios extends AppCompatActivity {

    Spinner comboEspacios;
    TextView txtId;
    LinkedList<String> listaIds;
    LinkedList<Espacio> listaEspacios;
    DBController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_reserva_espacios);

        comboEspacios = (Spinner) findViewById(R.id.comboEspacios);

        txtId = (TextView) findViewById(R.id.txtId);

        controller = controller.getDBController(this);

        consultarTablaEspacios();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaIds);
        comboEspacios.setAdapter(adaptador);
    }

    private void consultarTablaEspacios() {
        listaEspacios = controller.findEspaciosAReservar("Aula", "9999", 69);
        for (int i = 0; i < listaEspacios.size(); i++) {
            listaIds.addLast(listaEspacios.get(i).getNombre());
        }
    }
}
