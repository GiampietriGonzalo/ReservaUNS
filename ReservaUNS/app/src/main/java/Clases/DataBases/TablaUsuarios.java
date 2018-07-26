package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import Clases.Principales.Docente;
import Clases.Principales.EmpleadoDepartamento;
import Clases.Principales.EmpleadoSec;
import Clases.Principales.Usuario;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaUsuarios implements Tabla {


    private static final String[] columns={Columns.Id, Columns.Cuenta , Columns.Password, Columns.Legajo, Columns.Nombre, Columns.Apellido,Columns.Mail, Columns.Telefono ,Columns.Tipo};


    public static Usuario findUsuario(int idUser, SQLiteDatabase db) {

        Usuario toReturn = null;

        Cursor cursor = db.query("Usuarios", columns, Columns.Id + " = '" + idUser, null, null, null, null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            switch (cursor.getString(8)) {

                case "Docente": {
                    toReturn = new Docente(cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getInt(3), cursor.getString(6), cursor.getString(7));
                    break;
                }

                case "EmpleadoDepartamento": {
                    toReturn = new EmpleadoDepartamento(cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getInt(3), cursor.getString(6), cursor.getString(7));
                    break;
                }

                case "EmpleadoSec":{
                    toReturn = new EmpleadoSec(cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getInt(3), cursor.getString(6), cursor.getString(7));
                    break;
                }

            }
        }
            return toReturn;

    }


    public static Docente getDocente(int idUser, SQLiteDatabase db){

        Docente docente= null;

        Cursor cursor=db.query("Usuarios",columns,Columns.Id +" = '"+idUser,null,null,null,null);


        return  docente;
    }

    public static int getNextID(SQLiteDatabase db){
        int nextID=0;
        Cursor cursor=db.query("Usuarios",columns,null,null,null,null,null);

        while (!cursor.isClosed() && cursor.moveToNext())
            nextID++;


        return nextID;
    }


    private static class Columns implements BaseColumns {

        public static final String Id = "Id";
        public static final String Nombre = "Nombre";
        public static final String Apellido= "Apellido";
        public static final String Cuenta= "Cuenta";
        public static final String Password = "Password";
        public static final String Mail = "Mail";
        public static final String Telefono= "Telefono";
        public static final String Legajo = "Legajo";
        public static final String Tipo = "Tipo";
    }
}
