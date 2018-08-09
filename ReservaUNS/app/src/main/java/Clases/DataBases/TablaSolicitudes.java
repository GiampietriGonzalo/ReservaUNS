package Clases.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.net.ConnectException;
import java.util.LinkedList;

import Clases.Principales.Solicitud;
import Clases.Principales.SolicitudAsignacion;
import Clases.Principales.SolicitudReserva;

public class TablaSolicitudes implements Tabla {

    private static final String[] columns={Columns.Id, Columns.IdEstado , Columns.IdAutor, Columns.IdHorario, Columns.Fecha, Columns.Descripcion, Columns.CapacidadEstimada,Columns.Tipo};


    public static LinkedList<Solicitud> findSolicitudesUsuario(int idUsuario, SQLiteDatabase db){

        LinkedList<Solicitud> solicitudes=new LinkedList<Solicitud>();

        Cursor cursor=db.query("Solicitudes",columns,Columns.IdAutor +" = '"+idUsuario+"'",null,null,null,null);

        while(!cursor.isClosed() && cursor.moveToNext()){

            switch (cursor.getString(7)){

                case "SolicitudReserva":{
                    solicitudes.addLast(new SolicitudReserva (cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
                    break;
                }

                case "SolicitudAsignacion":{
                    solicitudes.addLast(new SolicitudAsignacion (cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
                    break;
                }

            } //Fin switch
        } //Fin while

        return solicitudes;

    }

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

    public static boolean cancelarSolicitud(int idSolicitud,SQLiteDatabase db){

        boolean exito=false;

        Cursor cursor=db.query("Solicitudes",columns,Columns.Id +" = '"+idSolicitud+"'",null,null,null,null);

        if (cursor.moveToFirst()){
            exito= db.delete("Solicitudes",Columns.Id + " = ?",new String[]{""+idSolicitud}) > 0;}

        return exito;
    }

    public static LinkedList<Solicitud> getSolicitudes(SQLiteDatabase db){

        LinkedList<Solicitud> solicitudes= new LinkedList<Solicitud>();
        Solicitud aux=null;

        Cursor cursor=db.query("Solicitudes",columns,null,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()){

            switch (cursor.getString(7)){

                case "Reserva":{
                    aux=new SolicitudReserva(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                    break;
                }

                case "Asignacion":{
                    aux=new SolicitudAsignacion(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));

                }

            }
            solicitudes.addLast(aux);
        }

        return  solicitudes;

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
