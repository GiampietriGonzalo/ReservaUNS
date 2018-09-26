package pipenatr.Activities;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.Estados.StateController;
import Clases.Principales.Asignacion;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;
import Clases.Principales.Horario;
import Clases.Principales.Prestamo;

public class RegistrarAsignacion extends Fragment {

    private VerificadorDatosFormulario verificador;
    private DBController controller;
    private View myView;
    private LayoutInflater inflater;
    private int id;

    //variables datos asigna3
    private LinkedList<String> diasAsignacion, horariosAsignacion, aulas;
    private LinkedList<Espacio> espacios;
    private String capacidad, fechaInicioVig, fechaFinVig;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        myView = inflater.inflate(R.layout.formulario_reserva, container, false);

        verificador = new VerificadorDatosFormulario(getActivity());
        id = 0;

        diasAsignacion = new LinkedList<>();
        horariosAsignacion = new LinkedList<>();
        aulas = new LinkedList<>();
        controller= DBController.getDBController(container.getContext());

        myView.findViewById(R.id.SVReserva).setVisibility(myView.GONE);
        myView.findViewById(R.id.spinnerEdificios).setVisibility(myView.GONE);
        myView.findViewById(R.id.spinnerTiposEspacio).setVisibility(myView.GONE);
        myView.findViewById(R.id.txtSpinnerEd).setVisibility(myView.GONE);
        myView.findViewById(R.id.txtSpinnerEsp).setVisibility(myView.GONE);
        myView.findViewById(R.id.campoCantidadDias).setVisibility(myView.VISIBLE);
        Button btnEnviar = myView.findViewById(R.id.btnEnviarSolicitud);
        btnEnviar.setText("Registrar asignación");
        btnEnviar.setOnClickListener(new ListenerAsignacion());

        Button btnSeleccionar = (Button) myView.findViewById(R.id.btnSeleccionarCantidad);
        btnSeleccionar.setOnClickListener( new ListenerConfirmacionCantidad());

