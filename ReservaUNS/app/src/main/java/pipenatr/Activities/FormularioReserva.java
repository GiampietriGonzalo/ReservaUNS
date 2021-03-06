package pipenatr.Activities;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Estados.Estado;
import Clases.Estados.StateController;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;
import Clases.Principales.Horario;
import Clases.Principales.Prestamo;
import Clases.Principales.Solicitud;
import Clases.Principales.SolicitudReserva;

public class FormularioReserva extends Fragment {

    private View myView;

    private VerificadorDatosFormulario verificador;
    private LinkedList<Espacio> listaEspacios;
    private LinkedList<String> toAdapter;
    private DBController controller;
    private ArrayAdapter adapter;
    private ListView listView;

    //Variables del formulario
    private String tipoEspacio, nombreEdificio, fecha, capacidad, horaIni, horaFin;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.formulario_reserva, container, false);
        listView= (ListView) myView.findViewById(R.id.LVReservas);
        listView.setVisibility(View.INVISIBLE);
        listView.setEnabled(false);

        LinearLayout layoutDiaHora = (LinearLayout) myView.findViewById(R.id.layoutRS);
        View view = inflater.inflate(R.layout.formulario_sublayout_dias_horarios, null);
        layoutDiaHora.addView(view, 7);

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

        verificador = new VerificadorDatosFormulario(getActivity());

        return myView;
    }


    private class ListenerEnviarSolicitud implements View.OnClickListener{

        private ListView listView;
        private ScrollView scrollView;
        private Spinner spinnerEspacio,spinnerEdificio;
        private TextView text;

        public void onClick(View view)
        {
            listView =(ListView)myView.findViewById(R.id.LVReservas);

            //Muestra una ventana para verificar si el usuario desea reservar el espacio
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String itemSeleccionado = (String) listView.getItemAtPosition(position);
                    final int pos = position;

                    //Muestra ventana para confirmar la reserva del aula
                    AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                    alerta.setPositiveButton("Reservar", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            enviarSolicitud(pos);
                            Toast.makeText(getActivity(), "Su solicitud fue enviada para revisión", Toast.LENGTH_LONG).show();
                            getFragmentManager().popBackStack();
                            getFragmentManager().beginTransaction().commit();
                            Intent intent = new Intent(myView.getContext(), PantallaPrincipal.class);
                            getActivity().finish();
                            startActivity(intent);
                        }
                    });

                    alerta.setNegativeButton("Cancelar", null);
                    alerta.setMessage("¿desea reservar este espacio?");
                    alerta.setTitle("Reservar espacio");
                    alerta.setCancelable(true);
                    alerta.create().show();
                }
            });

            //Obtiene la informacion ingresada por el usuario y verifica que la haya ingresado correctamente
            text = (TextView) myView.findViewById(R.id.txtFechaReserva);
            fecha = text.getText().toString().trim();
            text = (TextView) myView.findViewById(R.id.txtCapacidadEspacio);
            capacidad = text.getText().toString().trim();
            text = (TextView) myView.findViewById(R.id.txtHoraInicio);
            horaIni = text.getText().toString().trim();
            text = (TextView) myView.findViewById(R.id.txtHoraFin);
            horaFin = text.getText().toString().trim();
            spinnerEspacio = (Spinner) myView.findViewById(R.id.spinnerTiposEspacio);
            spinnerEdificio = (Spinner) myView.findViewById(R.id.spinnerEdificios);
            tipoEspacio = spinnerEspacio.getSelectedItem().toString();
            nombreEdificio = spinnerEdificio.getSelectedItem().toString();

            if(!fecha.matches("") && !capacidad.matches("") && !horaIni.matches("") && !horaFin.matches("") && spinnerEspacio.getSelectedItemPosition()!=0)
            {
                //Verifica si la hora de inicio ingresada es menor a la hora de finalizacion
                if(Integer.parseInt(horaIni.replace(":",""))>=Integer.parseInt(horaFin.replace(":","")))
                    verificador.mostrarMensajeError("La hora de inicio de la reserva debe ser anterior a la hora de fin.");
                else
                    //Verifica que la fecha y las horas de inicio y fin sean validas
                    if(verificador.verificarFecha(fecha) && verificador.verificarHorario(horaIni) && verificador.verificarHorario(horaFin))
                    {
                        //Inicializo listas donde se almacenaran los ids de los espacios para mostrar en la lista y los espacios para reservar
                        listaEspacios = new LinkedList<Espacio>();
                        toAdapter= new LinkedList<String>();

                        //Consulto los espacios disponibles de acuerdo a las especificaciones del usuario
                        consultarTablaEspacios();

                        //Si no se encontraron espacios muestra un cartel de notificacion, caso contrario, muestra la lista de espacios disponibles
                        if(toAdapter.isEmpty()) {
                            
                            AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());

                            alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {//Cierra la ventana
                                }
                            });

                            alerta.setMessage("No se encontraron espacios disponibles que satifagan el criterio de búsqueda.");
                            alerta.setTitle("Aviso");
                            alerta.setCancelable(true);
                            alerta.create().show();

                        } else {
                            //Seteo el adapter para mostrar en el listView
                            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, toAdapter);
                            listView.setAdapter(adapter);

                            //HABILITAR LISTVIEW
                            listView.setVisibility(View.VISIBLE);
                            listView.setEnabled(true);

                            //DESHABILITAR EL RESTO
                            scrollView=(ScrollView) myView.findViewById(R.id.SVReserva);
                            scrollView.setVisibility(view.GONE);
                            scrollView.setEnabled(false);

                        }
                    }
            }
            else
                verificador.mostrarMensajeError("Debe ingresar toda la iformacion solicitada.");
        }
    }

    //Revisa la lista de espacios en el sistema y guarda aquellos que se encuentran disponibles en el dia y horario especificados
    private void consultarTablaEspacios()
    {
        boolean encontre;

        LinkedList<Espacio> listaEspaciosPrestados = new LinkedList<>();
        LinkedList<Espacio> listaEspaciosAReservar = controller.findEspaciosAReservar(tipoEspacio, nombreEdificio, Integer.parseInt(capacidad));
        LinkedList<Solicitud> solicitudes = controller.getSolicitudes();
        LinkedList<Prestamo> prestamos = controller.getPrestamos();

        Espacio espacio;
        Prestamo prestamo;
        Horario horarioPrestamo;


        //Para todos los espacios que cumplen los requisitos del usuario
        for(int i=0; i<listaEspaciosAReservar.size(); i++) {
            espacio = listaEspaciosAReservar.get(i);
            encontre = false;

            //Busco en la lista de prestamos si el espacio se encuentra asignado a un prestamo activo
            for(int p=0; p<prestamos.size() && !encontre;p++) {
                prestamo = prestamos.get(p);

                if(prestamo.getEstadoString().matches("Activo") && prestamo.getIdEspacio()==espacio.getID()) {
                    horarioPrestamo = prestamo.getHorario();

                    //Si el horario del prestamo coincide con el horario del usuario, no se puede realizar la solicitud, caso contrario se almacena el espacio
                    if( !(horarioPrestamo.getHoraFin()>Integer.parseInt(horaIni.replace(":", "")) && horarioPrestamo.getHoraInicio()<Integer.parseInt(horaFin.replace(":", ""))) ) {
                        listaEspacios.addLast(espacio);
                        toAdapter.addLast(listaEspaciosAReservar.get(i).getNombre()+"-----"+listaEspaciosAReservar.get(i).getEdificio().getNombre());
                    }
                    encontre = true;

                }
            }

            if(!encontre) {
                listaEspacios.addLast(espacio);
                toAdapter.addLast(listaEspaciosAReservar.get(i).getNombre()+"-----"+listaEspaciosAReservar.get(i).getEdificio().getNombre());
            }
        }
    }

    private void enviarSolicitud(int position){

        LinkedList<Integer> idHorarios = new LinkedList<Integer>();
        LinkedList<String> fechas = new LinkedList<String>();
        fechas.addLast(fecha);
        String nombreEspacioSeleccionado;
        Espacio espacioSeleccionado = null;
        Estado estado;

        for( int i = 0; i<listaEspacios.size(); i++){
            nombreEspacioSeleccionado= recuperarNombre(listView.getItemAtPosition(position).toString());
            if(nombreEspacioSeleccionado.equals(listaEspacios.get(i).getNombre()))
                espacioSeleccionado = listaEspacios.get(i);
        }


        Horario horarioReserva = new Horario(9999, Integer.parseInt(horaIni.replace(":","")), Integer.parseInt(horaFin.replace(":","")), 9999, fechas);
        estado= StateController.getEstadoActivo();
        SolicitudReserva nuevaSolicitud = new SolicitudReserva(9999, Integer.parseInt(SaveSharedPreference.getUserId(getActivity())), idHorarios, fecha, espacioSeleccionado.getID(), Integer.parseInt(capacidad),estado);

        idHorarios.addLast(horarioReserva.getId());
        nuevaSolicitud.setIdHorario(idHorarios);

        controller.insertSolicitudReserva(nuevaSolicitud);

        controller.insertHorario(horarioReserva);
    }

    private String recuperarNombre(String nombreEspacioEdificio){

        int i=0;
        String toReturn=new String();

        while(nombreEspacioEdificio.charAt(i)!='-'){
            toReturn=toReturn+nombreEspacioEdificio.charAt(i);
            i++;
        }

        return toReturn;
    }
}