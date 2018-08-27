package Clases.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.LinkedList;

import Clases.Principales.Asignacion;
import Clases.Principales.Prestamo;
import Clases.Principales.Reserva;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaPrestamos implements Tabla {

    private static String[] columns={Columns.Id, Columns.Fecha, Columns.IdEstado, Columns.IdHorario,Columns.IdEspacio,Columns.Tipo,Columns.IdTitular, Columns.FechaDesde, Columns.FechaHasta};


    public  static boolean insertReserva(Prestamo p,SQLiteDatabase db){

        ContentValues values= new ContentValues();
        values.put("Id",p.getId());
        values.put("Fecha",p.getFecha());
        values.put("IdEstado",p.getIdEstado());
        values.put("IdEspacio",p.getIdEspacio());
        values.put("IdHorario",p.getIdHorario());
        values.put("Tipo","Reserva");
        values.put("IdTitular",((Reserva)p).getIdDocente());

        return db.insert("Prestamos",null,values)>0;

    }

    public static boolean insertAsignacion(Prestamo p, SQLiteDatabase db){

        ContentValues values= new ContentValues();
        values.put("Id",p.getId());
        values.put("Fecha",p.getFecha());
        values.put("IdEstado",p.getIdEstado());
        values.put("IdEspacio",p.getIdEspacio());
        values.put("IdHorario",p.getIdHorario());
        values.put("Tipo","Reserva");
        values.put("FechaHasta",((Asignacion)p).getFechaHasta());
        values.put("FechaDesde",((Asignacion)p).getFechaDesde());

        return db.insert("Prestamos",null,values)>0;
    }

    public static boolean eliminarPrestamo(int idPrestamo, SQLiteDatabase db){

        boolean exito=false;

        Cursor cursor=db.query("Prestamos",columns,Columns.Id +" = '"+idPrestamo+"'",null,null,null,null);

        if (cursor.moveToFirst()){
            exito= db.delete("Prestamos",Columns.Id + " = ?",new String[]{""+idPrestamo}) > 0;}

        return exito;
    }

    public static boolean actualizarPrestamo(Prestamo prestamo, SQLiteDatabase db){

        boolean exito=false;

        Cursor cursor=db.query("Prestamos",columns,Columns.Id +" = '"+prestamo.getId()+"'",null,null,null,null);

        if (cursor.moveToFirst()) {

            exito = db.delete("Prestamos", Columns.Id + " = ?", new String[]{"" + prestamo.getId()}) > 0;

            switch (cursor.getString(5)){

                case "Reserva":{
                    if (exito)
                        insertReserva(prestamo,db);
                    break;
                }

                case "Asignacion":{
                    if (exito)
                        insertAsignacion(prestamo,db);
                    break;
                }

            } //Fin switch

        }


        return exito;
    }

    public static LinkedList<Prestamo> getPrestamos(SQLiteDatabase db){

        Cursor cursor=db.query("Prestamos",columns,null,null,null,null,null);
        LinkedList<Prestamo> prestamos= new LinkedList<Prestamo>();
        Prestamo aux=null;

        while(!cursor.isClosed() && cursor.moveToNext()){

            switch (cursor.getString(5)){

                case "Reserva":{
                    aux= new Reserva(cursor.getInt(0),cursor.getString(1),cursor.getInt(3),cursor.getInt(4),cursor.getInt(6));
                    aux.setEstado(cursor.getInt(2));
                    break;
                }

                case "Asignacion":{
                    aux= new Asignacion(cursor.getInt(0),cursor.getString(1),cursor.getInt(3),cursor.getInt(4),cursor.getString(7),cursor.getString(8));
                    aux.setEstado(cursor.getInt(2));
                    break;
                }

            }

            prestamos.addLast(aux);

        }

        return prestamos;
    }

    public  static Prestamo findPrestamo(int id, SQLiteDatabase db){

        Prestamo toReturn=null;

        Cursor cursor=db.query("Prestamos",columns,Columns.Id +" = '"+id+"'",null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            switch (cursor.getString(5)){

                case "Reserva":{
                    toReturn= new Reserva(cursor.getInt(0),cursor.getString(1),cursor.getInt(3),cursor.getInt(4),cursor.getInt(6));
                    toReturn.setEstado(cursor.getInt(2));
                    break;
                }

                case "Asignacion":{
                    toReturn= new Asignacion(cursor.getInt(0),cursor.getString(1),cursor.getInt(3),cursor.getInt(4),cursor.getString(7),cursor.getString(8));
                    toReturn.setEstado(cursor.getInt(2));
                    break;
                }
            }
        }

        return toReturn;
    }


    public static int getNextID(SQLiteDatabase db){

        int nextID=0;
        Cursor cursor=db.query("Prestamos",columns,null,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext())
            nextID++;

        return nextID;
    }


    private class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String Fecha = "Fecha";
        public static final String IdEstado = "IdEstado";
        public static final String IdHorario= "IdHorario";
        public static final String IdEspacio= "IdEspacio";
        public static final String Tipo = "Tipo";
        public static final String IdTitular= "IdTitular";
        public static final String FechaDesde="FechaDesde";
        public static final String FechaHasta="FechaHasta";


    }

}
