package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import org.json.JSONObject;

import Clases.Principales.Aula;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaAulas implements Tabla {

    private static final String[] columns={Columns.Id, Columns.Nombre , Columns.Capacidad,  Columns.NombreEdficio, Columns.NombreAnterior};


    public static Aula find(String nombreAula, SQLiteDatabase db) {

        Aula toReturn=null;
        String nombreEdificio;
        JSONObject jsonEdificio= new JSONObject();

        Cursor cursor=db.query("Aulas",columns,Columns.Nombre +" = '"+nombreAula.trim().toLowerCase()+"'",null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            nombreEdificio= cursor.getString(3);
            //BUSCAR EDIFICIO


        }


        return toReturn;
    }


    private static class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String Nombre = "Nombre";
        public static final String Capacidad = "Capacidad";
        public static final String NombreEdficio = "NombreEdificio";
        public static final String NombreAnterior= "NombreAnterior";

    }

}
