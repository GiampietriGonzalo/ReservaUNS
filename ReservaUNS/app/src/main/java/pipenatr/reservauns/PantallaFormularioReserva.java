package pipenatr.reservauns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PantallaFormularioReserva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Clases.Otras.R.layout.activity_pantalla_formulario_reserva);

        String[] aux = new String[] {"Aula", "Laboratorio", "Sala de conferencias", "Sala de reuniones"};
        Spinner spinner = (Spinner) findViewById(Clases.Otras.R.id.spinnerTiposEspacio);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);

        aux = new String[] {"Departamento", "Edificio de aulas"};
        spinner = (Spinner) findViewById(Clases.Otras.R.id.spinnerEdificios);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);
    }
}
