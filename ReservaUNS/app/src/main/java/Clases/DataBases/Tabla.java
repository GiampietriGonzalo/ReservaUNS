package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class Tabla {

    public static int getNextID(SQLiteDatabase db, String nombreTabla, String[] columns) {

        Cursor cursor = db.query(nombreTabla, columns, null, null, null, null, null, null);
        int nextID = 0;
        if (!cursor.isClosed() && cursor.moveToNext()) {
            nextID = cursor.getInt(0);
            cursor.moveToPrevious();
        }

        while (!cursor.isClosed() && cursor.moveToNext()) {
            nextID++;
        }

        return nextID;
    }
}

