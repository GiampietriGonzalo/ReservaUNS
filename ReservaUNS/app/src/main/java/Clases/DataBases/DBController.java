package Clases.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by gonza on 16/07/18.
 */

public class DBController {

    private final static int DB_VERSION= 1;
    private final static String DB_NAME="DataBase";
    private static DBController dbC;
    private DBHelper dbHelper;
    private SQLiteDatabase sqlDB;

    private DBController(Context context) {
        dbHelper = new DBHelper(context);
        sqlDB=dbHelper.getWritableDatabase();
    }

    public static DBController getDbC(Context context) {
        if(dbC==null)
            dbC=new DBController(context);
        return dbC;
    }

    public void open(){
        sqlDB =dbHelper.getWritableDatabase();
    }

    public void close(){
        sqlDB.close();
    }




    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
            super(context, DB_NAME,null,DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {

            //db.execSQL(CommercesTable.getCreateTable());
            //db.execSQL(CityTable.getCreateTable());
            //db.execSQL(ProductServiceTable.getCreateTable());
            //db.execSQL(SectorTable.getCreateTable());
            //db.execSQL(CommercesAuxTable.getCreateTable());
            //db.execSQL(UsersTable.getCreateTable());
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("DROP TABLE IF EXISTS " + ProductServiceTable.getName());
            //db.execSQL("DROP TABLE IF EXISTS " + CommercesTable.getName());
            //db.execSQL("DROP TABLE IF EXISTS " + CityTable.getName());
            //db.execSQL("DROP TABLE IF EXISTS " + SectorTable.getName());
            // db.execSQL("drop table if exists " +  CommercesAuxTable.getName());
            //db.execSQL("drop table if exists " + UsersTable.getName());

            onCreate(db);   // Llamamos al método onCreate para volver a crear las tablas.
        }

        /*public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }*/


    }

}
