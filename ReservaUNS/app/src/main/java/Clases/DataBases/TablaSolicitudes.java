package Clases.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.net.ConnectException;

import Clases.Principales.Solicitud;
import Clases.Principales.SolicitudAsignacion;
import Clases.Principales.SolicitudReserva;

public class TablaSolicitudes implements Tabla {

    private static final String[] columns={Columns.Id, Columns.IdEstado , Columns.IdAutor, Columns.IdHorario, Columns.Fecha, Columns.Descripcion, Columns.CapacidadEstimada,Columns.Tipo};

    public static Solicitud findSolicitud(int idSolicitud,SQLiteDatabase db){

        Solicitud toReturn=null;

        Cursor cursor=db.query("Solicitudes",columns,Columns.Id +" = '"+idSolicitud+"'",null,null,null,null);

        while(!cursor.isClosed() && cursor.moveToNext()){

            switch (cursor.getString(7)){

                case "SolicitudReserva":{
                    toReturn= new SolicitudReserva(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                    break;
                }

                case "SolicitudAsignacion":{
                    toReturn= new SolicitudAsignacion(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                    break;
                }

            }//Fin switch
        }

        return toReturn;
    }


    public static boolean insertSolicitudReserva(Solicitud solicitud, SQLiteDatabase db){

        ContentValues values= new ContentValues();
        values.put("Id",solicitud.getId());
        values.put("IdEstado",solicitud.getIdEstado());
        values.put("IdAutor",solicitud.getIdAutor());
        values.put("IdHorario",solicitud.getIdHorario());
        values.put("Fecha",solicitud.getFecha());
        values.put("Descripcion",solicitud.getDescripcion());
        values.put("CapacidadEstimada",solicitud.getCapacidadEstimada());
        values.put("Tipo","SolicitudReserva");

        return db.insert("Solicitudes",null,values) >0 ;
    }

    public static boolean insertSolicitudAsignacion(Solicitud solicitud, SQLiteDatabase db){

        ContentValues values= new ContentValues();
        values.put("Id",solicitud.getId());
        values.put("IdEstado",solicitud.getIdEstado());
        values.put("IdAutor",solicitud.getIdAutor());
        values.put("IdHorario",solicitud.getIdHorario());
        values.put("Fecha",solicitud.getFecha());
        values.put("Descripcion",solicitud.getDescripcion());
        values.put("CapacidadEstimada",solicitud.getCapacidadEstimada());
        values.put("Tipo","SolicitudAsignacion");

        return db.insert("Solicitudes",null,values) >0 ;
    }

    public static int getNextID(SQLiteDatabase db){
        int nextID=0;
        Cursor cursor=db.query("Solicitudes",columns,null,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext())
            nextID++;


        return nextID;
    }

    private static class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String IdEstado = "IdEstado";
        public static final String IdAutor = "IdAutor";
        public static final String IdHorario = "IdHorario";
        public static final String Fecha = "Fecha";
        public static final String Descripcion = "Descripcion";
        public static final String CapacidadEstimada = "CapacidadEstimada";
        public static final String Tipo= "Tipo";
    }


}
