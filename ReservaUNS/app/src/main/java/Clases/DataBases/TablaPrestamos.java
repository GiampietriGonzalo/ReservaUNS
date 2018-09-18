package Clases.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.renderscript.Sampler;

import java.util.LinkedList;

import Clases.Estados.Aceptado;
import Clases.Estados.Activo;
import Clases.Estados.Cancelado;
import Clases.Estados.Estado;
import Clases.Estados.Rechazado;
import Clases.Estados.StateController;
import Clases.Principales.Asignacion;
import Clases.Principales.Prestamo;
import Clases.Principales.Reserva;
import pipenatr.Activities.ConsultarSolicitud;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaPrestamos extends Tabla {

    private static String[] columns={Columns.Id, Columns.Fecha, Columns.IdHorario,Columns.IdEspacio,Columns.Tipo,Columns.IdTitular, Columns.FechaDesde, Columns.FechaHasta, Columns.Estado};


   private static ContentValues insertBasico(Prestamo p){

       ContentValues values= new ContentValues();

       values.put("Id",p.getId());
       values.put("Fecha",p.getFecha());
       values.put("IdEspacio",p.getIdEspacio());
       values.put("IdHorario",p.getIdHorario());
       values.put("Estado",p.getEstado().toString());


       return values;
   }

    public static boolean insertReserva(Prestamo p,SQLiteDatabase db){

        ContentValues values= insertBasico(p);

        values.put("Tipo","Reserva");
        values.put("IdTitular",((Reserva)p).getIdDocente());


        return db.insert("Prestamos",null,values)>0;

    }

    public static boolean insertAsignacion(Prestamo p, SQLiteDatabase db){

        ContentValues values= insertBasico(p);

        values.put("Tipo","Asignacion");
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
         return db.update("Prestamos", insertBasico(prestamo),Columns.Id +" = "+prestamo.getId(),null) > 0;
    }

    public static LinkedList<Prestamo> getPrestamos(SQLiteDatabase db){

        Cursor cursor=db.query("Prestamos",columns,null,null,null,null,null);
        LinkedList<Prestamo> prestamos= new LinkedList<Prestamo>();
        Prestamo aux=null;
        Estado estado=null;

        while(!cursor.isClosed() && cursor.moveToNext()){

            estado=getEstado(cursor);

            switch (cursor.getString(4)){

                case "Reserva":{
                    aux= new Reserva(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(5),estado);
                    break;
                }

                case "Asignacion":{
                    aux= new Asignacion(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(6),cursor.getString(7),estado);
                    break;
                }

            }

            if(prestamos!=null)
                prestamos.addLast(aux);

        }

        return prestamos;
    }

    public static LinkedList<Prestamo> getReservas(SQLiteDatabase db){

        LinkedList<Prestamo> reservas= new LinkedList<Prestamo>();

        Cursor cursor=db.query("Prestamos",columns,Columns.Tipo+" = '"+"Reserva"+"'",null,null,null,null);
        Prestamo aux=null;
        Estado estado=null;


        while(!cursor.isClosed() && cursor.moveToNext()){

            estado=getEstado(cursor);
            aux= new Reserva(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(5),estado);

            reservas.addLast(aux);

        }




        return reservas;
    }

    public  static Prestamo findPrestamo(int id, SQLiteDatabase db){

        Prestamo toReturn=null;
        Estado estado;

        Cursor cursor=db.query("Prestamos",columns,Columns.Id +" = '"+id+"'",null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            estado=getEstado(cursor);

            switch (cursor.getString(4)){

                case "Reserva":{
                    toReturn= new Reserva(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(5),estado);
                    break;
                }

                case "Asignacion":{
                    toReturn= new Asignacion(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(6),cursor.getString(7),estado);
                    break;
                }
            }
        }

        return toReturn;
    }


    public static int getNextID(SQLiteDatabase db){
        return getNextID(db,"Prestamos",columns);
    }

    public static Estado getEstado(Cursor cursor) {

        Estado estado = null;

        switch (cursor.getString(8)) {

            case "Aceptado": {
                estado = StateController.getEstadoAceptado();
                break;
            }

            case "Cancelado": {
                estado = StateController.getEstadoCancelado();
                break;
            }

            case "Rechazado": {
                estado = StateController.getEstadoRechazado();
                break;
            }

            case "Activo": {
                estado = StateController.getEstadoActivo();
                break;
            }


        }
        return estado;
    }


    private class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String Fecha = "Fecha";
        public static final String IdHorario= "IdHorario";
        public static final String IdEspacio= "IdEspacio";
        public static final String Tipo = "Tipo";
        public static final String IdTitular= "IdTitular";
        public static final String FechaDesde="FechaDesde";
        public static final String FechaHasta="FechaHasta";
        public static final String Estado="Estado";

    }

}
