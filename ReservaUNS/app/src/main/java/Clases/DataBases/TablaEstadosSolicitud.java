package Clases.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import Clases.Estados.EstadoSolicitud;
import Clases.Estados.SolicitudAceptada;
import Clases.Estados.SolicitudActiva;
import Clases.Estados.SolicitudCancelada;
import Clases.Estados.SolicitudRechazada;
import Clases.Principales.Solicitud;

public class TablaEstadosSolicitud implements Tabla {

    private static final String[] columns={Columns.Id, Columns.IdSolicitud , Columns.Tipo};


    public static EstadoSolicitud findEstadoSolicitud(int idEstadoSolicitud, SQLiteDatabase db){

        EstadoSolicitud toReturn=null;
        Cursor cursor=db.query("EstadosSolicitudes",columns,Columns.Id +" = '"+idEstadoSolicitud+"'",null,null,null,null);

        while(!cursor.isClosed() && cursor.moveToNext()){

            switch (cursor.getString(2)){

                case "SolicitudActiva":{
                    toReturn= new SolicitudActiva(cursor.getInt(0),cursor.getInt(1));
                    break;
                }

                case "SolicitudAceptada":{
                    toReturn= new SolicitudAceptada(cursor.getInt(0),cursor.getInt(1));
                    break;
                }

                case "SolicitudRechazada":{
                    toReturn= new SolicitudRechazada(cursor.getInt(0),cursor.getInt(1));
                    break;
                }

                case "SolicitudCancelada":{
                    toReturn= new SolicitudCancelada(cursor.getInt(0),cursor.getInt(1));
                    break;
                }

            }//Fin switch

        }

        return toReturn;
    }

    public static boolean insertSolicitudActiva(EstadoSolicitud estado, SQLiteDatabase db) {

        ContentValues values= new ContentValues();
        values.put("Id",estado.getId());
        values.put("IdSolicitud",estado.getIdSolicitud());
        values.put("Tipo","EstadoSolicitudActiva");

        return db.insert("EstadosSolicitudes",null,values) > 0;
    }

    public static boolean insertSolicitudAceptada(EstadoSolicitud estado, SQLiteDatabase db) {

        ContentValues values= new ContentValues();
        values.put("Id",estado.getId());
        values.put("IdSolicitud",estado.getIdSolicitud());
        values.put("Tipo","SolicitudAceptada");

        return db.insert("EstadosSolicitudes",null,values) > 0;
    }

    public static boolean insertSolicitudRechazada(EstadoSolicitud estado, SQLiteDatabase db) {

        ContentValues values= new ContentValues();
        values.put("Id",estado.getId());
        values.put("IdSolicitud",estado.getIdSolicitud());
        values.put("Tipo","SolicitudRechazada");

        return db.insert("EstadosSolicitudes",null,values) > 0;
    }

    public static boolean insertSolicitudCancelada(EstadoSolicitud estado, SQLiteDatabase db) {

        ContentValues values= new ContentValues();
        values.put("Id",estado.getId());
        values.put("IdSolicitud",estado.getIdSolicitud());
        values.put("Tipo","SolicitudCancelada");

        return db.insert("EstadosSolicitudes",null,values) > 0;
    }




    public static int getNextID(SQLiteDatabase db){
        int nextID=0;
        Cursor cursor=db.query("EstadosSolicitudes",columns,null,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext())
            nextID++;

        return nextID;
    }


    private static class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String IdSolicitud = "IdSolicitud";
        public static final String Tipo= "Tipo";
    }


}