        return myView;
    }

    private class ListenerAsignacion implements View.OnClickListener {

        private ScrollView scrollView;
        private EditText text;

        public void onClick(View view) {

            //Obtiene la informacion ingresada por el usuario y verifica que la haya ingresado correctamente
            text = (EditText) myView.findViewById(R.id.txtCapacidadEspacio);
            capacidad = text.getText().toString().trim();
            text = (EditText) myView.findViewById(R.id.txtInicioPeriodo);
            fechaInicioVig = text.getText().toString().trim();
            text = (EditText) myView.findViewById(R.id.txtFinPeriodo);
            fechaFinVig = text.getText().toString().trim();

            Spinner dia, aula;
            TextView aux;

            for(int i=0; i<id; i= i+4) {
                String num = String.valueOf(i);
                int resID = getResources().getIdentifier(num, "id", myView.getContext().getPackageName());
                dia = (Spinner) myView.findViewById(resID);
                diasAsignacion.addLast(dia.getSelectedItem().toString().trim().toLowerCase());

                num = String.valueOf((i+1));
                resID = getResources().getIdentifier(num, "id", myView.getContext().getPackageName());
                aux = (TextView) myView.findViewById(resID);
                horariosAsignacion.addLast(aux.getText().toString().trim());

                num = String.valueOf((i+2));
                resID = getResources().getIdentifier(num, "id", myView.getContext().getPackageName());
                aux = (TextView) myView.findViewById(resID);
                horariosAsignacion.addLast(aux.getText().toString().trim());

                num = String.valueOf((i+3));
                resID = getResources().getIdentifier(num, "id", myView.getContext().getPackageName());
                aula = (Spinner) myView.findViewById(resID);
                aulas.addLast(aula.getSelectedItem().toString());
            }



            //Verifica si datos son nulos
            if(!horariosAsignacion.contains("") && !aulas.contains("") && !capacidad.matches("") && !fechaFinVig.matches("") && !fechaInicioVig.matches(""))
            {
                //Verifica si la hora de inicio ingresada es menor a la hora de finalizacion
                boolean error = false;
                for(int i=0; i<horariosAsignacion.size(); i= i+2)
                   if(Integer.parseInt(horariosAsignacion.get(i).replace(":",""))>=Integer.parseInt(horariosAsignacion.get((i+1)).replace(":","")))
                       error = true;
                if(error)
                    verificador.mostrarMensajeError("La hora de inicio de una asignación debe ser anterior a la hora de fin.");
                else {
                    //Verifica que la fecha y las horas de inicio y fin sean validas
                    for(int i=0; i<horariosAsignacion.size(); i++)
                        if(!verificador.verificarHorario(horariosAsignacion.get(i)))
                            error = true;
                    if(!error && verificador.verificarFecha(fechaFinVig) && verificador.verificarFecha(fechaInicioVig)) {
                        //Si se los espacios se encuentran disponibles en sus respectivos dias (dias deben ser diferentes) y horarios, se realiza la asignacion
                        if(consultarDispoibilidad()) {

                            //Muestra ventana para confirmar la reserva del aula
                            AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                            alerta.setPositiveButton("Asignar", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    realizarAsignacion();
                                    Toast.makeText(getActivity(), "Se realizó exitosamente la asignación.", Toast.LENGTH_LONG).show();
                                    getFragmentManager().popBackStack();
                                    getFragmentManager().beginTransaction().commit();
                                    Intent intent = new Intent(myView.getContext(), PantallaPrincipal.class);
                                    getActivity().finish();
                                    startActivity(intent);
                                }
                            });

                            alerta.setNegativeButton("Cancelar", null);
                            alerta.setMessage("¿desea realizar la asignación?");
                            alerta.setTitle("Asignar espacios");
                            alerta.setCancelable(true);
                            alerta.create().show();
                        }
                    }
                }
            }
            else
                verificador.mostrarMensajeError("Debe ingresar toda la iformacion solicitada.");

        }

    }

    private void realizarAsignacion() {
        int indiceHorarios = 0;

        for(int i=0; i<diasAsignacion.size(); i++) {

            int horarioInicio = Integer.parseInt(horariosAsignacion.get(indiceHorarios).replace(":",""));
            int horarioFin = Integer.parseInt(horariosAsignacion.get(indiceHorarios+1).replace(":",""));
            indiceHorarios = indiceHorarios + 2;

            LinkedList<String> dia = new LinkedList<>();
            dia.addLast(diasAsignacion.get(i));

            Horario nuevoHorario = new Horario(9999, horarioInicio, horarioFin, 9999, dia);
            Asignacion asigna100 = new Asignacion(9999, diasAsignacion.get(i), nuevoHorario.getId(), espacios.get(i).getID(), fechaInicioVig, fechaFinVig, StateController.getEstadoAceptado());
            nuevoHorario.setIdPrestamo(asigna100.getId());

            controller.insertAsignacion(asigna100);
            controller.insertHorario(nuevoHorario);
        }
    }

    //Consulto si los espacios estan disponibles de acuerdo a las especificaciones del usuario
    private boolean consultarDispoibilidad() {

        Calendar calendario = Calendar.getInstance();

        LinkedList<Prestamo> prestamos = controller.getPrestamos();

        espacios = new LinkedList<Espacio>();

        Espacio esp;
        Prestamo pres;
        Horario hor;

        boolean puedeAsignar = true;
        int indiceHorarios = 0;

        //Obtiene los espacios a partir de los nombres seleccionados por el usuario
        for(int i=0; i<aulas.size() && puedeAsignar; i++) {
            esp = buscarEspacio(aulas.get(i));

            espacios.addLast(controller.findEspacio(esp.getID()));
            for(int j=0; j<prestamos.size() && puedeAsignar; j++) {
                pres = prestamos.get(j);
                //Si el prestamo ocurre en el mismo espacio
                if(pres.getIdEspacio() == esp.getID()) {
                    calendario.setTime(getDate(pres.getFecha()));
                    //Si el prestamo ocurre en el mismo dia
                    if(calendario.get(Calendar.DAY_OF_WEEK) == getValorNumericoDia(diasAsignacion.get(i))) {
                        //Verifica si el prestamo ocurre un horario distinto, caso contrario no se puede asignar el espacio en esa fecha y ese horario
                        hor = controller.findHorario(pres.getIdHorario());
                        if(hor.getHoraFin()>Integer.parseInt(horariosAsignacion.get(indiceHorarios).replace(":","")) && hor.getHoraInicio()<Integer.parseInt(horariosAsignacion.get(indiceHorarios+1).replace(":","")))
                            puedeAsignar = false;
                    }
                }
            }
            indiceHorarios = indiceHorarios + 2;
        }

        if(!puedeAsignar)
            verificador.mostrarMensajeError("No se puede realizar la asignación ya que existen conflictos con una asignación existente.");

        return puedeAsignar;
    }

    private class ListenerConfirmacionCantidad implements View.OnClickListener {

        public void onClick(View view) {

            EditText elemento;
            Spinner spinnerDias, spinnerEspacios;

            //Crea el arreglo con los espacios para insertar en el adapter del spinner
            LinkedList<Espacio> espacios = obtenerEspacios();
            String[] espaciosArray =  new String[espacios.size()];
            for(int i=0; i<espacios.size(); i++)
                espaciosArray[i] = espacios.get(i).getNombre();

            //Crea el arreglo con los dias de la semana para insertar en el spinner
            String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
            ArrayAdapter<String> adapter, adapterEspacios;

            TextView text = (TextView) myView.findViewById(R.id.txtCantDias);

            if(text.getText().toString().equals(""))
                verificador.mostrarMensajeError("Debe ingresar la cantidad de clases semanales de la asignación");
            else {
                int cantDiasAsig = Integer.parseInt(text.getText().toString());
                LinearLayout layoutDiaHora = (LinearLayout) myView.findViewById(R.id.layoutRS);

                for(int i=0; i<cantDiasAsig; i++) {
                    View viewZ = inflater.inflate(R.layout.formulario_sublayout_dias_horarios, null);
                    layoutDiaHora.addView(viewZ, 7);

                    //Setea el campo de fecha como invisible
                    elemento = (EditText) viewZ.findViewById(R.id.txtFechaReserva);
                    elemento.setVisibility(myView.GONE);

                    //Crea el spinner de dias
                    spinnerDias = (Spinner) viewZ.findViewById(R.id.spinnerDiasAsignacion);
                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dias);
                    spinnerDias.setAdapter(adapter);
                    spinnerDias.setId(id);
                    spinnerDias.setVisibility(myView.VISIBLE);
                    id++;

                    //Hora inicio
                    elemento = (EditText) viewZ.findViewById(R.id.txtHoraInicio);
                    elemento.setId(id);
                    id++;

                    //Hora fin
                    elemento = (EditText) viewZ.findViewById(R.id.txtHoraFin);
                    elemento.setId(id);
                    id++;

                    //Crea el spinner con los espacios
                    spinnerEspacios = (Spinner) viewZ.findViewById(R.id.spinnerEspacios);
                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, espaciosArray);
                    spinnerEspacios.setAdapter(adapter);
                    spinnerEspacios.setId(id);
                    spinnerEspacios.setVisibility(myView.VISIBLE);
                    id++;
                }

                myView.findViewById(R.id.campoCantidadDias).setVisibility(myView.GONE);
                myView.findViewById(R.id.SVReserva).setVisibility(myView.VISIBLE);
                myView.findViewById(R.id.txtvPeriodo).setVisibility(myView.VISIBLE);
                myView.findViewById(R.id.formLayoutPeriodo).setVisibility(myView.VISIBLE);


            }
        }
    }

    //Retorna una lista que contiene todos los espacios cargados en el sistema
    private LinkedList<Espacio> obtenerEspacios() {
        LinkedList<Edificio> edificios = controller.getEdificios();
        LinkedList<Espacio> espacios, espaciosEdificio;
        espacios = new LinkedList<Espacio>();
        Edificio ed;

        for(int i=0; i<edificios.size(); i++) {
            ed = edificios.get(i);
            espaciosEdificio = ed.getEspacios();
            for(int j=0; j<espaciosEdificio.size(); j++)
                espacios.addLast(espaciosEdificio.get(j));
        }

        return espacios;
    }

    //Dado el nombre de un espacio retorna el objeto espacio asociado al mismo
    private Espacio buscarEspacio(String nombre) {

        Espacio espacio = null;
        LinkedList<Espacio> espacios = obtenerEspacios();

        boolean encontre = false;

        for(int i=0; i<espacios.size() && !encontre; i++) {
            if(espacios.get(i).getNombre().equals(nombre)) {
                espacio = espacios.get(i);
                encontre = true;
            }
        }

        return espacio;
    }

    //Retorna un objeto de tipo Date a partir de un string que contiene un fecha en formato dd/mm/aaaa
    private Date getDate(String fecha) {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/aaaa");
        Date date = null ;
        try {
            date = format.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private boolean verificarDias() {
        boolean diasValidos = true;
        String dia;

        for(int p=0; p<diasAsignacion.size() && diasValidos; p++) {
            dia = diasAsignacion.get(p);
            for(int h=p+1; h<diasAsignacion.size() && diasValidos; h++) {
                if(dia.matches(diasAsignacion.get(h)))
                    diasValidos = false;
            }
        }

        return diasValidos;
    }

    //Determina el dia valor numerico de un dia de la semana dado
    private int getValorNumericoDia(String dia) {
        int indice = 0;

        switch (dia) {
            case "Lunes": indice = 1;
                break;
            case "Martes": indice = 2;
                break;
            case "Miercoles": indice = 3;
                break;
            case "Jueves": indice = 4;
                break;
            case "Viernes": indice = 5;
                break;
            case "Sabado": indice = 6;
                break;
        }

        return indice;
    }

}
