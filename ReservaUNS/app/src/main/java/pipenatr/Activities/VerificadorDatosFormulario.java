package pipenatr.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.Calendar;
import java.util.Date;

public class VerificadorDatosFormulario {

    Context context;

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
            mostrarMensajeError("La fecha debe tener el formato dd/mm/aaaa");
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
                        mostrarMensajeError("El dia de la reserva debe estar comprendido entre el 1 y " + diasAux + " de acuerdo al mes seleccionado");
                }
                else
                    mostrarMensajeError("El mes de la reserva no es válido");
            }

            else
                mostrarMensajeError("El año de la reserva no es válido");
        }

        if (fechaValida)
            fechaValida = (mes>=(calendario.get(Calendar.MONTH)+1)) && (año==calendario.get(Calendar.YEAR));


        if( !fechaValida || (mes== calendario.get(Calendar.MONTH)+1 && dia <= calendario.get(Calendar.DAY_OF_MONTH)))  {
            mostrarMensajeError("La fecha ingresada no es válida: ");
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
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {//Cierra la ventana
            }
        });
        alerta.setMessage(mensaje);
        alerta.setTitle("Error");
        alerta.setCancelable(true);
        alerta.create().show();
    }

}
