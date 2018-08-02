package pipenatr.reservauns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

    public void enviarSolicitud(View view)
    {
        Intent intent = new Intent(this, PantallaReservarEspacio.class);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTiposEspacio);
        intent.putExtra("tipoEspacio", spinner.getSelectedItem().toString());
        spinner = (Spinner) findViewById(R.id.spinnerEdificios);
        intent.putExtra("nombreEdificio", spinner.getSelectedItem().toString());
    }
}
