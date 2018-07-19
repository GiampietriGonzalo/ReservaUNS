package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.LinkedList;

import Clases.Principales.Edificio;
import Clases.Principales.Espacio;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaEdificios implements Tabla {

    private static final String[] columns={Columns.Id, Columns.Nombre , Columns.Direccion, Columns.Telefono, Columns.IdEncargado, Columns.Codigo};

    public static Edificio findDepartamento(String nombreEdificio, SQLiteDatabase db) {

        Edificio toReturn=null;
        LinkedList<Espacio> espacios;

        Cursor cursor=db.query("Edificios",columns, Columns.Nombre +" = '"+nombreEdificio.trim().toLowerCase()+"'",null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            //Busco los espacios vinculados a este edificio
           


        }


        return toReturn;

    }

    private static class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String Nombre = "Nombre";
        public static final String Direccion= "Direcion";
        public static final String Telefono = "Telefono";
        public static final String IdEncargado= "IdEncargado";
        public static final String Codigo= "Codigo";
    }

}
