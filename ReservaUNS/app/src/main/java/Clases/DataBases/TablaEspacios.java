package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import org.json.JSONObject;

import java.util.LinkedList;

import Clases.Principales.Aula;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;
import Clases.Principales.Laboratorio;
import Clases.Principales.SalaConferencias;
import Clases.Principales.SalaReuniones;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaEspacios implements Tabla {

    private static final String[] columns={Columns.Id, Columns.Nombre , Columns.Capacidad, Columns.Piso, Columns.Cuerpo, Columns.IdEdificio, Columns.Tipo ,Columns.NombreAnterior};


    public static LinkedList<Espacio> findEspaciosAReservarSinEdificioPreferencia(String tipo, int capacidadEstimada, SQLiteDatabase db){

        LinkedList<Espacio> toReturn= new LinkedList<Espacio>();
        Espacio aux=null;

        Cursor cursor=db.rawQuery("SELECT * FROM Espacios WHERE Tipo = ?  AND Capacidad = ?", new String[] {tipo, ""+capacidadEstimada});

        while (!cursor.isClosed() && cursor.moveToNext()) {

            switch (tipo){

                case "Aula":{
                    aux = new Aula(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getString(6), cursor.getInt(3), cursor.getString(4));
                    break;
                }

                case "Laboratorio":{
                    aux = new Laboratorio(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getInt(3), cursor.getString(4));
                    break;
                }

                case "SalaReuniones":{
                    aux = new SalaReuniones(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getInt(3), cursor.getString(4));
                    break;
                }

                case "SalaConferencias":{
                    aux = new SalaConferencias(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getInt(3), cursor.getString(4));
                    break;
                }

            } //fin switch

            toReturn.addLast(aux);

        }

        return toReturn;

    }


    public static LinkedList<Espacio> findEspaciosAReservar(String tipo,int idEdificioPreferencia, int capacidadEstimada, SQLiteDatabase db){

        LinkedList<Espacio> toReturn= new LinkedList<Espacio>();
        Espacio aux=null;


        Cursor cursor=db.rawQuery("SELECT * FROM Espacios WHERE Tipo = ? AND idEdificio= ? AND Capacidad = ?", new String[] {tipo, ""+idEdificioPreferencia, ""+capacidadEstimada});

        while (!cursor.isClosed() && cursor.moveToNext()) {
            
            switch (tipo){

                case "Aula":{
                    aux = new Aula(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getString(6), cursor.getInt(3), cursor.getString(4));
                    break;
                }

                case "Laboratorio":{
                    aux = new Laboratorio(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getInt(3), cursor.getString(4));
                    break;
                }

                case "SalaReuniones":{
                    aux = new SalaReuniones(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getInt(3), cursor.getString(4));
                    break;
                }

                case "SalaConferencias":{
                    aux = new SalaConferencias(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getInt(3), cursor.getString(4));
                    break;
                }
                
            } //fin switch

            toReturn.addLast(aux);

        }

        return toReturn;
    }

    /**
     * @return Si el aula es encontrada la retorna, caso contrario retorna null
     * */
    public static Aula findAula(String nombreAula, SQLiteDatabase db) {

        Aula toReturn=null;
        String nombreEdificio;
        JSONObject jsonEdificio= new JSONObject();

        Cursor cursor=db.query("Espacios",columns,Columns.Nombre +" = '"+nombreAula.trim().toLowerCase()+"'",null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            if(cursor.getString(4)=="Aula")
                toReturn= new Aula(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(5),cursor.getString(6), cursor.getInt(3), cursor.getString(4));

            nombreEdificio= cursor.getString(3);

        }


        return toReturn;
    }

    /**
     * Dado el id de un edificio, retorna todos los Espacios que se encuentra en él.
     * */

    public static Espacio findEspacio(int idEspacio, SQLiteDatabase db) {


        Espacio toReturn=null;
        Cursor cursor=db.query("Espacios",columns,Columns.Id +" = '"+idEspacio+"'",null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            switch (cursor.getString(4)) {

                case "Aula":{
                            toReturn = new Aula(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getString(6), cursor.getInt(3), cursor.getString(4));
                            break;
                }

                case "Laboratorio":{
                            toReturn = new Laboratorio(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getInt(3), cursor.getString(4));
                            break;
                }

                case "SalaReuniones":{
                            toReturn = new SalaReuniones(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getInt(3), cursor.getString(4));
                            break;
                }

                case "SalaConferencias":{
                            toReturn = new SalaConferencias(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(5), cursor.getInt(3), cursor.getString(4));
                            break;
                }


            }//Fin switch
        }

        return toReturn;

    }


    public static LinkedList<Integer> findEspacios(int idEdificio, SQLiteDatabase db) {

        LinkedList<Integer> espacios=null;

        Cursor cursor=db.query("Espacios",columns,Columns.IdEdificio +" = '"+idEdificio+"'",null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext())
            espacios.addLast(cursor.getInt(0));
        return espacios;

    }




    private static class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String Nombre = "Nombre";
        public static final String Capacidad = "Capacidad";
        public static final String Piso= "Piso";
        public static final String Cuerpo= "Cuerpo";
        public static final String IdEdificio = "NombreEdificio";
        public static final String NombreAnterior= "NombreAnterior";
        public static final String Tipo= "Tipo";

    }

}
