package pipenatr.reservauns;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class PantallaFormularioReserva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_formulario_reserva);

        String[] aux = new String[] {"Aula", "Laboratorio", "Sala de conferencias", "Sala de reuniones"};
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTiposEspacio);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);

        aux = new String[] {"Departamento", "Edificio de aulas"};
        spinner = (Spinner) findViewById(R.id.spinnerEdificios);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);
    }
}
