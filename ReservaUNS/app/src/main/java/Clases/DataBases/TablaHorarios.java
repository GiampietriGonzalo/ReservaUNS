
package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import Clases.Principales.Horario;

public class TablaHorarios implements Tabla {


    private static String[] columns={Columns.Id,Columns.HoraInicio,Columns.HoraFin,Columns.IdPrestamo,Columns.DiasSemana};


    /*public static boolean saveHorario(Horario h,SQLiteDatabase db){



    }*/

    public static Horario findHorario(int idHorario, SQLiteDatabase db){

        Horario toReturn=null;
        Cursor cursor=db.query("Horarios",columns, Columns.Id + " = '" + idHorario ,null,null,null,null,null);
        LinkedList<String> diasSemanas= new LinkedList<String>();
        JSONObject lista;

        try {
            while (!cursor.isClosed() && cursor.moveToNext()) {
                lista = new JSONObject(cursor.getString(4));

                for(int i=0; i<lista.length();i++)
                    diasSemanas.addLast(lista.getString(""+i));

                toReturn = new Horario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), diasSemanas);
            }
        }
        catch (JSONException e){System.out.println("ERROR EN EL JSONOject");}
        return toReturn;
    }



    public static int getNextID(SQLiteDatabase db){
        int nextID=0;
        Cursor cursor=db.query("Horarios",columns,null,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext())
            nextID++;


        return nextID;
    }


    private class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String HoraInicio="HoraInicio";
        public static final String HoraFin = "";
        public static final String IdPrestamo =  "IdPrestamo";
        public static final String DiasSemana = "DiasSemana";

    }

}
