package pipenatr.Activities;

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

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Estados.EstadoSolicitud;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;

public class FormularioReserva extends Fragment {
    View myView;

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
                    //Verifica si la hora de inicio ingresada es menor a la hora de finalizacion
                    if(Integer.parseInt(horaIni.replace(":",""))>=Integer.parseInt(horaFin.replace(":","")))
                        mostrarMensajeError("La hora de inicio de la reserva debe ser anterior a la hora de fin.");
                    else
                    {
                        if(verificarFecha(fecha) && verificarHorario(horaIni) && verificarHorario(horaFin))
                        {
                            //Setea el parametro correspondiente al tipo de espacio que se desea reservar.spinner = (Spinner) findViewById(R.id.spinnerTiposEspacio);
                            intent.putExtra("tipoEspacio", spinner.getSelectedItem().toString());

                            //Setea el parametro correspondiente al nombre del edificio de preferencia (si fue seleccionado)
                            spinner = (Spinner) myView.findViewById(R.id.spinnerEdificios);
                            if(spinner.getSelectedItemPosition()==0)
                                intent.putExtra("nombreEdificio", "Edificio");
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
                    }
                }
                else
                    mostrarMensajeError("Debe ingresar toda la iformacion solicitada.");
            }
        });

        //Crea spinner que contiene tipos de espacio

        String[] aux = new String[] {"Tipo espacio...", "Aula", "Laboratorio", "Sala de conferencias", "Sala de reuniones"};
        Spinner spinner = (Spinner) myView.findViewById(R.id.spinnerTiposEspacio);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);

        //Crea spinner que contiene tipos de edificio
        LinkedList<Edificio> edificios= DBController.getEdificios();
        aux = new String[edificios.size()+1];
        int i=1;
        aux[0]="Edificio";
        for(Edificio edificio:edificios){
            aux[i]=edificio.getNombre();
            i++;
        }
        aux[0]="Edificio";

        spinner = (Spinner) myView.findViewById(R.id.spinnerEdificios);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, aux);
        spinner.setAdapter(adapter);

        return myView;
    }

    private boolean verificarFecha(String fecha)
    {
        boolean fechaValida = false;
        String[] valores = fecha.split("/");
        Calendar calendario = Calendar.getInstance();
        int diasEnMes, año, mes, dia, diasAux;
        int contador = 0;

        //Cuenta si el usuario ingresó la fecha con el formato indicado
        for( int i = 0; i<fecha.length(); i++)
            if(fecha.charAt(i) == '/')
                contador++;
        if(contador!=2)
            mostrarMensajeError("La fecha debe tener el formato dd/mm/aaaa");
        else
        {
            // verifico que el año sea correcto
            año = Integer.parseInt(valores[2]);
            if(año>2017)
            {
                //verifico que el mes sea correcto
                mes = Integer.parseInt(valores[1]);
                if(mes>0 && mes<13)
                {
                    // de acuerdo al año y mes verifico que los dias sean correctos
                    dia = Integer.parseInt(valores[0]);
                    calendario.set(año, mes, 1);
                    diasAux = calendario.getActualMaximum(calendario.DAY_OF_MONTH);
                    if(dia>0 && dia<=diasAux)
                        fechaValida = true;
                    else
                        mostrarMensajeError("El dia de la reserva debe estar comprendido entre el 1 y " + diasAux + " de acuerdo al mes seleccionado");
                }
                else
                    mostrarMensajeError("El mes de la reserva no es válido");
            }
            else
                mostrarMensajeError("El año de la reserva no es válido");
        }
        return fechaValida;
    }

    private boolean verificarHorario(String horaIni)
    {
        boolean horaValida = false;
        String[] horaInicio;
        int hora, min;
        int contador = 0;
        for( int i = 0; i<horaIni.length(); i++)
            if(horaIni.charAt(i) == ':')
                contador++;
        if(contador!=1)
            mostrarMensajeError("El formato de la hora de reserva debe ser hh:mm");
        else
        {
            horaInicio = horaIni.split(":");
            hora = Integer.parseInt(horaInicio[0]);
            min = Integer.parseInt(horaInicio[1]);
            if(hora>=0 && hora<=23 && min>=0 && min<=59)
                horaValida = true;
            else
                mostrarMensajeError("El horario de reserva debe estar comprendido entre las 00:00hs y las 23:59hs");
        }
        return horaValida;
    }

    public void mostrarMensajeError(String mensaje)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Cierra la ventana
            }
        });
        alerta.setMessage(mensaje);
        alerta.setTitle("Error");
        alerta.setCancelable(true);
        alerta.create().show();
    }
}
