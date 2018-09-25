package pipenatr.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class VerificadorDatosFormulario {

    private Context context;

    public VerificadorDatosFormulario(Context context) {
        this.context = context;
    }

    public boolean verificarFecha(String fecha) {

        boolean fechaValida = false;
        String[] valores = fecha.split("/");
        Calendar calendario = Calendar.getInstance();
        int año=0, mes=0, dia=0, diasAux=0;
        int contador = 0;
        Date date= new Date();

        //Cuenta si el usuario ingresó la fecha con el formato indicado
        for( int i = 0; i<fecha.length(); i++)
            if(fecha.charAt(i) == '/')
                contador++;

        if(contador!=2)
            mostrarMensajeError("Las fechas deben tener el formato dd/mm/aaaa");
        else {
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
                    //calendario.set(año, mes, 1);
                    diasAux = calendario.getActualMaximum(calendario.DAY_OF_MONTH);
                    if(dia>0 && dia<=diasAux)
                        fechaValida = true;
                    else
                        mostrarMensajeError("El dia de la fecha " + fecha + " debe estar comprendido entre el 1 y " + diasAux + " de acuerdo al mes seleccionado");
                }
                else
                    mostrarMensajeError("El mes de la fecha " + fecha + " ingresada es inválido");
            }

            else
                mostrarMensajeError("El año de la fecha" + fecha + " es inválido");
        }

        if (fechaValida)
            fechaValida = (mes>=(calendario.get(Calendar.MONTH)+1)) && (año==calendario.get(Calendar.YEAR));


        if( !fechaValida || (mes== calendario.get(Calendar.MONTH)+1 && dia <= calendario.get(Calendar.DAY_OF_MONTH)))  {
            mostrarMensajeError("La fecha " + fecha + " ingresada no es válida, no puede ingresar una fecha pasada.");
            fechaValida=false;
        }
        return fechaValida;
    }

    public boolean verificarHorario(String horaIni)
    {
        boolean horaValida = false;
        String[] horaInicio;
        int hora, min;
        int contador = 0;

        for( int i = 0; i<horaIni.length(); i++)
            if(horaIni.charAt(i) == ':')
                contador++;
        if(contador!=1)
            mostrarMensajeError("El formato de la hora debe ser hh:mm");
        else
        {
            horaInicio = horaIni.split(":");
            hora = Integer.parseInt(horaInicio[0]);
            min = Integer.parseInt(horaInicio[1]);
            if(hora>=8 && hora<=22 && min>=0 && min<=59)
                horaValida = true;
            else
                mostrarMensajeError("El horario de debe estar comprendido entre las 08:00hs y las 22:00hs");
        }
        return horaValida;
    }

    //Verifica si los dias ingresados son distintos
    public boolean verificarDias(LinkedList<String> dias) {
        boolean esPosibleCursar = true;

        for(int i=0; i<dias.size() && esPosibleCursar; i++)
            for(int j=i+1; j<dias.size() && esPosibleCursar; j++)
                if(dias.get(i).matches(dias.get(j)))
                    esPosibleCursar = false;

        return esPosibleCursar;
    }

    public void mostrarMensajeError(String mensaje)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);

        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {//Cierra la ventana
            }
        });

        alerta.setMessage(mensaje);
        alerta.setTitle("Error");
        alerta.setCancelable(true);
        alerta.create().show();
    }
}
