package pipenatr.reservauns;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PantallaFormularioReserva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_formulario_reserva);

        String[] aux = new String[] {"Tipo espacio...", "Aula", "Laboratorio", "Sala de conferencias", "Sala de reuniones"};
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTiposEspacio);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);

        aux = new String[] {"Tipo edificio...", "Departamento", "Edificio de aulas"};
        spinner = (Spinner) findViewById(R.id.spinnerEdificios);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);
    }

    public void enviarSolicitud(View view)
    {
        Intent intent = new Intent(this, PantallaReservarEspacio.class);
        Spinner spinner;
        TextView text;
        String capacidad, fecha, horaIni, horaFin;

        //Obtiene la informacion ingresada por el usuario y verifica que la haya ingresado correctamente
        text = (TextView) findViewById(R.id.txtFechaReserva);
        fecha = text.getText().toString();
        text = (TextView) findViewById(R.id.txtCapacidadEspacio);
        capacidad = text.getText().toString();
        text = (TextView) findViewById(R.id.txtHoraInicio);
        horaIni = text.getText().toString();
        text = (TextView) findViewById(R.id.txtHoraFin);
        horaFin = text.getText().toString();
        spinner = (Spinner) findViewById(R.id.spinnerTiposEspacio);

        if(!fecha.matches("") && !capacidad.matches("") && !horaIni.matches("") && !horaFin.matches("") && spinner.getSelectedItemPosition()!=0)
        {
            //Setea el parametro correspondiente al tipo de espacio que se desea reservar.
            spinner = (Spinner) findViewById(R.id.spinnerTiposEspacio);
            System.out.println(spinner.getSelectedItem().toString());
            intent.putExtra("tipoEspacio", spinner.getSelectedItem().toString());

            //Setea el parametro correspondiente al nombre del edificio de preferencia (si fue seleccionado)
            spinner = (Spinner) findViewById(R.id.spinnerEdificios);
            if(spinner.getSelectedItemPosition()==0)
                intent.putExtra("nombreEdificio", "9999");
            else
                intent.putExtra("nombreEdificio", spinner.getSelectedItem().toString());

            //Setea el parametro correspondiente a la cantidad de alumnos de la comision
            intent.putExtra("numAlumnosComision", capacidad);

            //Setea el parametro correspondiente a la fecha de la reserva
            intent.putExtra("fecha", fecha);

            //Setea los parametros correspondientes a la hora de inicio y hora de finalizacion de la reserva (respectivamente)
            intent.putExtra("horaIni", horaIni);
            intent.putExtra("horaFin", horaFin);

            startActivity(intent);
        }
        else
        {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setMessage("Debe ingresar toda la información solicitada.");
            alerta.setTitle("Formulario incompleto");
            alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Cierra la ventana
                }
            });
            alerta.setCancelable(true);
            alerta.create().show();
        }
    }
}
