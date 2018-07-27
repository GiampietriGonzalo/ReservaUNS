package Clases.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;
import java.util.LinkedList;

import Clases.Principales.Aula;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;
import Clases.Principales.Usuario;

/**
 * Created by gonza on 16/07/18.
 */

public class DBController {

    private final static int DB_VERSION= 1;
    private final static String DB_NAME="DB";
    private static DBController dbC;
    private DBHelper dbHelper;
    private static SQLiteDatabase sqlDB;

    private DBController(Context context) {
        dbHelper = new DBHelper(context);
        sqlDB=dbHelper.getWritableDatabase();
    }

    public static SQLiteDatabase getDB() {
        return sqlDB;
    }

    public  DBController getDbC(Context context) {
        if(dbC==null)
            dbC=new DBController(context);
        return dbC;
    }

    public static Usuario findUsuario(int idUsuario){
        return TablaUsuarios.findUsuario(idUsuario,sqlDB);
    }


    public static LinkedList<Espacio> findEspaciosAReservar(String tipo,String nombreEdificioPreferencia, int capacidadEstimada){

        LinkedList<Espacio> espacios= new LinkedList<Espacio>();
        int idEdificioPreferencia= TablaEdificios.getIdEdificio(nombreEdificioPreferencia,sqlDB);

        //si idEdificioPreferencia = 9999 --> no se ingresó edificio de preferencia
        if(idEdificioPreferencia==9999)
            espacios= TablaEspacios.findEspaciosAReservarSinEdificioPreferencia(tipo,capacidadEstimada,sqlDB);
        else
            espacios= TablaEspacios.findEspaciosAReservar(tipo,idEdificioPreferencia,capacidadEstimada,sqlDB);

        return espacios;
    }


        public Espacio findAula(String nombreAula){

         Aula toReturn=null;

        if (nombreAula!=null && nombreAula!="")
            toReturn = TablaEspacios.findAula(nombreAula, sqlDB);


         return toReturn;

    }

    public LinkedList<Integer> findEspaciosDeEdificio(int idEdificio){
       return TablaEspacios.findEspacios(idEdificio, sqlDB);
    }

    public Edificio findDepartamento(int idDepartamento){
        return TablaEdificios.findDepartamento(idDepartamento, sqlDB);
    }

    public void open(){
        sqlDB =dbHelper.getWritableDatabase();
    }

    public void close(){
        sqlDB.close();
    }




    private class DBHelper extends SQLiteAssetHelper{


        public DBHelper(Context context){
            super(context, DB_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DB_VERSION);
            //super(context, name, factory, version);
        }





        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("DROP TABLE IF EXISTS " + ProductServiceTable.getName());
            //db.execSQL("DROP TABLE IF EXISTS " + CommercesTable.getName());
            //db.execSQL("DROP TABLE IF EXISTS " + CityTable.getName());
            //db.execSQL("DROP TABLE IF EXISTS " + SectorTable.getName());
            // db.execSQL("drop table if exists " +  CommercesAuxTable.getName());
            //db.execSQL("drop table if exists " + UsersTable.getName());

            //onCreate(db);   // Llamamos al método onCreate para volver a crear las tablas.
        }

        /*public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }*/


    }

}
