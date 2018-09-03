package Clases.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import Clases.Principales.Docente;
import Clases.Principales.EmpleadoDepartamento;
import Clases.Principales.EmpleadoSecretaria;
import Clases.Principales.Usuario;

/**
 * Created by gonza on 16/07/18.
 */

public class TablaUsuarios extends Tabla {


    private static final String[] columns={Columns.Id, Columns.Password, Columns.Legajo, Columns.Nombre, Columns.Apellido,Columns.Mail, Columns.Telefono ,Columns.Tipo};


    public static boolean insertDocente(Usuario docente,SQLiteDatabase db){

        ContentValues values= new ContentValues();
        values.put("Id",docente.getId());
        values.put("Password",docente.getPassword());
        values.put("Legajo",docente.getLegajo());
        values.put("Nombre",docente.getNombre());
        values.put("Apellido",docente.getApellido());
        values.put("Mail",docente.getMail());
        values.put("Telefono",docente.getTelefono());
        values.put("Tipo","Docente");

        return db.insert("Usuarios",null,values)>0;
    }

    public static boolean insertEmpleadoDepartamento(Usuario empleado,SQLiteDatabase db){

        ContentValues values= new ContentValues();
        values.put("Id",empleado.getId());
        values.put("Password",empleado.getPassword());
        values.put("Legajo",empleado.getLegajo());
        values.put("Nombre",empleado.getNombre());
        values.put("Apellido",empleado.getApellido());
        values.put("Mail",empleado.getMail());
        values.put("Telefono",empleado.getTelefono());
        values.put("Tipo","EmpleadoDepartamento");

        return db.insert("Usuarios",null,values)>0;
    }

    public static boolean insertEmpleadoSecretaria(Usuario empleado,SQLiteDatabase db){

        ContentValues values= new ContentValues();
        values.put("Id",empleado.getId());
        values.put("Password",empleado.getPassword());
        values.put("Legajo",empleado.getLegajo());
        values.put("Nombre",empleado.getNombre());
        values.put("Apellido",empleado.getApellido());
        values.put("Mail",empleado.getMail());
        values.put("Telefono",empleado.getTelefono());
        values.put("Tipo","EmpleadoSecretaria");

        return db.insert("Usuarios",null,values)>0;
    }

    public static Usuario findUsuario(int idUser, SQLiteDatabase db) {

        Usuario toReturn = null;

        Cursor cursor = db.query("Usuarios", columns, Columns.Id + " = '" + idUser+"'", null, null, null, null);

        while (!cursor.isClosed() && cursor.moveToNext()) {

            switch (cursor.getString(7)) {

                case "Docente": {
                    toReturn = new Docente(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getInt(2), cursor.getString(5), cursor.getString(6));
                    break;
                }

                case "EmpleadoDepartamento": {
                    toReturn = new EmpleadoDepartamento(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getInt(2), cursor.getString(5), cursor.getString(6));
                    break;
                }

                case "EmpleadoSecretaria":{
                    toReturn = new EmpleadoSecretaria(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getInt(2), cursor.getString(5), cursor.getString(6));
                    break;
                }

            }
        }
            return toReturn;

    }


    public static Usuario verificarLogIn(String mail, String contraseña, SQLiteDatabase db){

        Cursor cursor=db.query("Usuarios",columns,Columns.Mail +" = '"+mail+"'",null,null,null,null);
        Usuario usuario=null;

        if (cursor.moveToNext() && cursor.getString(1).equals(contraseña)){

            switch (cursor.getString(7)) {

                case "Docente": {
                    usuario = new Docente(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getInt(2), cursor.getString(5), cursor.getString(6));
                    break;
                }

                case "EmpleadoDepartamento": {
                    usuario = new EmpleadoDepartamento(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getInt(2), cursor.getString(5), cursor.getString(6));
                    break;
                }

                case "EmpleadoSecretaria":{
                    usuario = new EmpleadoSecretaria(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getInt(2), cursor.getString(5), cursor.getString(6));
                    break;
                }
            }
        }

        return  usuario;
    }

    public static int getNextID(SQLiteDatabase db){
        return getNextID(db,"Usuarios",columns);
    }



    private static class Columns implements BaseColumns {

        public static final String Id = "Id";
        public static final String Nombre = "Nombre";
        public static final String Apellido= "Apellido";
        public static final String Password = "Password";
        public static final String Mail = "Mail";
        public static final String Telefono= "Telefono";
        public static final String Legajo = "Legajo";
        public static final String Tipo = "Tipo";
    }
}
