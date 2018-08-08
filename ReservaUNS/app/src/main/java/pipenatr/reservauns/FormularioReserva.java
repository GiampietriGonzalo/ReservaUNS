package pipenatr.reservauns;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class FormularioReserva extends Fragment
{
    View myView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.formulario_reserva, container, false);

        //Crea oyente del boton para enviar el formulario
        Button btnEnviar = (Button) myView.findViewById(R.id.btnEnviarSolicitud);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), PantallaReservarEspacio.class);
                Spinner spinner;
                TextView text;
                String capacidad, fecha, horaIni, horaFin;

                //Obtiene la informacion ingresada por el usuario y verifica que la haya ingresado correctamente
                text = (TextView) myView.findViewById(R.id.txtFechaReserva);
                fecha = text.getText().toString();
                text = (TextView) myView.findViewById(R.id.txtCapacidadEspacio);
                capacidad = text.getText().toString();
                text = (TextView) myView.findViewById(R.id.txtHoraInicio);
                horaIni = text.getText().toString();
                text = (TextView) myView.findViewById(R.id.txtHoraFin);
                horaFin = text.getText().toString();
                spinner = (Spinner) myView.findViewById(R.id.spinnerTiposEspacio);

                if(!fecha.matches("") && !capacidad.matches("") && !horaIni.matches("") && !horaFin.matches("") && spinner.getSelectedItemPosition()!=0)
                {
                    //Setea el parametro correspondiente al tipo de espacio que se desea reservar.spinner = (Spinner) findViewById(R.id.spinnerTiposEspacio);
                    System.out.println(spinner.getSelectedItem().toString());
                    intent.putExtra("tipoEspacio", spinner.getSelectedItem().toString());

                    //Setea el parametro correspondiente al nombre del edificio de preferencia (si fue seleccionado)
                    spinner = (Spinner) myView.findViewById(R.id.spinnerEdificios);
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
                    AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                    alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Cierra la ventana
                        }
                    });
                    alerta.setMessage("Debe ingresar toda la información solicitada.");
                    alerta.setTitle("Formulario incompleto");
                    alerta.setCancelable(true);
                    /*
                    if(Integer.parseInt(horaIni.replace(":",""))<Integer.parseInt(horaFin.replace(":","")))
                    {
                        alerta.setMessage("La hora de inicio debe ser menor a la fecha de fin.");
                        alerta.setTitle("Horario inválido");
                    }
                    else
                    {
                        alerta.setMessage("Debe ingresar toda la información solicitada.");
                        alerta.setTitle("Formulario incompleto");
                    }
                    */
                    alerta.create().show();
                }

            }
        });

        //Crea spinner que contiene tipos de espacio
        String[] aux = new String[] {"Tipo espacio...", "Aula", "Laboratorio", "Sala de conferencias", "Sala de reuniones"};
        Spinner spinner = (Spinner) myView.findViewById(R.id.spinnerTiposEspacio);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);

        //Crea spinner que contiene tipos de edificio
        aux = new String[] {"Tipo edificio...", "Departamento", "Edificio de aulas"};
        spinner = (Spinner) myView.findViewById(R.id.spinnerEdificios);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);

        return myView;
    }
}
