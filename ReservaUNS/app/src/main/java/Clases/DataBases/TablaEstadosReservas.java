package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class TablaEstadosReservas implements Tabla {

    private static String[] columns={Columns.Id,Columns.IdReserva};

    public static int getNextID(SQLiteDatabase db){
        int toReturn=0;

        Cursor cursor=db.query("EstadosPrestamos",columns, Columns.Id ,null,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext()) {
            toReturn++;
        }

        return toReturn;
    }

    private class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String IdReserva =  "IdReserva";

    }



}
