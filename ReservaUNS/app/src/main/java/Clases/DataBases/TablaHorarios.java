
package Clases.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import Clases.Principales.Espacio;
import Clases.Principales.Horario;
import Clases.Principales.Prestamo;

public class TablaHorarios implements Tabla {


    private static String[] columns={Columns.Id,Columns.HoraInicio,Columns.HoraFin,Columns.IdPrestamo,Columns.DiasSemana};


    public static LinkedList<Horario> findHorariosEspacio(Espacio espacio, SQLiteDatabase db, DBController dbC){

        LinkedList<Horario> horarios= new LinkedList<Horario>();
        Horario aux;
        Cursor cursor=db.query("Horarios",columns,null,null,null,null,null,null);
        Prestamo p;

        try {

            while (!cursor.isClosed() && cursor.moveToNext()) {
                p=dbC.findPrestamo(cursor.getInt(3));
                if (dbC.findPrestamo(cursor.getInt(3)).getIdEspacio() == espacio.getID()) {

                    LinkedList<String> diasSemana = new LinkedList<String>();
                    JSONObject json = new JSONObject(cursor.getString(4));

                    for(int i=0; i<json.length();i++)
                        diasSemana.addLast(json.getString(""+i));


                    aux = new Horario(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3),diasSemana);

                    horarios.addLast(aux);
                }

            }

        }
        catch(JSONException e){
            System.out.println("ERROR EN EL JSON DE TABLAHORARIO DEL METODO FINDHORARIOESPACIO");
            e.printStackTrace();
        }


        return horarios;
    }

    public static boolean insertHorario(Horario h,SQLiteDatabase db){

        ContentValues values= new ContentValues();
        LinkedList<String> diasSemana= h.getDiasSemana();
        JSONObject jsonDiasSemana = new JSONObject();
        int i=0;

        values.put("Id",h.getId());
        values.put("HoraInicio",h.getHoraInicio());
        values.put("HoraFin",h.getHoraFin());
        values.put("IdPrestamo",h.getIdPrestamo());

        try{

            for(String dia: diasSemana){
                jsonDiasSemana.put(""+i , dia);
                i++;
            }

            values.put("DiasSemana",jsonDiasSemana.toString());

        }
        catch (JSONException e){System.out.println("Error en json de TablaHorarios");}

        return db.insert("Horarios",null,values)>0;
    }

    public static Horario findHorario(int idHorario, SQLiteDatabase db){

        Horario toReturn=null;
        Cursor cursor=db.query("Horarios",columns, Columns.Id + " = '" + idHorario+"'" ,null,null,null,null,null);
        LinkedList<String> diasSemanas= new LinkedList<String>();
        JSONObject lista;

        try {
            while (!cursor.isClosed() && cursor.moveToNext()) {
                lista = new JSONObject(cursor.getString(4));

                for(int i=0; i<lista.length();i++)
                    diasSemanas.addLast(lista.getString(""+i));

                toReturn = new Horario(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), diasSemanas);
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

    public static boolean eliminarHorario(int idHorario,SQLiteDatabase db){

        boolean exito=false;

        Cursor cursor=db.query("Horarios",columns,Columns.Id +" = '"+idHorario+"'",null,null,null,null);

        if (cursor.moveToFirst()){
            exito= db.delete("Horarios",Columns.Id + " = ?",new String[]{""+idHorario}) > 0;}

        return exito;
    }


    private class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String HoraInicio="HoraInicio";
        public static final String HoraFin = "HoraFin";
        public static final String IdPrestamo =  "IdPrestamo";
        public static final String DiasSemana = "DiasSemana";

    }

}
