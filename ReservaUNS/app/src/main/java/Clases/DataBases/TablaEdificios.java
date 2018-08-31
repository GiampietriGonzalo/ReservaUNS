package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.security.PublicKey;
import java.util.LinkedList;

import Clases.Principales.Departamento;
import Clases.Principales.Edificio;
import Clases.Principales.EdificioAulas;
import Clases.Principales.Espacio;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaEdificios implements Tabla {

    private static final String[] columns={Columns.Id, Columns.Nombre , Columns.Direccion, Columns.Telefono, Columns.IdEncargado,Columns.Codigo,Columns.Tipo};

    public static Edificio findDepartamento(int idEdificio, SQLiteDatabase db) {

        Edificio toReturn=null;
        LinkedList<Integer> espacios;

        Cursor cursor=db.query("Edificios",columns, Columns.Nombre +" = '"+idEdificio+"'",null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            //Busco los espacios vinculados a este edificio
            espacios=TablaEspacios.findEspacios(idEdificio,db);

            if(cursor.getString(6).equals("Departamento")) {
                toReturn = new Departamento(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(6));
                toReturn.setEspacios(espacios);
            }


        }

        return toReturn;

    }

    public static LinkedList<Edificio> getEdificios(SQLiteDatabase db){

        LinkedList<Edificio> edificios= new LinkedList<Edificio>();
        Edificio aux= null;

        Cursor cursor= db.query("Edificios",null,null,null,null,null,null);

        while(!cursor.isClosed() && cursor.moveToNext()){

            switch (cursor.getString(6)){

                case "Departamento":{
                    aux = new Departamento(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(6));
                    break;
                }

                case "EdificioAulas":{
                    aux= new EdificioAulas(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
                    break;
                }
            }

            if(aux!=null)
                edificios.addLast(aux);
        }
        return edificios;
    }

    public static Edificio findEdificio(int idEdificio, SQLiteDatabase db){

        Edificio toReturn=null;
        Cursor cursor=db.query("Edificios",columns, Columns.Id +" = '"+idEdificio+"'",null,null,null,null);

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

    public static int getIdEdificio(String nombreEdificio, SQLiteDatabase db){

        int id=9999;
        Cursor cursor;

        if( nombreEdificio!="Seleccionar"){

            cursor=db.query("Edificios",columns, Columns.Nombre +" = '"+nombreEdificio.trim()+"'",null,null,null,null);
            if (!cursor.isClosed() && cursor.moveToNext())
                id=cursor.getInt(0);
        }


        return id;

    }

    public static int getNextID(SQLiteDatabase db){

        int nextID=0;
        Cursor cursor=db.query("Edificios",columns,null,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext())
            nextID++;


        return nextID;
    }

    private static class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String Nombre = "Nombre";
        public static final String Direccion= "Direccion";
        public static final String Telefono = "Telefono";
        public static final String IdEncargado= "IdEncargado";
        public static final String Codigo= "Codigo";
        public static final String Tipo= "Tipo";
    }

}
