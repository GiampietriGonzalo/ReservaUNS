package pipenatr.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;
import Clases.Principales.Horario;

public class FormularioReserva extends Fragment {

    private View myView;

    private TextView txtNombre, txtEdificio, txtTipo;
    private LinkedList<Espacio> listaEspacios;
    private DBController controller;
    private ArrayAdapter adapter;
    private ListView listView;

    //Variables del formulario
    private String tipoEspacio, nombreEdificio, fecha;
    private int capacidadEstimada, horaIni, horaFin;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.formulario_reserva, container, false);

        listView= (ListView) myView.findViewById(R.id.LVReservas);
        listView.setVisibility(View.INVISIBLE);
        listView.setEnabled(false);


        controller = controller.getDBController(getActivity());



        //Crea oyente del boton para enviar el formulario

        Button btnEnviar = (Button) myView.findViewById(R.id.btnEnviarSolicitud);
        btnEnviar.setOnClickListener(new ListenerEnviarSolicitud());

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


    private class ListenerEnviarSolicitud implements View.OnClickListener{

        private ListView listView;
        private ScrollView scrollView;
        private Spinner spinnerEspacio,spinnerEdificio;
        private TextView text;
        private String capacidad, fecha, horaIni, horaFin;

        public void onClick(View view) {

            listView=(ListView)myView.findViewById(R.id.LVReservas);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemSeleccionado = (String) listView.getItemAtPosition(position);
                    Log.e("TAG DEF", "ES UN ESPACIO: "+itemSeleccionado.toString());
                }
            });

            //Obtiene la informacion ingresada por el usuario y verifica que la haya ingresado correctamente

            text = (TextView) myView.findViewById(R.id.txtFechaReserva);
            fecha = text.getText().toString();
            text = (TextView) myView.findViewById(R.id.txtCapacidadEspacio);
            capacidad = text.getText().toString();
            text = (TextView) myView.findViewById(R.id.txtHoraInicio);
            horaIni = text.getText().toString();
            text = (TextView) myView.findViewById(R.id.txtHoraFin);
            horaFin = text.getText().toString();
            spinnerEspacio = (Spinner) myView.findViewById(R.id.spinnerTiposEspacio);
            spinnerEdificio= (Spinner) myView.findViewById(R.id.spinnerEdificios);

            tipoEspacio=spinnerEspacio.getSelectedItem().toString();
            nombreEdificio= spinnerEdificio.getSelectedItem().toString();

            capacidadEstimada=Integer.parseInt(capacidad);
            listaEspacios=controller.findEspaciosAReservar(tipoEspacio,nombreEdificio,capacidadEstimada);

            LinkedList<String> toAdapter= new LinkedList<String>();

            for(Espacio espacio: listaEspacios)
                toAdapter.addLast(espacio.toString());


            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, toAdapter);


            listView.setAdapter(adapter);

            if(!listaEspacios.isEmpty())
                Log.e("E3","listaEspacios NO  ESTA VACIA");


            if(!fecha.matches("") && !capacidad.matches("") && !horaIni.matches("") && !horaFin.matches("") && spinnerEspacio.getSelectedItemPosition()!=0) {

                //Verifica si la hora de inicio ingresada es menor a la hora de finalizacion
                if(Integer.parseInt(horaIni.replace(":",""))>=Integer.parseInt(horaFin.replace(":","")))
                    mostrarMensajeError("La hora de inicio de la reserva debe ser anterior a la hora de fin.");
                else {
                    if(verificarFecha(fecha) && verificarHorario(horaIni) && verificarHorario(horaFin)){

                        spinnerEdificio = (Spinner) myView.findViewById(R.id.spinnerEdificios);

                    }
                }
            }
            else
                mostrarMensajeError("Debe ingresar toda la iformacion solicitada.");



            //TODO HABILITAR LISTVIEW
            listView.setVisibility(View.VISIBLE);
            listView.setEnabled(true);

            //TODO DESHABILITAR EL RESTO
            scrollView=(ScrollView) myView.findViewById(R.id.SVReserva);
            scrollView.setVisibility(view.INVISIBLE);
            scrollView.setEnabled(false);

        }


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

    /*
    private void enviarSolicitud() {
        LinkedList<String> fechas = new LinkedList<String>();
        fechas.addLast(fecha);

        Espacio espacioSeleccionado = null;

        for( int i = 0; i<listaEspacios.size(); i++)
            if(comboEspacios.getSelectedItem().toString()==listaEspacios.get(i).getNombre())
                espacioSeleccionado = listaEspacios.get(i);

        SolicitudActiva estadoSolicitud = new SolicitudActiva(9999, 9999);
        Reserva reservaAula = new Reserva(9999, "Descripcion", fecha, 9999, espacioSeleccionado.getID(), Integer.parseInt(SaveSharedPreference.getUserId(this)));
        Horario horarioReserva = new Horario(9999, horaIni, horaFin, 9999,new LinkedList<String>());
        SolicitudReserva nuevaSolicitud = new SolicitudReserva(9999, estadoSolicitud.getId(), Integer.parseInt(SaveSharedPreference.getUserId(this)), horarioReserva.getId(), fecha, "descripcion",numAlumnosComision);


        estadoSolicitud.setIdSolicitud(nuevaSolicitud.getId());
        reservaAula.setIdHorario(horarioReserva.getId());

        controller.insertSolicitudReserva(nuevaSolicitud);
        controller.insertSolicitudActiva(estadoSolicitud);
        controller.insertHorario(horarioReserva);
        controller.insertReserva(reservaAula);
    }*/

    private void consultarTablaEspacios() {


        LinkedList<Horario> listaHorarios;
        Horario horario;
        LinkedList<Espacio> listaEspaciosAux = controller.findEspaciosAReservar(tipoEspacio, nombreEdificio, capacidadEstimada);

        /*

        for (int i = 0; i < listaEspaciosAux.size(); i++) {
            Log.e("E3","LA LISTA NO ESTA VACIA");

            listaHorarios = controller.findHorariosEspacio(listaEspaciosAux.get(i));

            if(!listaHorarios.isEmpty()) {
                for( int k = 0; k<listaHorarios.size(); k++) {
                    horario = listaHorarios.get(k);
                    boolean encontre = false;
                    for(int j = 0; j<horario.getDiasSemana().size(); j++) {
                        if(horario.getDiasSemana().get(j).equals(fecha)) {
                            if(horario.getHoraFin()<=horaIni || horario.getHoraInicio()>=horaFin) {
                                listaIds.addLast(listaEspaciosAux.get(i).getNombre());
                                listaEspacios.addLast(listaEspaciosAux.get(i));
                            }
                        }
                    }
                }
            }
            else{
                listaIds.addLast(listaEspaciosAux.get(i).getNombre());
                //listaEspacios.addLast(listaEspaciosAux.get(i));
                Log.e("E2","entro bien");
            }
        }
        */
    }





}
