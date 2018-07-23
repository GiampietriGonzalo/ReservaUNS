package Clases.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import Clases.Principales.Usuario;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaUsuarios implements Tabla {


    private static final String[] columns={Columns.Id, Columns.Cuenta , Columns.Password, Columns.Legajo, Columns.Nombre, Columns.Mail, Columns.Tipo};


    public Usuario getDocente(int idUser, SQLiteDatabase db){

        Usuario docente= null;

        Cursor cursor=db.query("Usuarios",columns,Columns.Id +" = '"+idUser,null,null,null,null);


        return  docente;
    }


    private static class Columns implements BaseColumns {

        public static final String Id = "Id";
        public static final String Nombre = "Nombre";
        public static final String Cuenta= "Cuenta";
        public static final String Password = "Password";
        public static final String Mail = "Mail";
        public static final String Legajo = "Legajo";
        public static final String Tipo = "Tipo";
    }
}
