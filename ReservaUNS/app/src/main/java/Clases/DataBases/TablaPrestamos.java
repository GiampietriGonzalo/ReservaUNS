package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import Clases.Principales.Asignacion;
import Clases.Principales.Prestamo;
import Clases.Principales.Reserva;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaPrestamos implements Tabla {

    private static String[] columns={Columns.Id, Columns.Descripcion, Columns.Fecha, Columns.IdEstado, Columns.IdHorario,Columns.IdEspacio,Columns.Tipo,Columns.IdTitular, Columns.FechaDesde, Columns.FechaHasta};


    public  static Prestamo findPrestamo(int id, SQLiteDatabase db){

        Prestamo toReturn=null;

        Cursor cursor=db.query("Prestamos",columns,Columns.Id +" = '"+id,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            switch (cursor.getString(6)){

                case "Reserva":{
                    toReturn= new Reserva(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(4),cursor.getInt(5),cursor.getInt(7));
                    toReturn.setEstado(cursor.getInt(3));
                    break;
                }

                case "Asignacion":{
                    toReturn= new Asignacion(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(4),cursor.getInt(5),cursor.getString(8),cursor.getString(9));
                    break;
                }
            }
        }

        return toReturn;
    }


    private class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String Descripcion =  "Descripcion";
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
