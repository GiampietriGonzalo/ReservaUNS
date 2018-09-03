package Clases.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import Clases.Estados.EstadoPrestamo;
import Clases.Estados.PrestamoActivo;
import Clases.Estados.PrestamoCancelado;

public class TablaEstadosPrestamos extends Tabla {

    private static String[] columns={Columns.Id,Columns.IdPrestamo,Columns.Tipo};

    public static boolean insertPrestamoActivo(EstadoPrestamo estado, SQLiteDatabase db){

        ContentValues values= new ContentValues();

        values.put("Id",estado.getId());
        values.put("IdPrestamo",estado.getIdPrestamo());
        values.put("Tipo","PrestamoActivo");

        return db.insert("EstadosPrestamos",null,values)>0;
    }

    public static boolean insertPrestamoCancelado(EstadoPrestamo estado, SQLiteDatabase db){

        ContentValues values= new ContentValues();

        values.put("Id",estado.getId());
        values.put("IdPrestamo",estado.getIdPrestamo());
        values.put("Tipo","PrestamoCancelado");

        return db.insert("EstadosPrestamos",null,values)>0;
    }


    public static EstadoPrestamo findEstadoPrestamo(int id, SQLiteDatabase db){

        EstadoPrestamo toReturn=null;
        Cursor cursor=db.query("EstadosPrestamos",columns, Columns.Id + " = '" + id +"'",null,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()){

            switch (cursor.getString(2)){

                case "PrestamoActivo":{
                    toReturn= new PrestamoActivo(cursor.getInt(0),cursor.getInt(1));
                    break;
                }

                case "PrestamoCancelado":{
                    toReturn= new PrestamoCancelado(cursor.getInt(0),cursor.getInt(1));
                    break;
                }
            }

        }

        return toReturn;
    }


    public static int getNextID(SQLiteDatabase db){
        return getNextID(db,"EstadosPrestamos",columns);
    }

    public static boolean eliminarEstadoPrestamo(int idEstadoPrestamo,SQLiteDatabase db){

        boolean exito=false;

        Cursor cursor=db.query("EstadosPrestamos",columns,Columns.Id +" = '"+idEstadoPrestamo+"'",null,null,null,null);

        if (cursor.moveToFirst()){
            exito= db.delete("EstadosPrestamos",Columns.Id + " = ?",new String[]{""+idEstadoPrestamo}) > 0;}

        return exito;
    }

    private class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String IdPrestamo =  "IdPrestamo";
        public static final String Tipo = "Tipo";

    }



}
