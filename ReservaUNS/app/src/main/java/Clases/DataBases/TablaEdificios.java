package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.LinkedList;

import Clases.Principales.Departamento;
import Clases.Principales.Edificio;
import Clases.Principales.EdificioAulas;
import Clases.Principales.Espacio;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaEdificios implements Tabla {

    private static final String[] columns={Columns.Id, Columns.Nombre , Columns.Direccion, Columns.Telefono, Columns.IdEncargado,Columns.Tipo ,Columns.Codigo};

    public static Edificio findDepartamento(int idEdificio, SQLiteDatabase db) {

        Edificio toReturn=null;
        LinkedList<Integer> espacios;

        Cursor cursor=db.query("Edificios",columns, Columns.Nombre +" = '"+idEdificio,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            //Busco los espacios vinculados a este edificio
            espacios=TablaEspacios.findEspacios(idEdificio,db);

            if(cursor.getString(5)=="Departamento") {
                toReturn = new Departamento(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(6));
                toReturn.setEspacios(espacios);
            }


        }

        return toReturn;

    }

    public static Edificio findEdificio(int idEdificio, SQLiteDatabase db){

        Edificio toReturn=null;
        Cursor cursor=db.query("Edificios",columns, Columns.Nombre +" = '"+idEdificio,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            switch (cursor.getString(6)){

                case "Departamento":{
                    toReturn = new Departamento(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(6));
                    break;
                }

                case "EdificioAulas":{
                    toReturn= new EdificioAulas(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
                    break;
                }
            }
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
        public static final String Tipo= "Tipo";
    }

}
